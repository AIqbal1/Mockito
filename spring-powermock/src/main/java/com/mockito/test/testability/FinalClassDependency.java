package com.mockito.test.testability;

public class FinalClassDependency {

	private final FinalDepencyClass finalDepencyClass;

	public FinalClassDependency(FinalDepencyClass finalDepencyClass) {
		this.finalDepencyClass = finalDepencyClass;
	}

	public void testMe() {
		finalDepencyClass.poison();
	}

}
