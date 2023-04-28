package com.thy.backend.parent.base.enums;

/**
 * @author root
 */
public interface CodeEnum {
    /**
     * 由枚举实现
     *
     * @return 枚举类的code字段
     */
    int getCode();

    /**
     * 由枚举实现
     *
     * @return 枚举类的desc字段
     */
    String getDesc();


    default String getCodePropertyName() {
        return "code";
    }

    default String getDescPropertyName() {
        return "desc";
    }
}