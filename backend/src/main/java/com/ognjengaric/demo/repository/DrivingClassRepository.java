package com.ognjengaric.demo.repository;

import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DrivingClassRepository extends JpaRepository<DrivingClass, Integer> {
    Page<DrivingClass> findByInstructor(Instructor instructor, Pageable pageable);
    List<DrivingClass> findByInstructor(Instructor instructor);
    Page<DrivingClass> findByCandidate(Candidate candidate, Pageable pageable);
    List<DrivingClass> findByCandidate(Candidate candidate);
}
