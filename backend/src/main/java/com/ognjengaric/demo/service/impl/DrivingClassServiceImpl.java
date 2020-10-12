package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.dto.AppointmentDTO;
import com.ognjengaric.demo.repository.DrivingClassRepository;
import com.ognjengaric.demo.service.DrivingClassService;
import com.ognjengaric.demo.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<DrivingClass> findAll() {
        return drivingClassRepository.findAll();
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

        DateTime start = DateTime.parse(appointmentDTO.getStartDate());
        DateTime end =  DateTime.parse(appointmentDTO.getEndDate());

        DrivingClass drivingClass = new DrivingClass(start, end);

        candidate.addDrivingClass(drivingClass);
        instructor.addDrivingClass(drivingClass);
        candidate.getDrivingSchool().addDrivingClass(drivingClass);

        drivingClass = drivingClassRepository.saveAndFlush(drivingClass);

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
