package com.nkhomad.country.model;

import com.nkhomad.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Country extends BaseEntity {

    @Column(nullable = false)
    private String name;

}
