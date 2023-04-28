package com.thy.backend.parent.framework.redisson;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * <p>RedissonConfiguration</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/26 17:28:13
 */
@Import(RedissonSingleConfig.class)
public class RedissonAutoConfiguration {

    @Bean("redissonClient")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "redisson", name = "mode", havingValue = "single")
    public RedissonClient redissonClient(RedissonSingleConfig redissonSingleConfig) {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer();
        serverConfig.setAddress(redissonSingleConfig.getAddress());
        serverConfig.setDatabase(redissonSingleConfig.getDatabase());
        if (StrUtil.isNotBlank(redissonSingleConfig.getPassword())) {
            serverConfig.setPassword(redissonSingleConfig.getPassword());
        }

        if (redissonSingleConfig.getConnectionPoolSize() != null) {
            serverConfig.setConnectionPoolSize(redissonSingleConfig.getConnectionPoolSize());
        }

        if (redissonSingleConfig.getConnectionMinimumIdleSize() != null) {
            serverConfig.setConnectionMinimumIdleSize(redissonSingleConfig.getConnectionMinimumIdleSize());
        }
        if (redissonSingleConfig.getSubscriptionConnectionMinimumIdleSize() != null) {
            serverConfig.setSubscriptionConnectionMinimumIdleSize(
                    redissonSingleConfig.getSubscriptionConnectionMinimumIdleSize());
        }
        if (redissonSingleConfig.getSubscriptionConnectionPoolSize() != null) {
            serverConfig.setSubscriptionConnectionPoolSize(redissonSingleConfig.getSubscriptionConnectionPoolSize());
        }
        return Redisson.create(config);
    }


    @Bean
    public RedissonLockUtil redissonLockUtil(RedissonClient redissonClient) {
        RedissonLockUtil util = new RedissonLockUtil();
        util.setRedissonClient(redissonClient);
        return util;
    }

}
