package com.thy.backend.parent.framework.mp;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>SnowflakeRedissonConfig</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/26 18:18:46
 */
@Getter
@ConfigurationProperties(prefix = "snowflake.redisson")
public class SnowflakeRedissonConfig {
    private String address;

    private Integer database;

    private String password;
}
