package com.mockito.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;


// It allows us to create class
// instances by suppressing the defined constructors; but the problem is that the
// values we initialize in the constructor are just ignored, or rather, not initialized

@RunWith(PowerMockRunner.class)
public class SuppressConstructorTest {

	@Test
	public void supresses_own_constructor() throws Exception {
		SuppressConstructor nasty = Whitebox.newInstance(SuppressConstructor.class);
		assertNotNull(nasty);
		System.out.println(nasty.someValue);
	}
	
}
