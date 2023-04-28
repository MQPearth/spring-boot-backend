package com.thy.backend.parent.framework.api;

import com.thy.backend.parent.framework.api.exception.GlobalExceptionHandler;
import com.thy.backend.parent.framework.api.module.CodeEnumModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;

/**
 * @author root
 */
@Slf4j
@Import({GlobalExceptionHandler.class, CodeEnumModule.class})
public class ApiAutoConfiguration {


}