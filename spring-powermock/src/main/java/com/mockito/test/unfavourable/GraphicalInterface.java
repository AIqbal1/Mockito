package com.mockito.test.unfavourable;



public class GraphicalInterface {

	public static void showMessage(String msg) {
		throw new TestingImpedimentException("GUI objects need manual intervention");
	}
}
