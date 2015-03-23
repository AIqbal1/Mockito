package com.spring.maven.mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.maven.mockito.dummy.Student;
import com.spring.maven.mockito.spy.MethodInvocation;
import com.spring.maven.mockito.spy.StudentServiceSpy;

public class StudentServiceMockImpl {
	private Map<String, List<Student>> studentCouseMap = new HashMap<String, List<Student>>();
	
	
	private StudentServiceMockObject mock;
	
	public void setMock(StudentServiceMockObject mock) {
		this.mock = mock;
	}
	
	public void enrollToCourse(String courseName, Student student) {	
		
		MethodInvocation invocation = new MethodInvocation();
		invocation.addParam(courseName).addParam(student).setMethod("enrollToCourse");
		mock.registerCall(invocation);		
		
		List<Student> list = studentCouseMap.get(courseName);
		if (list == null) {
			list = new ArrayList<Student>();
		}
		if (!list.contains(student)) {
			list.add(student);
		}
		studentCouseMap.put(courseName, list);
	}
}