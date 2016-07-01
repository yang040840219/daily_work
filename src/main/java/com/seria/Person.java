package com.seria;

import java.io.Serializable;

public class Person implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5030264321831777056L;
	
	private transient Student student ;
	
	public Person(){
		System.out.println("创建 Person");
			student = new Student("123");
	}
	
	public String printStudentName(){
		return student.getName();
	}

	public Student getStudent() {
		if(student == null){
			student = new Student("123");
		}
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
