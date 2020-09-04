package com.oop.constant;

public enum DegreeEnum {

    OTHER("高中及以下"), ASSOCIATE("专科"), BACHELOR("本科"), MASTER("硕士"), DOCTOR("博士");

    String degree;

    private DegreeEnum(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }
}
