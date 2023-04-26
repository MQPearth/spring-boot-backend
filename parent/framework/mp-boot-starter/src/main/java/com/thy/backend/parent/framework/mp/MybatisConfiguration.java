package com.thy.backend.parent.framework.mp;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author thy
 */
@Slf4j
@Configuration
@Import({MetaPersistentObjectHandler.class, SnowflakeRedissonConfig.class})
public class MybatisConfiguration {

    private static long WORKER_ID = new Random().nextLong(1, 31);

    private static long DATA_CENTER_ID = new Random().nextLong(1, 31);

    private static String KEY;

    private static RLock LOCK;

    public static final int MAX_RETRY = 10;

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }


    @Bean
    public DefaultIdentifierGenerator defaultIdentifierGenerator(SnowflakeRedissonConfig redissonConfig)
            throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress(redissonConfig.getAddress());
        config.useSingleServer().setDatabase(redissonConfig.getDatabase());
        if (StrUtil.isNotBlank(redissonConfig.getPassword())) {
            config.useSingleServer().setPassword(redissonConfig.getPassword());
        }
        RedissonClient client = Redisson.create(config);

        try {
            int inc = 0;
            while (true) {
                KEY = DATA_CENTER_ID + "_" + WORKER_ID;
                LOCK = client.getFairLock(KEY);
                boolean isLock = LOCK.tryLock(3, TimeUnit.SECONDS);
                if (isLock) {
                    break;
                }
                if (inc >= MAX_RETRY) {
                    throw new Exception("雪花ID数据节点冲突: " + KEY);
                }
                Random random = new Random();
                WORKER_ID = random.nextLong(1, 31);
                DATA_CENTER_ID = random.nextLong(1, 31);
                inc++;
            }
        } catch (Exception e) {
            log.error("defaultIdentifierGenerator, lockKey: {}", KEY, e);
            throw e;
        }

        return new DefaultIdentifierGenerator(WORKER_ID, DATA_CENTER_ID);
    }

    @PreDestroy
    public void destroy() {
        try {
            if (Objects.nonNull(LOCK) && LOCK.isLocked()) {
                LOCK.unlock();
            }
        } catch (Exception e) {
            log.error("雪花ID数据节点释放失败, key: {}", KEY, e);
        }
    }
}