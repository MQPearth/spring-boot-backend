package com.thy.backend.parent.framework.web;

import com.thy.backend.parent.framework.web.config.CustomModuleConfig;
import com.thy.backend.parent.framework.web.config.WebMvcConfig;
import org.springframework.context.annotation.Import;

/**
 * <p>WebConfiguration</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 12:31:34
 */
@Import({CustomModuleConfig.class, WebMvcConfig.class})
public class WebConfiguration {


}
