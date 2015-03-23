package com.spring.maven.mockito.dummy;

public class DummyStudent extends Student {

	public DummyStudent(String roleNumber, String name) {
		super(roleNumber, name);
		// TODO Auto-generated constructor stub
	}
	
	public DummyStudent() {
		super(null, null);		
	}

	@Override
	public String getRoleNumber() {
		throw new RuntimeException("Dummy student");
	}

	@Override
	public String getName() {
		throw new RuntimeException("Dummy student");
	}	

}
