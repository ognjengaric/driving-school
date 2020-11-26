package com.ognjengaric.demo.dto;

import com.ognjengaric.demo.enums.LicenceCategory;

import java.util.List;

public class NewUserDTO {
    private String candidateId;
    private String name;
    private String surname;
    private String password;
    private LicenceCategory currentLicence;
    private List<LicenceCategory> licences;
    private String schoolId;
    private String instructorId;
    private String role;

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LicenceCategory getCurrentLicence() {
        return currentLicence;
    }

    public void setCurrentLicence(LicenceCategory currentLicence) {
        this.currentLicence = currentLicence;
    }

    public List<LicenceCategory> getLicences() {
        return licences;
    }

    public void setLicences(List<LicenceCategory> licences) {
        this.licences = licences;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
