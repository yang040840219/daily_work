package com.cache.impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.cache.data.Data;
import com.cache.data.Person;
import com.cache.service.HelloService;

@Service
public class HelloServiceImpl implements HelloService {

	@Cacheable(value={"HelloService"},key="#key+"+"'_'"+"+#key")
	@Override
	public String getMessage(String key) {
		String value = Data.getData(key);
		return  value ;
	}

	@CacheEvict(value={"HelloService"},key="#key+"+"'_'"+"+#key")
	@Override
	public void setMessage(String key, String value) {
		Data.setData(key, value);
	}

	@CacheEvict(value={"setPerson"},key="'person_'"+"+#person.id")
	@Override
	public void setPerson(Person person) {
		 Data.setPerson(person);
	}

	@Cacheable(value={"getPerson"},key="'person_'"+"+#id")
	@Override
	public Person getPerson(Integer id) {
		return Data.getPerson(id);
	}
	
	
	
	

}
