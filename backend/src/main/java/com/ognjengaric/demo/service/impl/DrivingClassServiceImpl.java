package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.dto.AppointmentDTO;
import com.ognjengaric.demo.repository.DrivingClassRepository;
import com.ognjengaric.demo.service.DrivingClassService;
import com.ognjengaric.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrivingClassServiceImpl implements DrivingClassService {

    @Autowired
    DrivingClassRepository drivingClassRepository;

    @Autowired
    UserService userService;

    @Override
    public List<DrivingClass> findByInstructor(Instructor instructor) {
        return drivingClassRepository.findByInstructor(instructor);
    }

    @Override
    public Integer save(AppointmentDTO appointmentDTO, String user_id) {
        User user = userService.findById(user_id);
        Candidate candidate;
        Instructor instructor;

        try {
            candidate = (Candidate) user;
            instructor = candidate.getInstructor();
        } catch (ClassCastException e){
            return null;
        }

        LocalDateTime start = ZonedDateTime.parse(appointmentDTO.getStartDate()).toLocalDateTime();
        LocalDateTime end =  ZonedDateTime.parse(appointmentDTO.getEndDate()).toLocalDateTime();

        DrivingClass drivingClass = drivingClassRepository.saveAndFlush(new DrivingClass(candidate, instructor, start, end));

        return drivingClass.getId();
    }

    @Override
    public List<AppointmentDTO> getClassesForUserScheduler(String user_id){
        User user = userService.findById(user_id);
        Instructor instructor;

        try {
            instructor = ((Candidate) user).getInstructor();
        } catch (ClassCastException e){
            return null;
        }

        return findByInstructor(instructor)
                .stream()
                .map(AppointmentDTO::new)
                .collect(Collectors.toList());
    }
}
