package com.ognjengaric.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ognjengaric.demo.dto.NewUserDTO;
import com.ognjengaric.demo.enums.ClassStatusType;
import com.ognjengaric.demo.enums.LicenceCategory;
import com.ognjengaric.demo.enums.RoadType;
import com.ognjengaric.demo.enums.TrafficIntensityType;
import com.ognjengaric.demo.util.ClassesCalculator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@PrimaryKeyJoinColumn(name="id")
public class Candidate extends User{


    @Column(unique = true)
    private String candidateId;

    @Column
    private LicenceCategory currentLicence;

    @ManyToOne(fetch = FetchType.EAGER)
    private Instructor instructor;

    @ElementCollection
    private List<LicenceCategory> ownedLicences = new ArrayList<>();

    @OneToMany
    private List<DrivingClass> classes = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private DrivingSchool drivingSchool;

    public Candidate() {
    }

    public Candidate(NewUserDTO dto, Instructor instructor){
        super(dto.getName(), dto.getSurname(), dto.getPassword());
        this.candidateId = dto.getCandidateId();
        this.currentLicence = dto.getCurrentLicence();
        this.instructor = instructor;
        this.ownedLicences = dto.getLicences();
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public LicenceCategory getCurrentLicence() {
        return currentLicence;
    }

    public void setCurrentLicence(LicenceCategory currentLicence) {
        this.currentLicence = currentLicence;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<LicenceCategory> getOwnedLicences() {
        return ownedLicences;
    }

    public void setOwnedLicences(List<LicenceCategory> ownedLicences) {
        this.ownedLicences = ownedLicences;
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

    private List<DrivingClass> getFinishedClasses(){
        return this.getClasses().stream()
                .filter(c -> c.getStatus().equals(ClassStatusType.FINISHED))
                .collect(Collectors.toList());
    }

    public int getTotalClassesFinished(){
        return this.getFinishedClasses()
                .size();
    }

    public long getDrivingRangeFinished(){
       return getFinishedClasses().stream()
               .filter(DrivingClass::getOnDrivingRange)
               .count();
    }

    public long getIntensityFinished(TrafficIntensityType intensity){
        return getFinishedClasses().stream()
                .filter(c -> c.getRoute() != null)
                .filter(c -> c.getRoute().getStreets()
                            .stream()
                            .anyMatch(s -> s.getIntensity().equals(intensity)))
                .count();
    }

    public long getRoadFinished(RoadType type){
        return getFinishedClasses().stream()
                .filter(c -> c.getRoute() != null)
                .filter(c -> c.getRoute().getStreets()
                        .stream()
                        .anyMatch(s -> s.getRoadType().equals(type)))
                .count();
    }

    public long getWithLoadFinished(){
        return getFinishedClasses().stream()
                .filter(DrivingClass::getWithLoad)
                .count();
    }

    public int getNightFinished(ClassesCalculator calc){
        int num = 0;

        for(DrivingClass c : getFinishedClasses()){
            if(calc.isNight(c.getStartDt(), c.getEndDt()))
                num++;
        }

        return num;
    }
}
