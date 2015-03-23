package com.mockito.test.testability;

public class VictimOfAPatternLover {

	private final SingletonDependency dependency;

	public VictimOfAPatternLover(SingletonDependency dependency) {
		this.dependency = dependency;
	}

	
	public void testMe() {
		// Mockito cannot stub a static method
		// dependency.callMe();
		
		// call protected instead
		
		dependency.wrapper();
	}
	


}
