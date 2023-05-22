package com.thy.backend.record.service.record.common.feign.user.modify;

import com.thy.backend.parent.base.result.RestResult;
import com.thy.backend.parent.record.po.po.UserModifyRecordPO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>UserModifyRecordFeign</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/5/22 09:31:06
 */
public interface UserModifyRecordFeign {

    @GetMapping("/getByUserId")
    RestResult<List<UserModifyRecordPO>> getByUserId(@RequestParam("id") Long id);
}
