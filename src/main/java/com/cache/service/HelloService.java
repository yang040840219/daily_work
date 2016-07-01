package com.cache.service;

import com.cache.data.Person;



public interface HelloService {
	
    public String getMessage(String key);
	
	
	public void setMessage(String key,String value);
	
	
	public void setPerson(Person person);
	
	public Person getPerson(Integer id);
}
