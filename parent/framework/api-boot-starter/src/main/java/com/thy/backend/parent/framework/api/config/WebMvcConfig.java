package com.thy.backend.parent.framework.api.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thy.backend.parent.base.lang.CustomModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author root
 */
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    public WebMvcConfig(List<CustomModule> moduleList) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        objectMapper.registerModules(moduleList.stream().map(CustomModule::getSimpleModule).toList());
        this.objectMapper = objectMapper;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        var stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        var converter = new MappingJackson2HttpMessageConverter();

        converter.setObjectMapper(objectMapper);
        converters.add(0, stringHttpMessageConverter);
        converters.add(0, converter);
    }


}