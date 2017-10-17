package com.util;

import java.io.Serializable;

import org.springframework.data.redis.core.ValueOperations;

public class RedisLock {

	private final static int DEFAULTEXPIRE = 15 * 60 * 1000 ;
	
	public static boolean getLock(ValueOperations<String,Serializable> valueOperations,String lockKey){
		long currentMills = System.currentTimeMillis() ;
		try{
			boolean flag = valueOperations.setIfAbsent(lockKey,currentMills); // 调用reids setnx
			if(!flag){
				Object lockValue = valueOperations.get(lockKey);
				if(lockValue == null){
					return valueOperations.setIfAbsent(lockKey,currentMills) ; // 没有 value , 重新执行
				}
				long longLockValue = (long)(lockValue); // 锁的当前值
				boolean isExpire = currentMills - longLockValue > DEFAULTEXPIRE ;// 没有获取到锁，判断超时
				if(isExpire){
					Object oldLockValue = valueOperations.getAndSet(lockKey,currentMills) ;
					if(oldLockValue == null){
						return valueOperations.setIfAbsent(lockKey,currentMills) ; // 尝试获取锁
					}
					long longOldLockValue = (long)(oldLockValue);
					if(longLockValue == longOldLockValue){ // 锁超时,判断getset 值,但获取到锁
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}
			return flag;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static boolean releaseLock(ValueOperations<String,Serializable> valueOperations,String lockKey){
		try{
			valueOperations.getOperations().delete(lockKey);
			return true ;
		}catch(Exception e){
			return false;
		}
	}
	
}
