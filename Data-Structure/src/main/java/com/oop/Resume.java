package com.oop;

import com.oop.constant.DegreeEnum;
import com.oop.constant.GenderEnum;
import com.oop.constant.JobsEnum;

/**
 * @Classname Resume
 * @Date 2020/8/25 18:44
 * @Created by liaogangwei
 * @Description 简历
 */
public class Resume {

    private String name;
    private GenderEnum gender;
    private JobsEnum job;
    private String decription;
    private String email;
    private DegreeEnum degree;
    private String tel;
    private String interviewer;
    private boolean result;         // 结果
    private String appraise;        // 评价

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public JobsEnum getJob() {
        return job;
    }

    public void setJob(JobsEnum job) {
        this.job = job;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DegreeEnum getDegree() {
        return degree;
    }

    public void setDegree(DegreeEnum degree) {
        this.degree = degree;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", job=" + job +
                ", decription='" + decription + '\'' +
                ", email='" + email + '\'' +
                ", degree=" + degree +
                ", tel='" + tel + '\'' +
                ", interviewer='" + interviewer + '\'' +
                ", result=" + result +
                ", appraise='" + appraise + '\'' +
                '}';
    }
}
