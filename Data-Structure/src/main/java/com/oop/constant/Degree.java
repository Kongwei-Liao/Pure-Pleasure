package com.oop.constant;

public enum Degree {
    // "小学","初中","高中",associate "专科教育",bachelor "本科教育",master "研究生",doctor

    OTHER("高中及以下"), ASSOCIATE("专科学历"), BACHELOR("本科学历"), MASTER("硕士学历"), DOCTOR("博士学历");

    String degree;

    private Degree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }
}
