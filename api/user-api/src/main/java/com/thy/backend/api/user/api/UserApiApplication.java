package com.thy.backend.api.user.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>UserApiApplication</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/27 17:55:33
 */
@ComponentScan("com.thy.backend")
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.thy.backend")
public class UserApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApiApplication.class, args);
    }
}
