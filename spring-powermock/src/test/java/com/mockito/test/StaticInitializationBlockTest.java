package com.mockito.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor("com.mockito.test.StaticInitializationBlock")
public class StaticInitializationBlockTest {

	
	@Test
	public void supresses_static_initialization_blocks() {
		assertEquals(0, StaticInitializationBlock.value);
	}	
	
}
