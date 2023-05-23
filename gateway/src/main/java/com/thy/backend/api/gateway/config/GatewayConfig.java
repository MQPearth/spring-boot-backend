package com.thy.backend.api.gateway.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>GatewayConfig</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/5/23 11:35:49
 */
@Configuration
public class GatewayConfig {
    @Bean
    public CacheManager cacheManager() {
        return new CaffeineCacheManager();
    }
}
