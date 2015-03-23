package com.mockito.test;

import java.util.List;

public interface CountryDao {

	List<Country> retrieve(RetrieveCountryRequest command);
}
