package com.nkhomad.country.service;

import com.nkhomad.aspect.LogEntryExit;
import com.nkhomad.city.model.City;
import com.nkhomad.country.model.Country;
import com.nkhomad.country.model.CountryRequest;
import com.nkhomad.country.repository.CountryRepository;
import com.nkhomad.exception.CityNotFoundException;
import com.nkhomad.exception.CountryNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Country findCountry(String name) {
        return countryRepository.findByName(name)
                .orElseThrow(() -> new CityNotFoundException(String.format("Country with name: '%s' not found", name)));
    }

    @Override
    @LogEntryExit
    public Long save(final CountryRequest countryRequest) {
        final Country country = countryRepository.save(Country.builder()
                .name(countryRequest.getName())
                .build());
        return country.getId();
    }

    @Override
    @LogEntryExit
    public Country update(final Long id, final CountryRequest countryRequest) {

        final Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(String.format("Country with name: '%s' not found", countryRequest.getName())));

        country.setName(countryRequest.getName());

        return country;
    }

}
