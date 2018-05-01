package com.scaffold.application.constant;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("other");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }
}
