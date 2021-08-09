package com.nkhomad.patient.model;

import com.nkhomad.BaseEntity;
import com.nkhomad.city.model.City;
import com.nkhomad.country.model.Country;
import com.nkhomad.enums.DiabetesState;
import com.nkhomad.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Patient extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Country country;

    @Column(nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "diabetes_state")
    private DiabetesState diabetesState;


}
