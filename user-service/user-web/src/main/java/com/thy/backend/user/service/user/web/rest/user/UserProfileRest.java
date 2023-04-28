package com.thy.backend.user.service.user.web.rest.user;

import com.thy.backend.parent.base.result.RestResult;
import com.thy.backend.parent.user.po.po.UserProfilePO;
import com.thy.backend.user.service.user.common.feign.user.UserProfileFeign;
import com.thy.backend.user.service.user.web.service.user.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>UserProfileRest</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/27 10:46:01
 */
@RequestMapping("/user-profile")
@RestController
public class UserProfileRest implements UserProfileFeign {

    @Autowired
    UserProfileService userProfileService;

    @Override
    public RestResult<UserProfilePO> test() {
        return RestResult.ok(userProfileService.getById(1));
    }
}
