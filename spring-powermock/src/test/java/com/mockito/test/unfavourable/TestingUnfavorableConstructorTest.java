package com.mockito.test.unfavourable;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * If we want to unit test the testMe() behavior of the class, we need to create an object
for the TestingUnfavorableConstructor class. However, when we try to create an
instant in the unit test, the class fails to indicate that the class cannot be instantiated
from an automated test suite
 * 
 * 
 * @author asif.iqbal
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class TestingUnfavorableConstructorTest {

	@Mock 
	DatabaseDependency dep1;
	@Mock 
	FileReadDependency dep2;
	
	TestingUnfavorableConstructor unfavorableConstructor;
	
	@Before 
	public void setUp() {
		unfavorableConstructor= new TestingUnfavorableConstructor() {
			protected void createDependencies() {
			}
		};
		unfavorableConstructor.setDependency1(dep1);
		unfavorableConstructor.setDependency2(dep2);
	}
	
	@Test 
	public void sanity() throws Exception {
	}
		
	
}
