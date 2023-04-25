package com.thy.backend.parent.user.po.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * <p>UserProfileGenderEnum</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/25 16:58:46
 */
public enum UserProfileGenderEnum {
    /**
     * 未知
     */
    UN_KNOW(0, "未知"),
    /**
     * 男性
     */
    MALE(1, "男性"),
    /**
     * 女性
     */
    FEMALE(2, "女性");

    @EnumValue
    @Getter
    private final int code;

    @Getter
    private final String desc;

    UserProfileGenderEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    @Override
    public String toString() {
        return Integer.toString(code);
    }
}
