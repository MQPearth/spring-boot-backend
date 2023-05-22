package com.thy.backend.api.user.api.controller;

import com.thy.backend.parent.base.result.ApiResult;
import com.thy.backend.parent.record.po.po.UserModifyRecordPO;
import com.thy.backend.record.service.record.interfaces.user.modify.record.UserModifyRecordFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>UserModifyRecordController</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/5/22 17:57:34
 */
@Slf4j
@RestController
@RequestMapping("/user/modify/record")
public class UserModifyRecordController {


    @Autowired
    UserModifyRecordFeignClient userModifyRecordFeignClient;


    @GetMapping("/getByUserId")
    public ApiResult<List<UserModifyRecordPO>> getByUserId(Long id) {
        return ApiResult.build(userModifyRecordFeignClient.getByUserId(id));
    }

}
