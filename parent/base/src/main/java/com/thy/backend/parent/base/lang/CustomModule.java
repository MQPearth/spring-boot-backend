package com.thy.backend.parent.base.lang;

import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Data;

/**
 * <p>CustomModule</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 11:17:57
 */
@Data
public class CustomModule {

    private SimpleModule simpleModule;

    public static CustomModule build(SimpleModule simpleModule) {
        CustomModule customModule = new CustomModule();
        customModule.simpleModule = simpleModule;
        return customModule;
    }
}
