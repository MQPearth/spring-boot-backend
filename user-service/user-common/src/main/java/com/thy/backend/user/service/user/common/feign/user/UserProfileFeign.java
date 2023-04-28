package com.thy.backend.user.service.user.common.feign.user;

import com.thy.backend.parent.base.result.RestResult;
import com.thy.backend.parent.user.po.po.UserProfilePO;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>UserProfileFeign</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/27 10:36:41
 */
public interface UserProfileFeign {

    @GetMapping("/test")
    RestResult<UserProfilePO> test();
}
