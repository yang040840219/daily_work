package com.m;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestFun {
	
	
	public static double sigmoid(int a){
		 
		double v = 1.0 / (1 + Math.exp(-a)) ;
		return v ;
		
	}
	
	public static void main(String []args) throws Exception{
		
		Map<String,Integer> map = new ConcurrentHashMap<String,Integer>();
		
		map.put("abc",1);
		System.out.println(map);
		int a = map.get("abc");
		System.out.println(map.containsKey("abc"));
		map.put("abc", a + 2 );
		System.out.println(map);
		
	}
}
