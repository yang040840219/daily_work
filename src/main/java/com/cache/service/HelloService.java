package com.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface HelloService {
	
	@Cacheable(value ="message", key="#key")
    public String getMessage(String key);
	
	
	@CacheEvict(value="message",key="#key")
	public void setMessage(String key,String value);
	
}
