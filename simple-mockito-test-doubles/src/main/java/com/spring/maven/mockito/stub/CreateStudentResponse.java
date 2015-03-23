package com.spring.maven.mockito.stub;

import com.spring.maven.mockito.dummy.Student;

public class CreateStudentResponse {

	private final String errorMessage;
	private final Student student;
	
	public CreateStudentResponse(String errorMessage, Student student) {
		this.errorMessage = errorMessage;
		this.student = student;
	}	
	
	public boolean isSuccess(){
		return null == errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public Student getStudent() {
		return student;
	}	
}
