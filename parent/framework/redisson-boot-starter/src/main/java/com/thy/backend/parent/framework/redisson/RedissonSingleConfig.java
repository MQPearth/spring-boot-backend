package com.thy.backend.parent.framework.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author root
 */
@Data
@ConfigurationProperties(prefix = "redisson.single")
public class RedissonSingleConfig {

    private String address;

    private Integer database;

    private String password;

}