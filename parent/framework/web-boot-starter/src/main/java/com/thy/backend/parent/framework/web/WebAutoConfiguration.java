package com.thy.backend.parent.framework.web;

import com.thy.backend.parent.framework.web.config.WebMvcConfig;
import com.thy.backend.parent.framework.web.lang.CustomModule;
import com.thy.backend.parent.framework.web.module.DefaultJavaTimeModule;
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
@Import(DefaultJavaTimeModule.class)
public class WebAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WebMvcConfig webMvcConfig(List<CustomModule> moduleList) {
        return new WebMvcConfig(moduleList);
    }
}
