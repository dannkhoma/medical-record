package com.nkhomad.patient.service;

import com.nkhomad.city.model.City;
import com.nkhomad.city.service.CityService;
import com.nkhomad.country.model.Country;
import com.nkhomad.country.service.CountryService;
import com.nkhomad.enums.DiabetesState;
import com.nkhomad.enums.Gender;
import com.nkhomad.exception.PatientsNotFoundException;
import com.nkhomad.patient.model.Patient;
import com.nkhomad.patient.model.PatientRequest;
import com.nkhomad.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private CityService cityService;

    @Mock
    private CountryService countryService;

    @InjectMocks
    private PatientServiceImpl patientService;



    private List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        patients.add(getMockPatient(1L, "Daniel", "Nkhoma", 33, Gender.MALE, DiabetesState.NO));
        patients.add(getMockPatient(2L, "Sam", "Robinson", 53, Gender.MALE, DiabetesState.YES));
        return patients;
    }

    private Patient getMockPatient(Long id, String firstName, String lastName, int age, Gender gender, DiabetesState diabetesState) {
        final Patient patient = new Patient();
        patient.setId(id);
        patient.setAge(age);
        patient.setCity(getCity());
        patient.setCountry(getCountry());
        patient.setGender(gender);
        patient.setDiabetesState(diabetesState);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        return patient;
    }

    private PatientRequest getMockPatientRequest(Long id, String firstName, String lastName, int age, Gender gender, DiabetesState diabetesState) {
        final PatientRequest patient = new PatientRequest();
        patient.setId(id);
        patient.setAge(age);
        patient.setCity(getCity().getName());
        patient.setCountry(getCity().getCountry().getName());
        patient.setGender(gender);
        patient.setDiabetesState(diabetesState);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        return patient;
    }

    private City getCity(){
        final City city= new City();
        city.setName("Brussels");
        city.setCountry(getCountry());
        return city;
    }

    private Country getCountry(){
        final Country country = new Country();
        country.setId(1L);
        country.setName("Germany");
        return country;
    }

    @Test
    void whenSearchParameterIsNullThrowIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            patientService.findPatients(null);
        });

        String expectedMessage = "Search parameter cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    void whenPatientsAreEmptyThrowPersonNotFoundExceptionException() {

        Mockito
                .when(patientRepository.findPatientByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("test", "test"))
                .thenReturn(new ArrayList<>());

        Exception exception = assertThrows(PatientsNotFoundException.class, () -> {
            patientService.findPatients("test");
        });

        String expectedMessage = "Patient with given name not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}