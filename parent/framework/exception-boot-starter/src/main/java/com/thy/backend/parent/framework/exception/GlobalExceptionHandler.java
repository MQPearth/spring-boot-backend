package com.thy.backend.parent.framework.exception;

import com.thy.backend.parent.base.result.ApiResult;
import com.thy.backend.parent.base.result.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author root
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResult<String> defaultHandler(Exception e) {
        log.error("defaultHandler", e);
        return ApiResult.fail();
    }

    /**
     * 请求方式错误
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class})
    public ApiResult<String> httpRequestMethodNotSupportedExceptionHandler(Exception e) {
        return ApiResult.build(ResultEnum.REQUEST_ERROR);
    }

    /**
     * controller参数异常/缺少
     */
    @ExceptionHandler({MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class})
    public ApiResult<String> missingServletRequestParameterException(Exception e) {
        return ApiResult.build(ResultEnum.PARAMS_ERROR);
    }


    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ApiResult<String> bindExceptionMethodArgumentNotValidException(Exception e) {
        BindingResult bindingResult;
        if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
        } else {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        }
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getDefaultMessage()).append(" ");
        }
        return ApiResult.paramsError(errorMessage.toString());
    }


    /**
     * 未找到处理器 异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult<String> noHandlerFoundExceptionHandler(Exception e) {
        return ApiResult.build(ResultEnum.RESOURCE_NOT_EXIST_ERROR);
    }

}