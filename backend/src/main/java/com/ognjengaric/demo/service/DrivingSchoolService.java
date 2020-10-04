package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.DrivingSchool;

public interface DrivingSchoolService {
    DrivingSchool findById(String id);
    void save(DrivingSchool drivingSchool);
}
