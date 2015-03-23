package com.mockito.test.unfavourable;

public class FileReadDependency {
	public FileReadDependency() {
		throw new TestingImpedimentException("File read error");
	}
}
