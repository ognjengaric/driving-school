package com.ognjengaric.demo.dto;

import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.enums.ClassStatusType;
import com.ognjengaric.demo.enums.LicenceCategory;

public class ClassTableViewDto {
    private String startDate;
    private String endDate;
    private String id;
    private ClassStatusType status;
    private String instructor;
    private String candidate;
    private String drivingSchool;
    private Boolean isOnDrivingRange;
    private Boolean isWithLoad;
    private Route route;
    private LicenceCategory licenceCategory;

    public ClassTableViewDto() {
    }

    public ClassTableViewDto(DrivingClass drivingClass) {
        this.startDate = drivingClass.getStartDt().toString();
        this.endDate = drivingClass.getEndDt().toString();
        this.id = drivingClass.getId().toString();
        this.status = drivingClass.getStatus();
        this.instructor = drivingClass.getInstructor().getName() + " " +  drivingClass.getInstructor().getSurname();
        this.candidate = drivingClass.getCandidate().getName() + " " +  drivingClass.getCandidate().getSurname();
        this.drivingSchool = drivingClass.getDrivingSchool().getName();
        this.isOnDrivingRange = drivingClass.getOnDrivingRange();
        this.isWithLoad = drivingClass.getWithLoad();
        this.route = drivingClass.getRoute();
        this.licenceCategory = drivingClass.getCandidate().getCurrentLicence();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClassStatusType getStatus() {
        return status;
    }

    public void setStatus(ClassStatusType status) {
        this.status = status;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getDrivingSchool() {
        return drivingSchool;
    }

    public void setDrivingSchool(String drivingSchool) {
        this.drivingSchool = drivingSchool;
    }

    public Boolean getIsOnDrivingRange() {
        return isOnDrivingRange;
    }

    public void setIsOnDrivingRange(Boolean onDrivingRange) {
        isOnDrivingRange = onDrivingRange;
    }

    public Boolean getIsWithLoad() {
        return isWithLoad;
    }

    public void setIsWithLoad(Boolean withLoad) {
        isWithLoad = withLoad;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public LicenceCategory getLicenceCategory() {
        return licenceCategory;
    }

    public void setLicenceCategory(LicenceCategory licenceCategory) {
        this.licenceCategory = licenceCategory;
    }
}
