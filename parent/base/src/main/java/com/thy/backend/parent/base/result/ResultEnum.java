package com.thy.backend.parent.base.result;

import lombok.Getter;

/**
 * @author root
 */
public enum ResultEnum {

    /**
     * ok
     */
    OK("0", "ok"),

    /**
     * 账号或密码错误
     */
    LOGIN_ACCOUNT_PWD_ERROR("0001", "账号或密码错误"),

    /**
     * 资源不存在
     */
    RESOURCE_NOT_EXIST_ERROR("0002", "资源不存在"),

    /**
     * 参数错误
     */
    PARAMS_ERROR("0003", "参数错误"),


    /**
     * 请求方式错误
     */
    REQUEST_ERROR("0004", "请求方式错误"),


    /**
     * 微信appid错误
     */
    WECHAT_APPID_ERROR("0005", "微信appid错误"),


    /**
     * 微信更新信息失败
     */
    WECHAT_UPDATE_INFO_ERROR("0006", "微信更新信息失败"),


    /**
     * 微信认证失败
     */
    WECHAT_AUTH_ERROR("0007", "微信认证失败"),


    /**
     * 微信信息解密失败
     */
    WECHAT_INFO_DECRYPT_ERROR("0008", "微信信息解密失败"),


    /**
     * 服务不可用
     */
    SERVICE_ERROR("9998", "服务不可用"),

    /**
     * fail
     */
    FAIL("9999", "fail");


    @Getter
    private final String code;


    @Getter
    private final String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
