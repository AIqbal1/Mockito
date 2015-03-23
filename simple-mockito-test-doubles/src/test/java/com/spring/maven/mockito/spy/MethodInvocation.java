package com.spring.maven.mockito.spy;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * The MethodInvocation class represents a method invocation: the method
name, a parameter list, and a return value. Suppose a sum() method is
invoked with two numbers and the method returns the sum of two numbers,
then the MethodInvocation class will contain a method name as sum, a
parameter list that will include the two numbers, and a return value that
will contain the sum of the two numbers.
 * 
 * 
 * @author asif.iqbal
 *
 */

public class MethodInvocation {

	private List<Object> params = new ArrayList<Object>();
	private Object returnedValue = null;
	private String method;
	
	public List<Object> getParams() {		
		return params;
	}
	
	public MethodInvocation addParam(Object parm){
		getParams().add(parm);
		return this;
	}
	
	public Object getReturnedValue() {
		return returnedValue;
	}
	
	public MethodInvocation setReturnedValue(Object returnedValue) {
		this.returnedValue = returnedValue;
		return this;
	}
	
	public String getMethod() {
		return method;
	}
	
	public MethodInvocation setMethod(String method) {
		this.method = method;
		return this;
	}
}
