package com.oop.constant;

public enum Gender {

    MALE("男性"), FEMALE("女性");

    String gender;
    Gender(String s) {
        this.gender = s;
    }

    public String getGender() {
        return gender;
    }
}
