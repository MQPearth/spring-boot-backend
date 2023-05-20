package com.thy.backend.api.user.api.controller;

import com.thy.backend.parent.base.result.ApiResult;
import com.thy.backend.parent.user.po.po.UserProfilePO;
import com.thy.backend.user.service.user.interfaces.user.UserProfileFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>UserProfileController</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/27 17:57:34
 */
@Slf4j
@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

    @Autowired
    UserProfileFeignClient userProfileFeignClient;


    @GetMapping("/test")
    public ApiResult<UserProfilePO> test() {
        log.error("user-api error");
        return ApiResult.build(userProfileFeignClient.test());
    }

}
