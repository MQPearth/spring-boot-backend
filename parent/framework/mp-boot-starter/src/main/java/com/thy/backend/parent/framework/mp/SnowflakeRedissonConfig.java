package com.thy.backend.parent.framework.mp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;

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
@Data
@ConfigurationProperties(prefix = "snowflake.redisson")
public class SnowflakeRedissonConfig implements SmartLifecycle {

    private String address;

    private Integer database;

    private String password;


    private static long WORKER_ID;

    private static long DATA_CENTER_ID;

    private static String KEY;

    private static RLock LOCK;

    public static final int MAX_RETRY = 10;

    private volatile boolean running = false;

    @Bean
    public DefaultIdentifierGenerator defaultIdentifierGenerator(SnowflakeRedissonConfig redissonConfig)
            throws Exception {
        Config config = new Config();
        SingleServerConfig singleConfig = config.useSingleServer();
        singleConfig.setAddress(redissonConfig.getAddress());
        singleConfig.setDatabase(redissonConfig.getDatabase());
        if (StrUtil.isNotBlank(redissonConfig.getPassword())) {
            config.useSingleServer().setPassword(redissonConfig.getPassword());
        }
        singleConfig.setConnectionPoolSize(1);
        singleConfig.setConnectionMinimumIdleSize(1);
        singleConfig.setSubscriptionConnectionMinimumIdleSize(1);

        RedissonClient client = Redisson.create(config);

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
            }
            log.info("current snowflake node: {}", KEY);
        } catch (Exception e) {
            log.error("defaultIdentifierGenerator, lockKey: {}", KEY, e);
            throw e;
        }

        return new DefaultIdentifierGenerator(WORKER_ID, DATA_CENTER_ID);
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
        this.running = false;

        try {
            if (Objects.nonNull(LOCK) && LOCK.isLocked()) {
                LOCK.forceUnlock();
            }
        } catch (Exception e) {
            log.error("雪花ID数据节点释放失败, key: {}", KEY, e);
        }

    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
