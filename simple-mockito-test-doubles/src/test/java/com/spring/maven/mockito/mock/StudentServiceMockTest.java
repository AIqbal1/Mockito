package com.spring.maven.mockito.mock;

import static org.junit.Assert.*;

import org.junit.Test;

import com.spring.maven.mockito.dummy.Student;

public class StudentServiceMockTest {
	
	StudentServiceMockImpl service = new StudentServiceMockImpl();
	StudentServiceMockObject mockObject = new StudentServiceMockObject();
	
	@Test	
	public void enrolls_students() throws Exception {
		//create 2 students
		Student bob = new Student("001", "Robert Anthony");
		Student roy = new Student("002", "Roy Noon");
		//set mock/spy
		service.setMock(mockObject);
		//invoke method twice
		service.enrollToCourse("english", bob);
		service.enrollToCourse("history", roy);
		//assert that the method was invoked twice
		assertEquals(2, mockObject.invocation("enrollToCourse"));
		//verify wrong information, that enrollToCourse was
		//invoked once, but actually it is invoked twice
		mockObject.verify("enrollToCourse", 2);
	}
		
}
