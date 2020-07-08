package com.ognjengaric.demo.repository;

import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrivingClassRepository extends JpaRepository<DrivingClass, Integer> {
    List<DrivingClass> findByInstructor(Instructor instructor);
}
