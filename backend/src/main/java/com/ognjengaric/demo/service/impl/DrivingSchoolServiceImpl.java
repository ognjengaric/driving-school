package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.DrivingSchool;
import com.ognjengaric.demo.domain.Street;
import com.ognjengaric.demo.repository.DrivingSchoolRepository;
import com.ognjengaric.demo.service.DrivingSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DrivingSchoolServiceImpl implements DrivingSchoolService {

    @Autowired
    DrivingSchoolRepository drivingSchoolRepository;

    private static String DEFAULT_SORT_COLUMN = "name";

    @Override
    public DrivingSchool findById(String id) {
        return drivingSchoolRepository.findById(id).orElse(null);
    }

    @Override
    public List<DrivingSchool> get() {
        return drivingSchoolRepository.findAll();
    }

    @Override
    public void save(DrivingSchool drivingSchool){
        Collections.sort(drivingSchool.getAvailableCategories());
        drivingSchoolRepository.save(drivingSchool);
    }

    @Override
    public Page<DrivingSchool> getPageable(int page, int size) {
        //Sort s = direction.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending();
        Pageable pageable = PageRequest.of(page, size, Sort.by(DEFAULT_SORT_COLUMN));

        return drivingSchoolRepository.findAll(pageable);
    }
}
