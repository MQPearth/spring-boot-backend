package com.thy.backend.user.service.user.web.service.user.impl;

import com.thy.backend.parent.framework.mp.SuperServiceImpl;
import com.thy.backend.parent.user.po.po.UserProfilePO;
import com.thy.backend.user.service.user.web.dao.UserProfileDAO;
import com.thy.backend.user.service.user.web.service.user.UserProfileService;
import org.springframework.stereotype.Service;

/**
 * <p>UserProfileServiceImpl</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/27 13:55:24
 */
@Service
public class UserProfileServiceImpl extends SuperServiceImpl<UserProfileDAO, UserProfilePO>
        implements UserProfileService {

}
