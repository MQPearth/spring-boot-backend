package com.thy.backend.parent.user.po.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.thy.backend.parent.base.enums.CodeEnum;
import lombok.Getter;

/**
 * <p>UserProfileGenderEnum</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/25 16:58:46
 */
@Getter
public enum UserProfileGenderEnum implements CodeEnum {
    /**
     * 未知
     */
    UN_KNOW(0, "未知"),
    /**
     * 男
     */
    MALE(1, "男"),
    /**
     * 女
     */
    FEMALE(2, "女");

    @EnumValue
    private final int code;

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
