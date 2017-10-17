package com.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestSoftReference {
	
	 public static void main(String[] args) throws InterruptedException, IOException{  
	        System.out.println("***************");  
	        System.out.println("0:使用软引用，1:启用强引用");  
	        System.out.println("***************");  
	        System.out.println("请输入启用的缓存模式:");  
	        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));  
	        String mode=reader.readLine();  
	        BufferStudent buffer=new BufferStudent(Integer.parseInt(mode));  
	         
	        for(int i=0;i<100;i++){  
	            buffer.put(new Student(i+"",i));  
	            System.out.println("已经加入:"+i);  
	        }  
	        System.out.println(buffer.size());  
	   }  

}
