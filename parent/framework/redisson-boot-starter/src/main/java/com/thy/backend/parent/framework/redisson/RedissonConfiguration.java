package com.thy.backend.parent.framework.redisson;

import cn.hutool.core.util.StrUtil;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * <p>RedissonConfiguration</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/26 17:28:13
 */
@Import(RedissonSingleConfig.class)
@Configuration
public class RedissonConfiguration {

    @Bean("redissonClient")
    @ConditionalOnProperty(prefix = "redisson", name = "mode", havingValue = "single")
    public RedissonClient redissonClient(RedissonSingleConfig redissonSingleConfig) {
        Config config = new Config();
        config.useSingleServer().setAddress(redissonSingleConfig.getAddress());
        config.useSingleServer().setDatabase(redissonSingleConfig.getDatabase());
        if (StrUtil.isNotBlank(redissonSingleConfig.getPassword())) {
            config.useSingleServer().setPassword(redissonSingleConfig.getPassword());
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
