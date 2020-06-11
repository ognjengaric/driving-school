package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.DrivingSchool;
import com.ognjengaric.demo.repository.DrivingSchoolRepository;
import com.ognjengaric.demo.service.DrivingSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrivingSchoolServiceImpl implements DrivingSchoolService {

    @Autowired
    DrivingSchoolRepository drivingSchoolRepository;

    @Override
    public DrivingSchool findById(String id) {
        return drivingSchoolRepository.findById(id).orElse(null);
    }

    @Override
    public long count() {
        return drivingSchoolRepository.count();
    }

    @Override
    public void save(DrivingSchool drivingSchool){
        drivingSchoolRepository.save(drivingSchool);
    }
}
