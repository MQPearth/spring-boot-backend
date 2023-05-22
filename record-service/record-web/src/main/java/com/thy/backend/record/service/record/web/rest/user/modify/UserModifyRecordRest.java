package com.thy.backend.record.service.record.web.rest.user.modify;

import com.thy.backend.parent.base.result.RestResult;
import com.thy.backend.parent.record.po.po.UserModifyRecordPO;
import com.thy.backend.record.service.record.common.PathCons;
import com.thy.backend.record.service.record.common.feign.user.modify.UserModifyRecordFeign;
import com.thy.backend.record.service.record.web.service.user.modify.UserModifyRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>UserModifyRecordRest</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/5/22 10:05:13
 */
@Slf4j
@RequestMapping(PathCons.USER_MODIFY_RECORD_PATH)
@RestController
public class UserModifyRecordRest implements UserModifyRecordFeign {

    @Autowired
    UserModifyRecordService userModifyRecordService;

    @Override
    public RestResult<List<UserModifyRecordPO>> getByUserId(Long id) {
        return RestResult.build(userModifyRecordService.getByUserId(id));
    }
}
