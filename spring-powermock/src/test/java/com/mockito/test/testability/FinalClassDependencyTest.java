package com.mockito.test.testability;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/*
 * Final classes are important for framework or architecture design so that no one can
hack the behavior, but it can create a serious problem for unit testing. Before you
choose to make a class final, ensure that your final class is a final implementation of
some interface so that other clients of the class can potentially use stubbed instances
of the interface.
 * 
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class FinalClassDependencyTest {

	@Mock
	FinalDepencyClass finalDependency;

	FinalClassDependency test;

	@Before
	public void setUp() {
		test = new FinalClassDependency(finalDependency);
	}

	@Test
	public void testMe() throws Exception {
		Mockito.doNothing().when(finalDependency).poison();
		test.testMe();
	}
}
