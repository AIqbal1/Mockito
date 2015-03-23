package com.mockito.test.testability;

import com.mockito.test.unfavourable.GraphicalInterface;

public class PrivateMethodRefactored {

	public Object validate(Object arg) {
		if (arg == null) {
			showError("Null input");
		}
		return arg;
	}

	// extract the testing impediments to a protected method
	protected void showError(String msg) {
		GraphicalInterface.showMessage(msg);
	}
}
