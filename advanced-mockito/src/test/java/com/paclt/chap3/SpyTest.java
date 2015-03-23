package com.paclt.chap3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;


/*
 * 
 * Spy is also known as partial mock. The following is the declaration of spy:
 * 
 * SomeClass realObject = new RealImplemenation();
 * SomeClass spyObject = spy(realObject);
 * 
 * 
 */
public class SpyTest {

	@Test
	public void when_spying_real_objects() throws Exception {
		
		Error error = new Error();
		error.setErrorCode("Q123");
		Error spyError = Mockito.spy(error);
		
		//call real method from spy
		assertEquals("Q123", spyError.getErrorCode());
		//Changing value using spy
		spyError.setErrorCode(null);
		//verify spy has the changed value
		assertEquals(null, spyError.getErrorCode());
		
		//Stubbing method		
		Mockito.when(spyError.getErrorCode()).thenReturn("E456");
		//Changing value using spy
		spyError.setErrorCode(null);
		//Stubbed method value E456 is returned NOT NULL
		assertNotSame(null, spyError.getErrorCode());
		//Stubbed method value E456
		assertEquals("E456", spyError.getErrorCode());
		
		// assert real object still ahs the correct value
		assertEquals("Q123", error.getErrorCode());
	}	
	
	
	// this causes an exception because the spy.get(0) makes the actual call on real object i.e empty list
	// instead use doReturn
	//
	@Test(expected=IndexOutOfBoundsException.class)
	public void when_thenReturn_fails() throws Exception {
		List<String> list = new ArrayList<String>();
		List<String> spy = Mockito.spy(list);
		//	impossible the real list.get(0) is called and fails
		//	with IndexOutofBoundsException, as the list is empty
		Mockito.when(spy.get(0)).thenReturn("not reachable");
	}	
	
	@Test 
	public void when_doReturn_passes() throws Exception {
		List<String> list = new ArrayList<String>();
		List<String> spy = Mockito.spy(list);
		//doReturn fixed the issue
		Mockito.doReturn("now reachable").when(spy).get(0);
		assertEquals("now reachable", spy.get(0));
	}	
	
	
}
