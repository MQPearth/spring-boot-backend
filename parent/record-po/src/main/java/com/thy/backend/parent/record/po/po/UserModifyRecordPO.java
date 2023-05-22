package com.thy.backend.parent.record.po.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thy.backend.parent.base.po.BasePersistent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>UserProfilePO</p>
 *
 * @author thy
 * @version 1.0
 * @date 2023/4/25 16:47:28
 */
@TableName("user_modify_record")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserModifyRecordPO extends BasePersistent {

    @TableField("user_id")
    Long userId;

    @TableField("old_data")
    String oldData;

    @TableField("new_data")
    String newData;


}
