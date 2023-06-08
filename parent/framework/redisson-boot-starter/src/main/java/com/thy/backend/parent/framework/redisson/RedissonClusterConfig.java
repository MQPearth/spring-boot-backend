package com.thy.backend.parent.framework.redisson;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author root
 */
@Data
@ConfigurationProperties(prefix = "redisson.cluster")
public class RedissonClusterConfig {

    private String nodeAddress;

    private String password;

}