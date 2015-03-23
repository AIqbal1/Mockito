package com.mockito.test.unfavourable;


public class DatabaseDependency {
	
	public DatabaseDependency() {
		throw new TestingImpedimentException("calls database");
	}
}
