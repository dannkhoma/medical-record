package com.nkhomad.country.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CountryRequest {

    @NotEmpty
    private String name;
}
