package com.seria;

import java.io.Serializable;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1327451101391947995L;
	
	private String name ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Student(String name) {
		System.out.println("创建 Student");
		this.name = name;
	}
	
	
	
	
  
}
