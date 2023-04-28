package com.thy.backend.parent.framework.service;

import com.thy.backend.parent.framework.service.config.ServiceWebMvcConfig;
import com.thy.backend.parent.framework.web.lang.CustomModule;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * <p>WebConfiguration</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 12:31:34
 */
public class ServiceAutoConfiguration {

    @Bean
    public ServiceWebMvcConfig webMvcConfig(List<CustomModule> moduleList) {
        return new ServiceWebMvcConfig(moduleList);
    }
}
