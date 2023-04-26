package com.thy.backend.parent.framework.redisson;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author root
 */
@Getter
@ConfigurationProperties(prefix = "redisson.single")
public class RedissonSingleConfig {

    private String address;

    private Integer database;

    private String password;

}