package com.thy.backend.record.service.record.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author root
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.thy.backend.record.service.record.web.dao")
public class RecordWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordWebApplication.class, args);
    }
}