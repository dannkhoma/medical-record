package com.nkhomad.patient.service;

import com.nkhomad.patient.model.Patient;
import com.nkhomad.patient.model.PatientRequest;
import com.nkhomad.service.GenericService;

import java.util.List;

public interface PatientService extends GenericService<Patient, PatientRequest> {

    List<Patient> findPatients(final String searchTerm);

    List<Patient> findAll();

}
