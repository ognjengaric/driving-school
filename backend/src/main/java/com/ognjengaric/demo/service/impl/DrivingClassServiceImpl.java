package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.repository.DrivingClassRepository;
import com.ognjengaric.demo.service.DrivingClassService;
import com.ognjengaric.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<DrivingClass> getClassesForUserScheduler(String user_id){
        User user = userService.findById(user_id);
        Instructor instructor;

        try {
            instructor = ((Candidate) user).getInstructor();
        } catch (ClassCastException e){
            return null;
        }

        return findByInstructor(instructor);
    }
}
