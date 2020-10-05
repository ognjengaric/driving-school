package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.DrivingSchool;
import org.springframework.data.domain.Page;

public interface DrivingSchoolService {
    DrivingSchool findById(String id);
    Page<DrivingSchool> getPageable(int page, int size);
    void save(DrivingSchool drivingSchool);
}
