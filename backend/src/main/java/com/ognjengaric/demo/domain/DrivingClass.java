package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.ClassStatusType;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
public class DrivingClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Candidate candidate;

    @ManyToOne
    private Instructor instructor;

    @ManyToOne
    private DrivingSchool drivingSchool;

    @Column
    private ClassStatusType status;

    @ManyToOne
    private Route route;

    @Column
    private DateTime startDt;

    @Column
    private DateTime endDt;

    @Column
    private DateTime actualStartDt;

    @Column
    private DateTime actualEndDt;

    @Column
    private Boolean isOnDrivingRange;

    @Column
    private Boolean isWithLoad;

    public DrivingClass() {
        this.status = ClassStatusType.PENDING;
    }

    public DrivingClass(DateTime startDt, DateTime endDt) {
        this.status = ClassStatusType.PENDING;
        this.startDt = startDt;
        this.endDt = endDt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public DrivingSchool getDrivingSchool() {
        return drivingSchool;
    }

    public void setDrivingSchool(DrivingSchool drivingSchool) {
        this.drivingSchool = drivingSchool;
    }

    public ClassStatusType getStatus() {
        return status;
    }

    public void setStatus(ClassStatusType status) {
        this.status = status;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public DateTime getStartDt() {
        return startDt;
    }

    public void setStartDt(DateTime startDt) {
        this.startDt = startDt;
    }

    public DateTime getEndDt() {
        return endDt;
    }

    public void setEndDt(DateTime endDt) {
        this.endDt = endDt;
    }

    public DateTime getActualStartDt() {
        return actualStartDt;
    }

    public void setActualStartDt(DateTime actualStartDt) {
        this.actualStartDt = actualStartDt;
    }

    public DateTime getActualEndDt() {
        return actualEndDt;
    }

    public void setActualEndDt(DateTime actualEndDt) {
        this.actualEndDt = actualEndDt;
    }

    public Boolean getOnDrivingRange() {
        return isOnDrivingRange;
    }

    public void setOnDrivingRange(Boolean onDrivingRange) {
        isOnDrivingRange = onDrivingRange;
    }

    public Boolean getWithLoad() {
        return isWithLoad;
    }

    public void setWithLoad(Boolean withLoad) {
        isWithLoad = withLoad;
    }
}
