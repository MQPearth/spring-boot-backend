package com.thy.backend.parent.framework.mp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thy.backend.parent.base.po.BasePersistent;

/**
 * <p>SuperServiceImpl</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/27 13:56:09
 */
public abstract class SuperServiceImpl<M extends SuperMapper<P>, P extends BasePersistent>
        extends ServiceImpl<M, P> {

}
