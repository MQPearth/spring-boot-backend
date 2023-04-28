package com.thy.backend.parent.framework.web.config;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.thy.backend.parent.base.lang.CustomModule;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>CodeEnumModule</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 11:03:55
 */
public class CustomModuleConfig {

    @Bean("javaTimeModule")
    public CustomModule javaTimeModule() {
        JavaTimeModule timeModule = new JavaTimeModule();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(pattern));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(pattern));
        return CustomModule.build(timeModule);
    }

}
