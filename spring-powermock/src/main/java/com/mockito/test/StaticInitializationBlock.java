package com.mockito.test;

public class StaticInitializationBlock {
	static int value;
	
	static	{
		value = 100/0;
		System.out.println("In static block");
	}
}
