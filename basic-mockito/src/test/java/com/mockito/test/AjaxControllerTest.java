package com.mockito.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class AjaxControllerTest {

	HttpServletRequest request;
	CountryDao countryDao;
	AjaxController ajaxController;
	
	List<Country> countries;
	
	@Before
	public void setUp() {
		request = Mockito.mock(HttpServletRequest.class);
		countryDao = Mockito.mock(CountryDao.class);
		ajaxController = new AjaxController(countryDao);
		
		countries = new ArrayList<Country>();
		countries.add(create("Argentina", "AR", "32"));
		countries.add(create("USA", "US", "01"));
		countries.add(create("Brazil", "BR", "05"));
		countries.add(create("India", "IN", "91"));
	}
	
	private Country create(String name, String iso, String coutryCode) {
		Country country = new Country();
		country.setCountryCode(coutryCode);
		country.setIso(iso);
		country.setName(name);
		return country;
	}	

	@Test
	public void retrieves_empty_country_list() throws Exception {
		List<Country> list = new ArrayList<Country>();
		list.add(new Country());

		// stubbing the get parameter
		when(request.getParameter(anyString())).thenReturn("1", "10",
				SortOrder.ASC.name(), SortColumn.iso.name());

		// stubbing the retrieve method
		when(countryDao.retrieve(isA(RetrieveCountryRequest.class)))
				.thenReturn(list);

		assertTrue(countryDao.retrieve(new RetrieveCountryRequest()).size() == 1);

		JsonDataWrapper<Country> response = ajaxController.retrieve(request);
		assertEquals(1, response.getPage());
		assertEquals(1, response.getTotal());
		assertEquals(1, response.getRows().size());

	}
	
	// throwing exception
	@Test(expected=RuntimeException.class)
	public void when_system_throws_exception() {
		when(request.getParameter(anyString())).thenReturn("1", "10", SortOrder.DESC.name(), SortColumn.iso.name());
		when(countryDao.retrieve(isA(RetrieveCountryRequest.class))).thenThrow(new RuntimeException("Database failure"));
		JsonDataWrapper<Country> response = ajaxController.retrieve(request);
	}	

	// throwing exceptions from void method
	// doThrow(exception).when(mock).voidmethod(arguments);
	
	@Test
	public void countryList_sortedBy_ISO_In_asc_order() {
		
		when(request.getParameter(anyString())).thenReturn("1", "10", SortOrder.ASC.name(), SortColumn.iso.name());
		
		Country argentina = new Country();
		argentina.setIso("AR");
		
		Country india = new Country();
		india.setIso("IN");
		
		Country usa = new Country();
		usa.setIso("US");
		
		List<Country> ascCountryList = new ArrayList<Country>();
		ascCountryList.add(argentina);	
		ascCountryList.add(india);
		ascCountryList.add(usa);
		
		when(countryDao.retrieve(argThat(new SortByISOInAscendingOrderMatcher()))).thenReturn(ascCountryList);
		JsonDataWrapper<Country> response = ajaxController.retrieve(request);
		
		assertEquals(ascCountryList, response.getRows());
	}	
	
	@Test 
	public void verify_zero_interaction() {
		verifyZeroInteractions(request,countryDao);
	}
	
	@Test 
	public void verify_nomore_interaction() {
		request.getParameter("page");
		request.getContextPath();
		Mockito.verify(request).getParameter(anyString());
		Mockito.verify(request).getContextPath();
		//this will fail getContextPath() is not verified
		verifyNoMoreInteractions(request);
	}	
	
	@Test
	public void sorting_asc_on_iso() {
		
		when(request.getParameter(anyString())).thenReturn("1", "10", SortOrder.ASC.name(), SortColumn.iso.name());
		when(countryDao.retrieve(isA(RetrieveCountryRequest.class))).thenAnswer(new SortAnswer());
		JsonDataWrapper<Country> response = ajaxController.retrieve(request);
		
		assertEquals("AR", response.getRows().get(0).getIso());
		assertEquals("BR", response.getRows().get(1).getIso());
		assertEquals("IN", response.getRows().get(2).getIso());
		assertEquals("US", response.getRows().get(3).getIso());
	}
	
	@Test
	public void sorting_desc_on_iso() {
		
		when(request.getParameter(anyString())).thenReturn("1","10",SortOrder.DESC.name(), SortColumn.iso.name());
		when(countryDao.retrieve(isA(RetrieveCountryRequest.class))).thenAnswer(new SortAnswer());		
		JsonDataWrapper<Country> response = ajaxController.retrieve(request);
	
		assertEquals("AR", response.getRows().get(3).getIso());
		assertEquals("BR", response.getRows().get(2).getIso());
		assertEquals("IN", response.getRows().get(1).getIso());
		assertEquals("US", response.getRows().get(0).getIso());
	}	
	
	class SortByISOInAscendingOrderMatcher extends ArgumentMatcher<RetrieveCountryRequest> {
		@Override
		public boolean matches(Object request) {
			if (request instanceof RetrieveCountryRequest) {
				SortOrder sortOrder = ((RetrieveCountryRequest) request).getSortOrder();
				SortColumn col = ((RetrieveCountryRequest) request).getSortname();
				return SortOrder.ASC.equals(sortOrder) && SortColumn.iso.equals(col);
			}
			return false;
		}
	}	
	
	class SortAnswer implements Answer<Object> {
		@Override
		public Object answer(InvocationOnMock invocation) throws Throwable {
			RetrieveCountryRequest request = (RetrieveCountryRequest)invocation.getArguments()[0];
			final int order = request.getSortOrder().equals(SortOrder.ASC) ? 1: -1;
			final SortColumn col = request.getSortname();
			Collections.sort(countries, new Comparator<Country>() {
				
				public int compare(Country arg0, Country arg1) {
					if (SortColumn.countryCode.equals(col))
						return order * arg0.getCountryCode().compareTo(arg1.getCountryCode());
					if (SortColumn.iso.equals(col))
						return order * arg0.getIso().compareTo(arg1.getIso());
					return order * arg0.getName().compareTo(arg1.getName());
				}
			});
			return countries;
		}
	}
	
}
