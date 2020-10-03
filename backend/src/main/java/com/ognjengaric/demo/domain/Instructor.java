package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.CategoryType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "I")
public class Instructor extends User{

    @OneToMany(mappedBy = "instructor")
    private List<Candidate> candidates = new ArrayList<>();

    @ElementCollection
    private List<CategoryType> trainableLicences = new ArrayList<>();

    @OneToMany
    private List<DrivingClass> classes = new ArrayList<>();

    public Instructor() {
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<CategoryType> getTrainableLicences() {
        return trainableLicences;
    }

    public void setTrainableLicences(List<CategoryType> trainableLicences) {
        this.trainableLicences = trainableLicences;
    }

    public List<DrivingClass> getClasses() {
        return classes;
    }

    public void setClasses(List<DrivingClass> classes) {
        this.classes = classes;
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
}
