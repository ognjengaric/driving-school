package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;

import java.util.List;

public interface DrivingClassService {
    List<DrivingClass> findByInstructor(Instructor instructor);

    List<DrivingClass> getClassesForUserScheduler(String user_id);
}
