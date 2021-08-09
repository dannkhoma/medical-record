package com.nkhomad.rest;

import com.nkhomad.aspect.LogEntryExit;
import com.nkhomad.exception.PatientsNotFoundException;
import com.nkhomad.patient.model.Patient;
import com.nkhomad.patient.model.PatientRequest;
import com.nkhomad.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/1.0/emr/patients")
@RequiredArgsConstructor
public class PatientResource {

    private final PatientService patientService;

    @PostMapping
    @LogEntryExit(value = LogLevel.INFO)
    public ResponseEntity<Void> createPatient(@Valid @RequestBody PatientRequest patientRequest, UriComponentsBuilder uriComponentsBuilder) {
        final Long patientId = patientService.save(patientRequest);

        UriComponents uriComponents = uriComponentsBuilder.path("/api/1.0/emr/patient/{id}").buildAndExpand(patientId);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAllPatients() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @GetMapping("/patient")
    @LogEntryExit(LogLevel.INFO)
    public List<Patient> findPatients(@RequestParam(value = "term") final String searchTerm) {
        try {
            return patientService.findPatients(searchTerm);
        } catch (PatientsNotFoundException ex) {
            return Collections.emptyList();
        }
    }
}
