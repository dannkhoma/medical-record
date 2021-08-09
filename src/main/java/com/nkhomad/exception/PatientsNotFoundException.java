package com.nkhomad.exception;

public class PatientsNotFoundException extends RuntimeException {

    public PatientsNotFoundException(String message) {
        super(message);
    }
}
