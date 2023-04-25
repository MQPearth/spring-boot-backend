package com.thy.backend.parent.framework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author root
 */
@Configuration
@Slf4j
@Import(GlobalExceptionHandler.class)
public class GlobalExceptionConfiguration {


}