package com.oop;

import com.oop.constant.Degree;

import java.util.Date;

public class Candidate extends Person {

    private String introduction;
    private String specialty;
    private String forWhichJob;
    private Degree degree;
    private Date date;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getForWhichJob() {
        return forWhichJob;
    }

    public void setForWhichJob(String forWhichJob) {
        this.forWhichJob = forWhichJob;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
