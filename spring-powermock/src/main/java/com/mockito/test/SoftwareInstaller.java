package com.mockito.test;

public class SoftwareInstaller {

	private final SystemVerifierFinalClass systemVerifier;
	
	public SoftwareInstaller(SystemVerifierFinalClass systemVerifier) {
		this.systemVerifier = systemVerifier;
	}
	
	public boolean install(String packageName) {
		if (systemVerifier.isInstallable()) {
			// install something
			return true;
		}
		return false;
	}	
	
}
