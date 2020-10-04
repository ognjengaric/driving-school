package com.ognjengaric.demo.domain;

import javax.persistence.*;

import com.ognjengaric.demo.enums.LicenceCategory;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DrivingSchool {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String shortName;

    @ElementCollection
    private List<LicenceCategory> availableCategories = new ArrayList<>();

    @OneToMany
    private List<Candidate> candidates = new ArrayList<>();

    @OneToMany
    private List<Instructor> instructors = new ArrayList<>();

    @OneToMany
    private List<DrivingClass> classes = new ArrayList<>();

    public DrivingSchool() {}

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<LicenceCategory> getAvailableCategories() {
        return availableCategories;
    }

    public void setAvailableCategories(List<LicenceCategory> availableCategories) {
        this.availableCategories = availableCategories;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<DrivingClass> getClasses() {
        return classes;
    }

    public void setClasses(List<DrivingClass> classes) {
        this.classes = classes;
    }
}
