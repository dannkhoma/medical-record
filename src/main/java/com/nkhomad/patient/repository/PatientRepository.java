package com.nkhomad.patient.repository;

import com.nkhomad.patient.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

     List<Patient> findPatientByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(final String firstName, final String lastName);

}
