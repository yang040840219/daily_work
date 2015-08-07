package com.seria;

import java.io.Serializable;

public class Person implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5030264321831777056L;
	
	
	private Student student ;
	
	public Person(){
		student = new Student("123");
	}
	
	public String printStudentName(){
		return student.getName();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
