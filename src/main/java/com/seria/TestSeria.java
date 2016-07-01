package com.seria;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class TestSeria {
	
	
	public static void main(String [] args){
		 try
	        {
			   //输出流保存的文件名为 my.out ；ObjectOutputStream能把Object输出成Byte流
	            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("my.out"));
	            Student s = new Student("a");
	            Person p = new Person();
	            p.setStudent(s);
	            oos.writeObject(p); 
	            oos.flush(); 
	            oos.close();
	        } catch (Exception e) 
	        {        
	            e.printStackTrace();
	        } 
		 
		 System.out.println("----------------------------------------");
		 ObjectInputStream oin = null;
	        try
	        {
	            oin = new ObjectInputStream(new FileInputStream("my.out"));
	        } catch (Exception e)
	        {        
	            e.printStackTrace();
	        } 
	        Person mts = null;
	        try {
	            mts = (Person) oin.readObject();
	            System.out.println(mts.getStudent().getName());
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }     
		
	}

}
