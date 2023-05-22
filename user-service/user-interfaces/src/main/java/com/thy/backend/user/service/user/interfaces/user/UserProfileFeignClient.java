package com.thy.backend.user.service.user.interfaces.user;

import com.thy.backend.user.service.user.common.PathCons;
import com.thy.backend.user.service.user.common.feign.user.UserProfileFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author root
 */
@FeignClient(name = "${web.feign.user}", contextId = "userProfileFeignClient", path = PathCons.USER_PROFILE_PATH)
public interface UserProfileFeignClient extends UserProfileFeign {

}
