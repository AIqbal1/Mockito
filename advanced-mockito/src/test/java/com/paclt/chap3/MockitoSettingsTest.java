package com.paclt.chap3;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MockitoSettingsTest {

	// RETURNS_DEFAULTS: This is the default setting that returns null for an object, false for a Boolean, and so on. 
	//
	// The following test case uses the RETURNS_DEFAULTS setting to return a NULL Bar object
	@Test
	public void when_default_settings() throws Exception {

		Foo fooWithReturnDefault = Mockito.mock(Foo.class, Mockito.RETURNS_DEFAULTS);
		// default null is returned, since its not stubbed
		assertNull(fooWithReturnDefault.getBar());

	}

	
	// This returns smart nulls, which are stubs that act like nulls
	// (in that they throw exceptions if you try and call stub. anyMethod())
	@Test
	public void when_changing_default_settings_to_return_smartNULLS(){

		Foo fooWithSmartNull = Mockito.mock(Foo.class, Mockito.RETURNS_SMART_NULLS);
		// a smart null is returned
		assertNotNull(fooWithSmartNull.getBar());
		System.out.println("fooWithSmartNull.getBar() = " + fooWithSmartNull.getBar());

	}

	// RETURNS_MOCKS: This returns mock for objects and default value for primitives. 
	// If the object cannot be mocked (such as a final class), a Null value is returned.
	
	// The RETURNS_MOCKS setting populates the Foo object with a mocked Bar object.
	// A mocked Bar object has a mocked Tar object and the mocked Tar object has an
	// empty mocked string name.
	@Test
	public void when_changing_default_settings_to_return_mocks() {

		Foo fooWithReturnsMocks = Mockito.mock(Foo.class, Mockito.RETURNS_MOCKS);
		// a mock is returned
		Bar mockBar = fooWithReturnsMocks.getBar();
		assertNotNull(mockBar);
		assertNotNull(mockBar.getTar());
		assertNotNull(mockBar.getTar().getName());
		System.out.println("fooWithReturnsMocks.getBar()=" + mockBar);
		System.out.println("fooWithReturnsMocks.getBar().getTar().getName()={" + mockBar.getTar().getName()+"}");

	}

	// RETURNS_DEEP_STUBS: This returns a deep stub. This is really important for legacy code where we need to stub 
	// the method chaining, for example, when Foo calls getBar().getTar().getName(). 
	// Deep stubbing allows Foo to directly stub the getName() method to return a value. 
	// Otherwise, we have to stub Foo's getBar method to return a mock Bar object, stub the bar's getTar() method to 
	// return a mock Tar object, and finally, stub the Tar's getName method to return a value. 
	@Test
	public void when_returns_deep_stub() throws Exception {
		
		Foo fooWithDeepStub = Mockito.mock(Foo.class, Mockito.RETURNS_DEEP_STUBS);
		when(fooWithDeepStub.getBar().getTar().getName()).thenReturn("Deep Stub");
		// a deep stubbed mock is returned
		System.out.println("fooWithDeepStub.getBar().getTar().getName()="+ fooWithDeepStub.getBar().getTar().getName());
		assertNotNull(fooWithDeepStub.getBar().getTar().getName());
	}

}

class Tar {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

class Bar {
	Tar tar;

	public Tar getTar() {
		return tar;
	}

	public void setTar(Tar tar) {
		this.tar = tar;
	}
}

class Foo {
	Bar bar;

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

}
