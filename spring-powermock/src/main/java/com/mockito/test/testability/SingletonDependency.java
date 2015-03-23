package com.mockito.test.testability;

import com.mockito.test.unfavourable.TestingImpedimentException;

public class SingletonDependency {

	private static SingletonDependency singletonDependency;
	
	private SingletonDependency() {
	
	}
	
	public synchronized static SingletonDependency getInstance() {
		if (singletonDependency == null) {
			singletonDependency = new SingletonDependency();
		}
		return singletonDependency;
	}
	
	
	/**
	 * 
	 * Mockito cannot stub a static method. The only way to overcome this issue is to create a protected method and wrap the
		static call. From the code, call the wrapped method, and from the test, override the
		protected method
	 * 
	 */	
	
	// Mockito cannot stub a static method	
	public static void callMe() {		
		throw new TestingImpedimentException("we dont need singleton");
	}	
	
	protected void wrapper() {
		callMe();
	}	
	
}
