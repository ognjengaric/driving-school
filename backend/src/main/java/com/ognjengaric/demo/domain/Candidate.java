package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.CategoryType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "C")
public class Candidate extends User{


    @Column(unique = true)
    private String candidateId;

    @Column
    private CategoryType currentLicence;

    @ManyToOne(fetch = FetchType.EAGER)
    private Instructor instructor;

    @ElementCollection
    private List<CategoryType> ownedLicences = new ArrayList<>();

    @OneToMany
    private List<DrivingClass> classes = new ArrayList<>();

    public Candidate() {
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public CategoryType getCurrentLicence() {
        return currentLicence;
    }

    public void setCurrentLicence(CategoryType currentLicence) {
        this.currentLicence = currentLicence;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<CategoryType> getOwnedLicences() {
        return ownedLicences;
    }

    public void setOwnedLicences(List<CategoryType> ownedLicences) {
        this.ownedLicences = ownedLicences;
    }

    public List<DrivingClass> getClasses() {
        return classes;
    }

    public void setClasses(List<DrivingClass> classes) {
        this.classes = classes;
    }

    public void addDrivingClass(DrivingClass drivingClass){
        this.classes.add(drivingClass);
        drivingClass.setCandidate(this);
    }

    public void removeDrivingClass(DrivingClass drivingClass){
        this.classes.remove(drivingClass);
        drivingClass.setCandidate(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Candidate )) return false;
        return super.getId() != null && super.getId().equals(((Candidate) o).getId());
    }
}
