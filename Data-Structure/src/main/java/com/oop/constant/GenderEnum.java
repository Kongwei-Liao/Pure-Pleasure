package com.oop.constant;

public enum GenderEnum {

    MALE("男"), FEMALE("女");

    String gender;
    GenderEnum(String s) {
        this.gender = s;
    }

    public String getGender() {
        return gender;
    }
}
