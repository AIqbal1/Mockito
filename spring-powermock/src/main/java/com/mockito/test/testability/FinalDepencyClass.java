package com.mockito.test.testability;

import com.mockito.test.unfavourable.TestingImpedimentException;

public final class FinalDepencyClass {
	
	public void poison() {
		throw new TestingImpedimentException("Finals cannot be mocked");
	}	
	
}
