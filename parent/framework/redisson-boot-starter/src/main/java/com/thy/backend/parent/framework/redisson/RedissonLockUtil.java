package com.thy.backend.parent.framework.redisson;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>RedissonLockUtil</p>
 *
 * @author zzx
 * @version 1.0
 * @ClassName RedissonLockUtil.java
 * @date 2022/2/17 9:38
 */
@Slf4j
public class RedissonLockUtil {

    @Getter
    @Setter
    private RedissonClient redissonClient;


    public boolean fairLock(String lockKey, RedissonLockTask runnable) throws Exception {
        return fairLock(lockKey, 3, TimeUnit.SECONDS, runnable);
    }

    /**
     * 基于redisson#fairLock的封装
     *
     * @param lockKey
     * @param waitTime
     * @param unit
     * @param task     获取到锁后执行的任务
     * @return 是否获取到锁
     * @throws Exception
     */
    public boolean fairLock(String lockKey, int waitTime, TimeUnit unit, RedissonLockTask task)
            throws Exception {
        RLock lock = null;
        boolean isLock = false;
        try {
            lock = redissonClient.getFairLock(lockKey);
            isLock = lock.tryLock(waitTime, unit);
            if (!isLock) {
                return false;
            }
            task.run();
        } catch (Exception e) {
            log.error("fairLock执行失败, lockKey: {}", lockKey, e);
            throw e;
        } finally {
            if (Objects.nonNull(lock) && lock.isLocked() && isLock) {
                lock.unlock();
            }
        }
        return true;
    }
}
