package com.thy.backend.parent.framework.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.thy.backend.parent.base.po.BasePersistent;
import com.thy.backend.parent.base.table.BasePersistentTableCons;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author root
 */
@Slf4j
public class MetaPersistentObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (BasePersistent.class.isAssignableFrom(metaObject.getOriginalObject().getClass())) {
                this.strictInsertFill(metaObject, BasePersistentTableCons.JAVA_FIELD_CREATE_TIME,
                        LocalDateTime.class, LocalDateTime.now());

                this.strictInsertFill(metaObject, BasePersistentTableCons.JAVA_FIELD_DELETED,
                        Boolean.class, false);
            }
        } catch (Exception e) {
            log.error("insertFill Exception", e);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (BasePersistent.class.isAssignableFrom(metaObject.getOriginalObject().getClass())) {
                metaObject.setValue(BasePersistentTableCons.JAVA_FIELD_MODIFY_TIME, LocalDateTime.now());
            }
        } catch (Exception e) {
            log.error("updateFill Exception", e);
        }
    }

}