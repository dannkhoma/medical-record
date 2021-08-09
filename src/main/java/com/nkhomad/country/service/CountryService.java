package com.nkhomad.country.service;

import com.nkhomad.country.model.Country;
import com.nkhomad.country.model.CountryRequest;
import com.nkhomad.service.GenericService;

public interface CountryService extends GenericService<Country, CountryRequest> {

    Country findCountry(String name);

}
