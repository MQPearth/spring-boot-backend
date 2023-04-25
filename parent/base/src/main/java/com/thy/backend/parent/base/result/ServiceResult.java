package com.thy.backend.parent.base.result;

import lombok.Data;

/**
 * @author root
 */
@Data
public final class ServiceResult<T> implements BaseResult<T> {

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


    public static <T> ServiceResult<T> ok(T data) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setResult(ResultEnum.OK, data);
        return result;
    }

    public static <T> ServiceResult<T> ok() {
        return ok(null);
    }


    public static <T> ServiceResult<T> fail(String msg) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setCode(ResultEnum.FAIL.getCode());
        result.setMsg(msg);
        return result;
    }

    public static <T> ServiceResult<T> fail() {
        return fail(ResultEnum.FAIL.getMsg());
    }


    public static <T> ServiceResult<T> build(ResultEnum resultEnum) {
        ServiceResult<T> result = new ServiceResult<>();
        result.setResult(resultEnum);
        return result;
    }


}
