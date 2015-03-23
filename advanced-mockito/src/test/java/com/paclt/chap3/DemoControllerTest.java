package com.paclt.chap3;

import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class DemoControllerTest {
	
	DemoController controller;
	
	@Mock
	LoginController loginController;
	@Mock 
	HttpServletRequest request;
	@Mock 
	HttpServletResponse response;
	@Mock 
	RequestDispatcher dispatcher;
	@Mock 
	MessageRepository repository;
	@Mock 
	ErrorHandler errorHandler;
	
	@Before
	public void beforeEveryTest() {
		MockitoAnnotations.initMocks(this);
		controller = new DemoController(loginController, errorHandler, repository);
	}
	
	@Test
	public void when_subsystem_throws_exception_Then_routes_to_error_page_() throws Exception {
		// throwing exception from a  void method
		doThrow(new IllegalStateException("LDAP error")).when(loginController).process(request, response);
		when(request.getServletPath()).thenReturn("/logon.do");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		
		controller.doGet(request, response);
		
		verify(request).getRequestDispatcher(eq("error.jsp"));
		verify(dispatcher).forward(request, response);
	}	
	
	// doAnswer(answer).when(mock).someVoidMethod();
	//
	// chaining : -
	//
	// doThrow(new RuntimeException()).
	// doNothing().
	// doAnswer(someAnswer).
	// when(mock).someVoidMethod();
	
	// calling the real method :
	//
	// doCallRealMethod().when(mock).someVoidMethod();
	
	@Test
	public void when_subsystem_throws_any_exception_Then_finds_error_message_and_routes_to_error_page_() throws Exception {
		doThrow(new IllegalStateException("LDAP error")).when(loginController).process(request, response);
		
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Error err = (Error)invocation.getArguments()[0];
				err.setErrorCode("123");
				return err;
			}
		}).when(errorHandler).mapTo(isA(Error.class));
		
		when(request.getServletPath()).thenReturn("/logon.do");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		
		controller.doGet(request, response);
		
		verify(request).getRequestDispatcher(eq("error.jsp"));
		verify(dispatcher).forward(request, response);
	}	
	
	// using captor to test arguments passed to methods:-
	//
	// ArgumentCaptor<T> argCaptor= ArgumentCaptor.forClass(T.class);
	// verify(mockObject).methodA(argCaptor.capture());
	@Test
	public void when_subsystem_throws_any_exception_Then_finds_error_message_using_captor_and_routes_to_error_page_() throws Exception {
		doThrow(new IllegalStateException("LDAP error")).when(loginController).process(request, response);
		
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Error err = (Error)invocation.getArguments()[0];
				err.setErrorCode("123");
				return err;
			}
		}).when(errorHandler).mapTo(isA(Error.class));
		
		when(request.getServletPath()).thenReturn("/logon.do");
		when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
		
		controller.doGet(request, response);
		
		verify(request).getRequestDispatcher(eq("error.jsp"));
		verify(dispatcher).forward(request, response);
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);		
		verify(repository).lookUp(captor.capture());
		assertEquals("123", captor.getValue());
	}
	
	// testing with generics
	//	
	// old way:
	//
	// Class<List<String>> listClass = (Class<List<String>>) (Class)List.class;
	//
	// ArgumentCaptor<List<String>> captor = ArgumentCaptor.forClass(listClass);
	
	@Captor
	ArgumentCaptor<List<String>> captor;
	@Mock 
	Service service;
	
	@Test
	public void when_captures_collections() {	
		service.call(Arrays.asList("a","b"));
		verify(service).call(captor.capture());
		assertTrue(captor.getValue().containsAll(Arrays.asList("a","b")));				
	}
	
	interface Service {
		void call(List<String> args);
	}	
	
	// without annotations
	@Test
	public void when_capturing_variable_args() throws Exception {
		String[] errorCodes = {"a","b","c"};
		
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		repository.lookUp(errorCodes);
		
		verify(repository).lookUp(captor.capture(),captor.capture(),captor.capture());
		assertTrue(captor.getAllValues().containsAll(Arrays.asList(errorCodes)));
	}	
	
	
	// ordering the calls :-
	//
	// InOrder inOrder=inOrder(mock1,mock2,...mockN);
	
	@Test
	public void when_inorder_invocations() throws Exception {
		request.getServletPath();
		service.call(Arrays.asList("a","b"));
		
		InOrder inOrder=inOrder(request,service);		
		inOrder.verify(request).getServletPath();
		inOrder.verify(service).call(anyList());
	}
	
	
}
