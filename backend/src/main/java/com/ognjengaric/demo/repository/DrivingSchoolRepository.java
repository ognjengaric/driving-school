package com.ognjengaric.demo.repository;

import com.ognjengaric.demo.domain.DrivingSchool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivingSchoolRepository extends JpaRepository<DrivingSchool, Long> {
    DrivingSchool findFirstById(Long id);
}
