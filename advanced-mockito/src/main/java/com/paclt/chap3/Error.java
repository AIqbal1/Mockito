package com.paclt.chap3;

public class Error {

	private StackTraceElement[] trace;
	private String errorCode;

	public StackTraceElement[] getTrace() {
		return trace;
	}

	public void setTrace(StackTraceElement[] trace) {
		this.trace = trace;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
