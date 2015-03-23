package com.mockito.test.testability;

import com.mockito.test.unfavourable.TestingImpedimentException;

public class FinalDependency {

	public final void doSomething() {
		throw new TestingImpedimentException("Final methods cannot be overriden");
	}	
	
}
