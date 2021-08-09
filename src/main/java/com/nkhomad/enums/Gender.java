package com.nkhomad.enums;

public enum Gender {

    MALE("M"), FEMALE("F");

    private final String initial;

    Gender(String initial) {
        this.initial = initial;
    }

    public String getInitial() {
        return initial;
    }
}
