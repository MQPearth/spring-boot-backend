package com.thy.backend.parent.framework.mp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>SnowflakeRedissonClusterConfig</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/6/14 09:15:02
 */
@Data
@ConfigurationProperties(prefix = "snowflake.redisson.cluster")
public class SnowflakeRedissonClusterConfig {
    private String nodeAddress;

    private String password;
}
