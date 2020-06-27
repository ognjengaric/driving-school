package com.ognjengaric.demo.domain;

import com.ognjengaric.demo.enums.ClassStatusType;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime start;

    @Column
    private LocalDateTime end;

    public DrivingClass() {
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
