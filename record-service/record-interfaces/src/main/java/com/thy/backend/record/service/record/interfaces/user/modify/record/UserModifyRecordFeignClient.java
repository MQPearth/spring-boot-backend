package com.thy.backend.record.service.record.interfaces.user.modify.record;

import com.thy.backend.record.service.record.common.PathCons;
import com.thy.backend.record.service.record.common.feign.user.modify.UserModifyRecordFeign;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>UserModifyRecordFeignClient</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/5/22 09:41:11
 */
@FeignClient(name = "${web.feign.record}", contextId = "userModifyRecordFeignClient",
        path = PathCons.USER_MODIFY_RECORD_PATH)
public interface UserModifyRecordFeignClient extends UserModifyRecordFeign {
}
