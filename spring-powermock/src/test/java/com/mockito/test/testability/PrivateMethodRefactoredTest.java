package com.mockito.test.testability;

import org.junit.Before;
import org.junit.Test;


/**
 * 
 * The test code
invokes the validate() method on the new anonymous implementation of the
PrivateMethodRefactored class, and in turn, the polymorphic behavior calls the
empty implementation.

this approach of bypassing the testing impediments with overridden
versions of the testing impediments is known as faking or fake object.
 * 
 * 
 * @author asif.iqbal
 *
 */
public class PrivateMethodRefactoredTest {

	PrivateMethodRefactored privateMethod;

	@Before
	public void setUp() {
		privateMethod = new PrivateMethodRefactored() {
			protected void showError(String msg) {
			}
		};
	}

	
	@Test
	public void validate() throws Exception {
		privateMethod.validate(null);
	}

}
