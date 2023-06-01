package com.thy.backend.api.user.api.controller;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.thy.backend.parent.base.result.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>IPController</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/6/1 08:44:07
 */
@Slf4j
@RestController
@RequestMapping("/ip")
public class IPController {

    @GetMapping("/get")
    public ApiResult<String> get(HttpServletRequest req) {
        String ip = JakartaServletUtil.getClientIP(req);
        log.info("ip: {}", ip);
        return ApiResult.ok(ip);
    }

}
