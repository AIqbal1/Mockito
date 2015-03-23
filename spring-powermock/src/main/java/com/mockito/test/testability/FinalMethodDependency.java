package com.mockito.test.testability;

public class FinalMethodDependency {

	private final FinalDependency dependency;
	
	public FinalMethodDependency(FinalDependency dependency) {
		this.dependency = dependency;
	}
	
	public void testMe() {
		dependency.doSomething();
	}	
	
}
