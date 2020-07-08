package com.ognjengaric.demo.dto;

import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.enums.ClassStatusType;

import java.time.LocalDateTime;

public class AppointmentDTO {
    private String title;
    private String startDate;
    private String endDate;
    private int id;
    private ClassStatusType status;

    public AppointmentDTO(){}

    public AppointmentDTO(DrivingClass drivingClass) {
        this.title = drivingClass.getCandidate().getName() + " " + drivingClass.getCandidate().getSurname();
        this.startDate = drivingClass.getStart_dt().toString();
        this.endDate = drivingClass.getEnd_dt().toString();
        this.id = drivingClass.getId();
        this.status = drivingClass.getClassStatusType();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassStatusType getStatus() {
        return status;
    }

    public void setStatus(ClassStatusType status) {
        this.status = status;
    }
}
