package com.mockito.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemVerifierFinalClass.class)
public class FinalClassTest {

	@Test
	public void mocks_final_classes() throws Exception {
		
		SystemVerifierFinalClass systemVerifier = mock(SystemVerifierFinalClass.class);
		when(systemVerifier.isInstallable()).thenReturn(true);
		
		SoftwareInstaller installer = new SoftwareInstaller(systemVerifier);
		assertTrue(installer.install("java"));
	}
	
}
