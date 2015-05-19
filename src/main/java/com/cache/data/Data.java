package com.cache.data;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Data {
   
	private static Map<String,String> map = new HashMap<String,String>();
	
	static {
		map.put("a","123");
		map.put("b", "456");
	}
	
	public static String getData(String key){
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return map.get(key) ;
	}
	
	public static void setData(String key,String value){
		map.put(key, value);
	}
	
}
