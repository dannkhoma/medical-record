package com.nkhomad.enums;

public enum DiabetesState {

    YES("Y"), NO("N"), UNKNOWN("U");

    private final String initial;

    DiabetesState(String initial) {
        this.initial = initial;
    }

    public String getInitial() {
        return initial;
    }


}
