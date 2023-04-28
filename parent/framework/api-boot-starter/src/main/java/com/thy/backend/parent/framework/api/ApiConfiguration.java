package com.thy.backend.parent.framework.api;

import com.thy.backend.parent.framework.api.config.CustomModuleConfig;
import com.thy.backend.parent.framework.api.config.WebMvcConfig;
import com.thy.backend.parent.framework.api.exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;

/**
 * @author root
 */
@Slf4j
@Import({GlobalExceptionHandler.class, CustomModuleConfig.class, WebMvcConfig.class})
public class ApiConfiguration {


}