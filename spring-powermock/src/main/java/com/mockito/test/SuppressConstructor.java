package com.mockito.test;

public class SuppressConstructor {

	public int someValue = 100;
	
	public SuppressConstructor(int val){
		val = val/0;
	}	
	
}
