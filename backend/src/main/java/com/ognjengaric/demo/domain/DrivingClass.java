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

    @Column
    private ClassStatusType classStatusType;

    @ManyToOne
    private Route route;

    @Column
    private DateTime start_dt;

    @Column
    private DateTime end_dt;

    public DrivingClass() {
    }

    public DrivingClass(Candidate candidate, Instructor instructor, DateTime start_dt, DateTime end_dt) {
        this.classStatusType = ClassStatusType.PENDING;
        this.candidate = candidate;
        this.instructor = instructor;
        this.start_dt = start_dt;
        this.end_dt = end_dt;
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

    public ClassStatusType getClassStatusType() {
        return classStatusType;
    }

    public void setClassStatusType(ClassStatusType classStatusType) {
        this.classStatusType = classStatusType;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public DateTime getStart_dt() {
        return start_dt;
    }

    public void setStart_dt(DateTime start_dt) {
        this.start_dt = start_dt;
    }

    public DateTime getEnd_dt() {
        return end_dt;
    }

    public void setEnd_dt(DateTime end_dt) {
        this.end_dt = end_dt;
    }
}
