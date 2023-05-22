package com.thy.backend.record.service.record.web.service.user.modify;

import com.thy.backend.parent.base.result.ServiceResult;
import com.thy.backend.parent.framework.mp.SuperService;
import com.thy.backend.parent.record.po.po.UserModifyRecordPO;

import java.util.List;

/**
 * <p>UserModifyRecordService</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/5/22 09:56:12
 */
public interface UserModifyRecordService extends SuperService<UserModifyRecordPO> {
    ServiceResult<List<UserModifyRecordPO>> getByUserId(Long id);
}
