package com.thy.backend.parent.base.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thy.backend.parent.base.cons.BasePersistentCons;
import com.thy.backend.parent.base.table.BasePersistentTableCons;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>BasePersistent</p>
 *
 * @author thy
 * @version 1.0
 * @date 2023/4/25 16:03:50
 */
@Data
public class BasePersistent implements Serializable {

    @Serial
    private static final long serialVersionUID = -2439790319722696658L;

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @TableField(value = BasePersistentTableCons.DB_FIELD_CREATE_TIME, fill = FieldFill.INSERT)
    @JsonFormat(timezone = BasePersistentTableCons.DEFAULT_TIMEZONE,
            pattern = BasePersistentTableCons.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = BasePersistentTableCons.DB_FIELD_MODIFY_TIME, fill = FieldFill.UPDATE)
    @JsonFormat(timezone = BasePersistentTableCons.DEFAULT_TIMEZONE,
            pattern = BasePersistentTableCons.YYYY_MM_DD_HH_MM_SS)
    private LocalDateTime modifyTime;

    /**
     * 创建人
     */
    @TableField(BasePersistentTableCons.DB_FIELD_CREATOR)
    private Long creator;

    /**
     * 修改人
     */
    @TableField(BasePersistentTableCons.DB_FIELD_MODIFIER)
    private Long modifier;

    /**
     * 是否删除
     */
    @TableLogic
    @TableField(value = BasePersistentTableCons.DB_FIELD_DELETED, fill = FieldFill.INSERT)
    private Boolean deleted;


    public static <P extends BasePersistent> P fillBase(Long creator, P p) {
        p.setCreator(creator);
        p.setModifier(creator);
        return p;
    }

    /**
     * 当可以获取到操作人时, 必须使用fillBase(int, P)方法
     *
     * @param p   子类对象
     * @param <P> 实体类
     * @return 子类实体对象
     */
    public static <P extends BasePersistent> P fillBase(P p) {
        return BasePersistent.fillBase(BasePersistentCons.DEFAULT_CREATOR_MODIFIER, p);
    }
}
