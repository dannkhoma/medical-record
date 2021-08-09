package com.nkhomad.city.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CityRequest {

    @NotEmpty
    private String name;
}
