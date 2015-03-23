package com.paclt.chap3;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

// with mockito junit runner you dont need to add the before set up or mockito annotations
@RunWith(MockitoJUnitRunner.class) 
public class AnnotationTest { 
	
	
	@Captor 
	ArgumentCaptor<List<String>> captor; 
	@Mock 
	Service service; 
	
	@Test 
	public void when_captor_annotation_is_used() { 
		service.call(Arrays.asList("a","b")); 
		verify(service).call(captor.capture()); 
		assertTrue(captor.getValue().containsAll(Arrays. asList("a","b"))); 
	} 
	
	interface Service {
		void call(List<String> args);
	}	
} 
