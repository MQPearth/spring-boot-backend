package com.thy.backend.parent.base.result;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author root
 */
public interface BaseResult<T> {

    /**
     * 是否成功
     *
     * @return bool
     */
    @JsonIgnore
    boolean isOk();

    /**
     * 是否失败
     *
     * @return bool
     */
    @JsonIgnore
    default boolean isFail() {
        return !isOk();
    }

    /**
     * 获取携带的数据
     *
     * @return T
     */
    T getData();

    /**
     * 响应消息
     *
     * @return msg
     */
    String getMsg();

    /**
     * 响应码
     *
     * @return code
     */
    String getCode();

    /**
     * 设置响应
     *
     * @param resultEnum 响应
     * @param data       数据
     * @return BaseResult
     */
    BaseResult<T> setResult(ResultEnum resultEnum, T data);


    /**
     * 设置响应
     *
     * @param resultEnum 响应
     * @return BaseResult
     */
    default BaseResult<T> setResult(ResultEnum resultEnum) {
        return setResult(resultEnum, null);
    }
}