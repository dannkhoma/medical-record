package com.nkhomad.patient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nkhomad.enums.DiabetesState;
import com.nkhomad.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PatientRequest {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private Gender gender;

    @NotEmpty
    private String city;

    @NotEmpty
    private String country;

    @NotNull
    private int age;

    private DiabetesState diabetesState;
}
