package com.ognjengaric.demo.dto;

import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.Instructor;
import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.enums.LicenceCategory;

public class UserDTO {
    private String id;
    private String name;
    private String surname;
    private LicenceCategory currentLicence;
    private String role;

    public UserDTO() {
    }

    public UserDTO(User user){
        this.id = user.getId().toString();
        this.name = user.getName();
        this.surname = user.getSurname();

        if(user instanceof Candidate){
            this.currentLicence = ((Candidate) user).getCurrentLicence();
            this.role = "Candidate";
        } else  if(user instanceof Instructor)
            this.role = "Instructor";
        else
            this.role = "Administrator";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LicenceCategory getCurrentLicence() {
        return currentLicence;
    }

    public void setCurrentLicence(LicenceCategory currentLicence) {
        this.currentLicence = currentLicence;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
