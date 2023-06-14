package com.thy.backend.parent.framework.mp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>SnowflakeRedissonConfig</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/26 18:18:46
 */
@Slf4j
@Component
public class SnowflakeRedissonConfig implements SmartLifecycle {

    private static long WORKER_ID;

    private static long DATA_CENTER_ID;

    private static String KEY;

    private static RLock LOCK;

    public static final int MAX_RETRY = 10;

    private volatile boolean running = false;

    @Bean
    public DefaultIdentifierGenerator defaultIdentifierGenerator(
            @Value("snowflake.redisson.mode") String mode,
            ApplicationContext context
    ) throws Exception {
        RedissonClient client = getRedissonClient(mode, context);

        Random random = new Random();
        try {
            int inc = 0;
            while (true) {
                WORKER_ID = random.nextLong(1, 31);
                DATA_CENTER_ID = random.nextLong(1, 31);

                KEY = "snowflake_lock_" + DATA_CENTER_ID + "_" + WORKER_ID;
                LOCK = client.getFairLock(KEY);
                // wait 3s
                boolean isLock = LOCK.tryLock(3, TimeUnit.SECONDS);
                if (isLock) {
                    break;
                }
                if (inc >= MAX_RETRY) {
                    throw new Exception("雪花ID数据节点冲突: " + KEY);
                }
                inc++;
                log.warn("雪花ID数据节点冲突, 发起重试, inc: {}", inc);
            }
            log.info("current snowflake node: {}", KEY);
        } catch (Exception e) {
            log.error("defaultIdentifierGenerator, lockKey: {}", KEY, e);
            throw e;
        }

        return new DefaultIdentifierGenerator(WORKER_ID, DATA_CENTER_ID);
    }

    private RedissonClient getRedissonClient(String mode, ApplicationContext context) {
        if ("cluster".equals(mode)) {
            return redissonClientCluster(context.getBean(SnowflakeRedissonClusterConfig.class));
        } else {
            return redissonClientSingle(context.getBean(SnowflakeRedissonSingleConfig.class));
        }
    }

    public RedissonClient redissonClientCluster(SnowflakeRedissonClusterConfig redissonConfig) {
        Config config = new Config();
        ClusterServersConfig serversConfig = config.useClusterServers();
        serversConfig.addNodeAddress(redissonConfig.getNodeAddress());
        if (StrUtil.isNotBlank(redissonConfig.getPassword())) {
            serversConfig.setPassword(redissonConfig.getPassword());
        }

        serversConfig.setSubscriptionConnectionPoolSize(1);
        serversConfig.setSubscriptionConnectionMinimumIdleSize(1);
        serversConfig.setSlaveConnectionMinimumIdleSize(1);
        serversConfig.setSlaveConnectionPoolSize(1);
        serversConfig.setMasterConnectionMinimumIdleSize(1);
        serversConfig.setMasterConnectionPoolSize(1);

        return Redisson.create(config);
    }


    public RedissonClient redissonClientSingle(SnowflakeRedissonSingleConfig redissonConfig) {
        Config config = new Config();
        SingleServerConfig singleConfig = config.useSingleServer();
        singleConfig.setAddress(redissonConfig.getAddress());
        singleConfig.setDatabase(redissonConfig.getDatabase());

        if (StrUtil.isNotBlank(redissonConfig.getPassword())) {
            singleConfig.setPassword(redissonConfig.getPassword());
        }
        singleConfig.setConnectionPoolSize(1);
        singleConfig.setConnectionMinimumIdleSize(1);
        singleConfig.setSubscriptionConnectionMinimumIdleSize(1);
        singleConfig.setSubscriptionConnectionPoolSize(1);

        return Redisson.create(config);
    }

    public static long getWorkerId() {
        return WORKER_ID;
    }

    public static long getDataCenterId() {
        return DATA_CENTER_ID;
    }

    @Override
    public int getPhase() {
        //在 WebServerGracefulShutdownLifecycle 那一组之后
        return SmartLifecycle.DEFAULT_PHASE - 1;
    }

    @Override
    public void start() {
        this.running = true;
    }

    @Override
    public void stop() {
        doStop();
    }

    public synchronized void doStop() {
        if (this.running) {
            this.running = false;
            try {
                if (Objects.nonNull(LOCK) && LOCK.isLocked()) {
                    LOCK.unlock();
                    log.info("The data node of the snowflake ID has been released, key: {}", KEY);
                }
            } catch (Exception e) {
                try {
                    if (Objects.nonNull(LOCK) && LOCK.isLocked()) {
                        LOCK.forceUnlock();
                        log.info("The data node of the snowflake ID has been forcibly released, key: {}", KEY);
                    }
                } catch (Exception ex) {
                    log.error("The release of the data node for the snowflake ID has failed, key: {}", KEY, ex);
                }
            }
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }


}
