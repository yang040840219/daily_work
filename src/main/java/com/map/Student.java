package com.map;

public class Student {
	
	private String id ;
	private String name ;
	private Integer age ;
    private byte[] bytes;  

	
	
	public Student(){} 
	
	public Student(String id,Integer age){
		 this.id = id ;
		 this.age = age ;
		 this.bytes=new byte[1024*1024*200]; //200M 
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
