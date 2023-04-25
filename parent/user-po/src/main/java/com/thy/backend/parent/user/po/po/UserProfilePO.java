package com.thy.backend.parent.user.po.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thy.backend.parent.base.po.BasePersistent;
import com.thy.backend.parent.user.po.enums.UserProfileGenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>UserProfilePO</p>
 *
 * @author thy
 * @version 1.0
 * @date 2023/4/25 16:47:28
 */
@TableName("user_profile")
@EqualsAndHashCode(callSuper = true)
@Data
public class UserProfilePO extends BasePersistent {


    @TableField("url")
    String url;

    @TableField("trueName")
    String trueName;

    @TableField("gender")
    UserProfileGenderEnum gender;


}
