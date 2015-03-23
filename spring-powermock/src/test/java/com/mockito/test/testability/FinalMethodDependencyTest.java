package com.mockito.test.testability;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FinalMethodDependencyTest {

	// the mock object cannot stub a final method
	// this throws errors
	@Mock
	FinalDependency finalDependency;
	
	FinalMethodDependency methodDependency;	
	
	@Before
	public void setUp() {
		methodDependency = new FinalMethodDependency(finalDependency);
	}
	
	@Test
	public void testSomething() throws Exception {
		methodDependency.testMe();
	}	
	
}
