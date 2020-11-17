package com.example.aop;

public class Student {

    private Integer age;

    public void setAge(Integer age) {
        this.age = age;
    }

    @Loggable
    public Integer getAge() {
        System.out.println("Age : " + age );
        return age;
    }
}