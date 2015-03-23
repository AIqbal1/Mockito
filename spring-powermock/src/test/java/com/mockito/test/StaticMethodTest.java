package com.mockito.test;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MedicalBill.class)
public class StaticMethodTest {
		
	@Test
	public void stub_static_methods() throws Exception {
		System.out.println(MedicalBill.generateId());
		// enable mocking
		mockStatic(MedicalBill.class);
		// stub the static method
		// This method allows us to mock static methods.
		PowerMockito.when(MedicalBill.generateId()).thenReturn(1);
		// check the stubbed value
		assertEquals(1, MedicalBill.generateId());
	}

}
