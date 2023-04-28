package com.thy.backend.parent.framework.web.module;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.thy.backend.parent.framework.web.lang.CustomModule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>DefaultJavaTimeModule</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 14:40:50
 */
public class DefaultJavaTimeModule extends CustomModule {

    public DefaultJavaTimeModule() {
        JavaTimeModule timeModule = new JavaTimeModule();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(pattern));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(pattern));
        this.simpleModule = timeModule;
    }
}
