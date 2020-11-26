package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.DrivingSchool;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DrivingSchoolService {
    DrivingSchool findById(String id);
    List<DrivingSchool> get();
    Page<DrivingSchool> getPageable(int page, int size);
    void save(DrivingSchool drivingSchool);
}
