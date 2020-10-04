package com.ognjengaric.demo.repository;

import com.ognjengaric.demo.domain.DrivingSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrivingSchoolRepository extends JpaRepository<DrivingSchool, String> {
    Optional<DrivingSchool> findById(String id);
}
