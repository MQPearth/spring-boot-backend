package com.thy.backend.record.service.record.web.service.user.modify.impl;

import com.thy.backend.parent.base.result.ServiceResult;
import com.thy.backend.parent.framework.mp.SuperServiceImpl;
import com.thy.backend.parent.record.po.po.UserModifyRecordPO;
import com.thy.backend.record.service.record.web.dao.UserModifyRecordDAO;
import com.thy.backend.record.service.record.web.service.user.modify.UserModifyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>UserModifyRecordServiceImpl</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/5/22 09:58:11
 */
@Service
@Slf4j
public class UserModifyRecordServiceImpl extends SuperServiceImpl<UserModifyRecordDAO, UserModifyRecordPO>
        implements UserModifyRecordService {

    @Override
    public ServiceResult<List<UserModifyRecordPO>> getByUserId(Long id) {
        log.info("param: {}", id);
        return ServiceResult.ok(baseMapper.selectListByProperty("user_id", id));
    }
}
