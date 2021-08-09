package com.nkhomad.city.service;

import com.nkhomad.city.model.City;
import com.nkhomad.city.model.CityRequest;
import com.nkhomad.country.model.Country;
import com.nkhomad.service.GenericService;

public interface CityService extends GenericService<City, CityRequest> {

    City findCityAndCountry(String name, Country country);
}
