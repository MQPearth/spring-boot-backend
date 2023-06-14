package com.thy.backend.parent.framework.mp;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>SnowflakeRedissonSingleConfig</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/6/14 09:15:02
 */
@Data
@ConfigurationProperties(prefix = "snowflake.redisson.single")
public class SnowflakeRedissonSingleConfig {
    private String address;

    private String password;

    private Integer database;
}
