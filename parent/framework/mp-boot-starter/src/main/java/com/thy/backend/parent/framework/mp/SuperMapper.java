package com.thy.backend.parent.framework.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thy.backend.parent.base.table.BasePersistentTableCons;

import java.io.Serializable;
import java.util.List;

/**
 * @author root
 */
public interface SuperMapper<T> extends BaseMapper<T>, Serializable {

    /**
     * 逻辑 删除字段
     */
    String LOGIC_FIELD = BasePersistentTableCons.DB_FIELD_DELETED;

    /**
     * 未逻辑删除的对应值
     */
    boolean LOGIC_NOT_DELETE_VALUE = false;


    /**
     * 构建查询条件
     *
     * @param logic  logic
     * @param params 参数
     * @return <T>
     * @author zzx
     */
    private QueryWrapper<T> buildCondition(boolean logic, Object... params) {
        if (params.length % 2 != 0) {
            throw new IllegalArgumentException("字段和参数值需要成对出现");
        }
        QueryWrapper<T> qw = new QueryWrapper<>();

        for (int i = 0; i < params.length; i += 2) {
            qw.eq(String.valueOf(params[i]), params[i + 1]);
        }

        if (logic) {
            qw.eq(LOGIC_FIELD, LOGIC_NOT_DELETE_VALUE);
        }
        return qw;
    }

    /**
     * 根据多个属性等值查询一条记录
     *
     * @param params 参数
     * @return <T>
     * @author zzx
     */
    default T selectOneByProperty(Object... params) {
        return selectOneByPropertyLogic(true, params);
    }

    /**
     * 根据多个属性等值查询一条记录
     *
     * @param params 参数
     * @param logic  是否开启逻辑字段过滤
     * @return <T>
     * @author zzx
     */
    default T selectOneByPropertyLogic(boolean logic, Object... params) {
        return selectOne(buildCondition(logic, params));
    }


    /**
     * 根据多个属性等值查询多条记录
     *
     * @param params 参数
     * @return <T>
     * @author zzx
     */
    default List<T> selectListByProperty(Object... params) {
        return selectListByPropertyLogic(true, params);
    }

    /**
     * 根据多个属性等值查询多条记录
     *
     * @param params 参数
     * @param logic  是否开启逻辑字段过滤
     * @return <T>
     * @author zzx
     */
    default List<T> selectListByPropertyLogic(boolean logic, Object... params) {
        return selectList(buildCondition(logic, params));
    }

}
