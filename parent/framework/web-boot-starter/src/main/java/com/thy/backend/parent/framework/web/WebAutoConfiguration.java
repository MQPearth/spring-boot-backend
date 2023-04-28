package com.thy.backend.parent.framework.web;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.thy.backend.parent.framework.web.config.WebMvcConfig;
import com.thy.backend.parent.framework.web.lang.CustomModule;
import com.thy.backend.parent.framework.web.module.DefaultJavaTimeModule;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.List;

/**
 * <p>WebAutoConfiguration</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 14:26:13
 */
@Slf4j
@Import(DefaultJavaTimeModule.class)
public class WebAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WebMvcConfig webMvcConfig(List<CustomModule> moduleList) {
        return new WebMvcConfig(moduleList);
    }


    @Bean
    public Decoder feignDecoder(DefaultJavaTimeModule javaTimeModule) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.registerModule(javaTimeModule.getSimpleModule());
        om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.EVERYTHING,
                JsonTypeInfo.As.PROPERTY);

        TypeFactory typeFactory = TypeFactory.defaultInstance();

        return (response, type) -> {
            JavaType javaType = typeFactory.constructType(type);
            return om.readValue(response.body().asInputStream(), javaType);
        };
    }

}
