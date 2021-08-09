package com.nkhomad.patient.service;

import com.nkhomad.aspect.LogEntryExit;
import com.nkhomad.city.service.CityService;
import com.nkhomad.country.model.Country;
import com.nkhomad.country.service.CountryService;
import com.nkhomad.exception.PatientsNotFoundException;
import com.nkhomad.patient.model.Patient;
import com.nkhomad.patient.model.PatientRequest;
import com.nkhomad.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final CityService cityService;
    private final CountryService countryService;

    @Override
    @LogEntryExit(value = LogLevel.DEBUG)
    public Long save(final PatientRequest patientRequest) {
        final Country country = countryService.findCountry(patientRequest.getCountry());
        final Patient patient = patientRepository.save(Patient.builder()
                .age(patientRequest.getAge())
                .city(cityService.findCityAndCountry(patientRequest.getCity(), country))
                .country(country)
                .firstName(patientRequest.getFirstName())
                .lastName(patientRequest.getLastName())
                .gender(patientRequest.getGender())
                .diabetesState(patientRequest.getDiabetesState())
                .build());
        return patient.getId();
    }

    @Override
    @LogEntryExit(value = LogLevel.DEBUG)
    public Patient update(final Long id, final PatientRequest patientRequest) {

        final Patient patient = patientRepository.findById(patientRequest.getId())
                .orElseThrow(() -> new PatientsNotFoundException(String.format("Patient with id: '%s' not found", patientRequest.getId())));
        final Country country = countryService.findCountry(patientRequest.getCountry());
        patient.setAge(patientRequest.getAge());
        patient.setCity(cityService.findCityAndCountry(patientRequest.getCity(), country));
        patient.setCountry(country);
        patient.setDiabetesState(patientRequest.getDiabetesState());
        patient.setGender(patientRequest.getGender());
        patient.setFirstName(patientRequest.getFirstName());
        patient.setLastName(patientRequest.getLastName());
        return patient;
    }

    @Override
    @LogEntryExit
    public List<Patient> findPatients(final String searchTerm) {

        validateParameters(searchTerm);

        List<Patient> patients = patientRepository.findPatientByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchTerm, searchTerm);

        if (patients.isEmpty()) {
            throw new PatientsNotFoundException("Patient with given name not found");
        }

        return patients;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    private void validateParameters(final String searchTerm) {
        if (ObjectUtils.isEmpty(searchTerm)) {
            throw new IllegalArgumentException("Search parameter cannot be null");
        }
    }

}
