package com.nkhomad.city.service;

import com.nkhomad.aspect.LogEntryExit;
import com.nkhomad.city.model.City;
import com.nkhomad.city.model.CityRequest;
import com.nkhomad.country.model.Country;
import com.nkhomad.exception.CityNotFoundException;
import com.nkhomad.city.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    @LogEntryExit
    public City findCityAndCountry(String name, Country country) {
        return cityRepository.findByNameAndCountry(name, country)
                .orElseThrow(() -> new CityNotFoundException(String.format("City with name: '%s' not found", name)));
    }

    @Override
    @LogEntryExit
    public Long save(final CityRequest cityRequest) {
        final City city = cityRepository.save(City.builder()
                .name(cityRequest.getName())
                .build());
        return city.getId();
    }

    @Override
    @LogEntryExit
    public City update(final Long id, final CityRequest cityRequest) {

        final City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(String.format("City with name: '%s' not found", cityRequest.getName())));

        city.setName(cityRequest.getName());

        return city;
    }

}
