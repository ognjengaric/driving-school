package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import com.ognjengaric.demo.dto.AppointmentDTO;
import com.ognjengaric.demo.dto.ClassTableViewDto;

import com.ognjengaric.demo.enums.ClassStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface DrivingClassService {
    List<DrivingClass> findByInstructor(Instructor instructor);
    List<DrivingClass> findByCandidate(Candidate candidate);
    Page<DrivingClass> findByInstructor(Instructor instructor, Pageable pageable);
    Page<DrivingClass> findByCandidate(Candidate candidate, Pageable pageable);
    Integer save(AppointmentDTO appointmentDTO, Integer user_id);
    ClassTableViewDto findById(Integer id);
    List<DrivingClass> findAll();
    boolean setStatus(Integer id, ClassStatusType status);
    boolean completeClass(ClassTableViewDto dto);

    List<AppointmentDTO> getClassesForUserScheduler(Integer user_id);
    Page<ClassTableViewDto> getMyClasses(Integer id, int page, int size);
}
