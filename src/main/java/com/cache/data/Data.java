package com.cache.data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Data {
   
	private static Map<String,String> map = new HashMap<String,String>();
	
	private static Map<Integer,Person> persons = new HashMap<Integer,Person>();
	
	static {
		map.put("a","123");
		map.put("b", "456");
	}
	
	public static String getData(String key){
		System.out.println("read from map");
		return map.get(key) ;
	}
	
	public static void setData(String key,String value){
		map.put(key, value);
	}
	
	
	public static Person getPerson(Integer id){
		System.out.println("read from map");
		return persons.get(id);
	}
	
	public static void setPerson(Person person){
		persons.put(person.getId(), person);
	}
}
