package com.nkhomad.city.repository;

import com.nkhomad.city.model.City;
import com.nkhomad.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByNameAndCountry(final String name, final Country country);

}
