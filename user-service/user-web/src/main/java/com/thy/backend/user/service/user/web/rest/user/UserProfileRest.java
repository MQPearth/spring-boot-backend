package com.thy.backend.user.service.user.web.rest.user;

import com.thy.backend.parent.base.result.RestResult;
import com.thy.backend.parent.user.po.po.UserProfilePO;
import com.thy.backend.user.service.user.common.PathCons;
import com.thy.backend.user.service.user.common.feign.user.UserProfileFeign;
import com.thy.backend.user.service.user.web.service.user.UserProfileService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequestMapping(PathCons.USER_PROFILE_PATH)
@RestController
public class UserProfileRest implements UserProfileFeign {

    @Autowired
    UserProfileService userProfileService;

    @Override
    public RestResult<UserProfilePO> test() {
        log.error("test error", new RuntimeException("user-web"));
        return RestResult.ok(userProfileService.getById(1660481187938119700L));
    }
}
