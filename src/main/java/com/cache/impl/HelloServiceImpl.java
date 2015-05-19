package com.cache.impl;

import org.springframework.stereotype.Service;

import com.cache.data.Data;
import com.cache.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	@Override
	public String getMessage(String key) {
		String value = Data.getData(key);
		return  value ;
	}

	@Override
	public void setMessage(String key, String value) {
		Data.setData(key, value);
	}

}
