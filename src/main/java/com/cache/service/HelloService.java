package com.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;


public interface HelloService {
	
	@Cacheable(value={"HelloService"},key="#key+"+"'_'"+"+#key")
    public String getMessage(String key);
	
	
	@CacheEvict(value={"HelloService"},key="#key+"+"'_'"+"+#key")
	public void setMessage(String key,String value);
	
}
