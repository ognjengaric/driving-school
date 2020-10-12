package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.LicenceCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Instructor extends User{

    @OneToMany(mappedBy = "instructor")
    private List<Candidate> candidates = new ArrayList<>();

    @ElementCollection
    private List<LicenceCategory> trainableLicences = new ArrayList<>();

    @OneToMany
    private List<DrivingClass> classes = new ArrayList<>();

    @ManyToOne
    private DrivingSchool drivingSchool;

    public Instructor() {
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<LicenceCategory> getTrainableLicences() {
        return trainableLicences;
    }

    public void setTrainableLicences(List<LicenceCategory> trainableLicences) {
        this.trainableLicences = trainableLicences;
    }

    public List<DrivingClass> getClasses() {
        return classes;
    }

    public void setClasses(List<DrivingClass> classes) {
        this.classes = classes;
    }

    public DrivingSchool getDrivingSchool() {
        return drivingSchool;
    }

    public void setDrivingSchool(DrivingSchool drivingSchool) {
        this.drivingSchool = drivingSchool;
    }

    public void addCandidate(Candidate candidate){
        this.candidates.add(candidate);
        candidate.setInstructor(this);
    }

    public void removeCandidate(Candidate candidate){
        this.candidates.remove(candidate);
        candidate.setInstructor(null);
    }

    public void addDrivingClass(DrivingClass drivingClass){
        this.classes.add(drivingClass);
        drivingClass.setInstructor(this);
    }

    public void removeDrivingClass(DrivingClass drivingClass){
        this.classes.remove(drivingClass);
        drivingClass.setInstructor(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Instructor )) return false;
        return super.getId() != null && super.getId().equals(((Instructor) o).getId());
    }
}
