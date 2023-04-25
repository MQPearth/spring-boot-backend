package com.thy.backend.parent.base.result;

import lombok.Data;

@Data
public class RestResult<T> implements BaseResult<T> {

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

    public static <T> RestResult<T> build(ResultEnum resultEnum, T data) {
        RestResult<T> result = new RestResult<>();
        result.setResult(resultEnum, data);
        return result;
    }

    public static <T> RestResult<T> build(ResultEnum resultEnum) {
        RestResult<T> result = new RestResult<>();
        result.setResult(resultEnum);
        return result;
    }

    public static <T> RestResult<T> build(ServiceResult<T> serviceResult) {
        RestResult<T> result = new RestResult<>();
        result.setCode(serviceResult.getCode());
        result.setMsg(serviceResult.getMsg());
        result.setData(serviceResult.getData());
        return result;
    }

    public static <T> RestResult<T> ok(T data) {
        RestResult<T> result = new RestResult<>();
        result.setResult(ResultEnum.OK, data);
        return result;
    }

    public static <T> RestResult<T> ok() {
        return ok(null);
    }


    public static <T> RestResult<T> fail(String msg) {
        RestResult<T> result = new RestResult<>();
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> RestResult<T> fail() {
        return build(ResultEnum.SERVICE_ERROR);
    }


}
