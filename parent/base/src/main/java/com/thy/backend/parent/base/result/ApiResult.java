package com.thy.backend.parent.base.result;

import lombok.Data;

/**
 * ApiResult
 *
 * @param <T>
 * @author root
 */
@Data
public final class ApiResult<T> implements BaseResult<T> {

    private String msg;
    private String code;
    private T data;


    @Override
    public boolean isOk() {
        return ResultEnum.OK.getCode().equals(code);
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public BaseResult<T> setResult(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
        this.data = data;
        return this;
    }

    public static <T> ApiResult<T> build(RestResult<T> restResult) {
        ApiResult<T> result = new ApiResult<>();
        result.setCode(restResult.getCode());
        result.setMsg(restResult.getMsg());
        result.setData(restResult.getData());
        return result;
    }

    public static <T> ApiResult<T> build(ServiceResult<T> serviceResult) {
        ApiResult<T> result = new ApiResult<>();
        result.setCode(serviceResult.getCode());
        result.setMsg(serviceResult.getMsg());
        result.setData(serviceResult.getData());
        return result;
    }


    public static <T> ApiResult<T> ok(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setResult(ResultEnum.OK, data);
        return result;
    }

    public static <T> ApiResult<T> ok() {
        return ok(null);
    }


    public static <T> ApiResult<T> fail(String msg) {
        ApiResult<T> result = new ApiResult<>();
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> ApiResult<T> fail() {
        return fail(ResultEnum.FAIL.getMsg());
    }


    public static <T> ApiResult<T> paramsError(String msg) {
        ApiResult<T> result = new ApiResult<>();
        result.setResult(ResultEnum.PARAMS_ERROR);
        result.setMsg(msg);
        return result;
    }

    public static <T> ApiResult<T> build(ResultEnum resultEnum) {
        ApiResult<T> result = new ApiResult<>();
        result.setResult(resultEnum);
        return result;
    }


}