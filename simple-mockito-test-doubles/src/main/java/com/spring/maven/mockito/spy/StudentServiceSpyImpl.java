package com.spring.maven.mockito.spy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spring.maven.mockito.dummy.Student;

public class StudentServiceSpyImpl {
	private Map<String, List<Student>> studentCouseMap = new HashMap<String, List<Student>>();
	
	private StudentServiceSpy spy;
	public void setSpy(StudentServiceSpy spy) {
		this.spy = spy;
	}
	
	public void enrollToCourse(String courseName, Student student) {
		
		MethodInvocation invocation = new MethodInvocation();
		invocation.addParam(courseName).addParam(student).setMethod("enrollToCourse");
		spy.registerCall(invocation);		
		
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