package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import com.ognjengaric.demo.dto.AppointmentDTO;

import java.util.List;

public interface DrivingClassService {
    List<DrivingClass> findByInstructor(Instructor instructor);
    Integer save(AppointmentDTO appointmentDTO, String user_id);

    List<AppointmentDTO> getClassesForUserScheduler(String user_id);
}
