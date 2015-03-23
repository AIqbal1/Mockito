package com.paclt.chap3;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
public class InlineStubbingTest {

	
	// The bar object is stubbed and created at the same time. This is useful when the bar
	// object is used in many test cases within the test class. The bar object should always
	// return a Tar object.
	
	Bar globalBar =  when(mock(Bar.class).getTar()).thenReturn(new Tar()).getMock();
	
	@Test
	public void when_stubbing_inline() throws Exception {
		assertNotNull(globalBar);
		assertNotNull(globalBar.getTar());
	}

}
