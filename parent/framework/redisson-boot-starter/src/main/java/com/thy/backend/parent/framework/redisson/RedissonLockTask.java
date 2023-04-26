package com.thy.backend.parent.framework.redisson;


/**
 * RedissonLockTask
 *
 * @author zzx
 */
@FunctionalInterface
public interface RedissonLockTask {

    /**
     * <p>run</p>
     *
     * @throws Exception
     * @author zzx
     * @date 2023/4/26 9:54
     */
    void run() throws Exception;
}