package com.thy.backend.parent.framework.service.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thy.backend.parent.framework.web.config.WebMvcConfig;
import com.thy.backend.parent.framework.web.lang.CustomModule;

import java.util.List;

/**
 * <p>ServiceWebMvcConfig</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 15:00:08
 */
public class ServiceWebMvcConfig extends WebMvcConfig {
    public ServiceWebMvcConfig(List<CustomModule> moduleList) {
        super(moduleList);
    }

    @Override
    protected ObjectMapper customizeObjectMapper(ObjectMapper om) {
        om.activateDefaultTyping(om.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.EVERYTHING,
                JsonTypeInfo.As.PROPERTY);
        return om;
    }
}
