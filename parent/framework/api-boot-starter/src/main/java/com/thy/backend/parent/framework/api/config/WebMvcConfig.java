package com.thy.backend.parent.framework.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired
    @Qualifier("objectMapperForApi")
    ObjectMapper objectMapper;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        var stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        var converter = new MappingJackson2HttpMessageConverter();

        converter.setObjectMapper(objectMapper);
        converters.add(0, stringHttpMessageConverter);
        converters.add(0, converter);
    }


}