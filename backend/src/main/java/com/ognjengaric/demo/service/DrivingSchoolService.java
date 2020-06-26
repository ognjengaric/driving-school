package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.DrivingSchool;

public interface DrivingSchoolService {
    DrivingSchool findById(String id);
    DrivingSchool getSchool();
    long count();
    void save(DrivingSchool drivingSchool);
}
