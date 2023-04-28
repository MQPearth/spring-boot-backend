package com.thy.backend.parent.framework.api;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thy.backend.parent.base.lang.CustomModule;
import com.thy.backend.parent.framework.api.config.CustomModuleConfig;
import com.thy.backend.parent.framework.api.config.WebMvcConfig;
import com.thy.backend.parent.framework.api.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @author root
 */
@Slf4j
@Import({GlobalExceptionHandler.class, CustomModuleConfig.class, WebMvcConfig.class})
public class ApiConfiguration {

    @Bean("objectMapperForApi")
    public ObjectMapper objectMapperForApi(List<CustomModule> moduleList) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.registerModules(moduleList.stream().map(CustomModule::getSimpleModule).toList());
        return objectMapper;
    }

    @Primary
    @Bean("objectMapperForOpenfeign")
    public ObjectMapper objectMapperForOpenfeign(@Qualifier("javaTimeModule") CustomModule module) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(module.getSimpleModule());
        return objectMapper;
    }

}