package com.map;

import java.lang.ref.WeakReference;

public class TestWeakReference {

	 public static void main(String[] args) throws InterruptedException{  
		 
		 Student student = new Student();
		 
		 //student.setName("abc");
		 
         WeakReference<Student> weakRef=new WeakReference<Student>(new Student());  
		 //WeakReference<String> weakRef=new WeakReference<String>(new String("abc"));  
         System.out.println(weakRef.get());  
         System.gc();  
         Thread.sleep(2000);  
         System.out.println(weakRef.get());  
     }  
	
}
