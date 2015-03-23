package com.mockito.test;

public class PrivateMethod {

	private String secretValue(){
		return "#$$%^&*";
	}
	
	public String exposeTheSecretValue(){
		return secretValue();
	}		
	
}
