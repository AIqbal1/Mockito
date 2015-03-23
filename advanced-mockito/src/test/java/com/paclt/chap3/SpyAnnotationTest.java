package com.paclt.chap3;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SpyAnnotationTest {

	@Spy
	ErrorHandler errorHandler;
	
	@Test
	public void when_spy_annotation_is_used() throws Exception {
		assertNotNull(errorHandler);
	}
}


