package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.Candidate;
import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Instructor;
import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.service.DrivingClassService;
import com.ognjengaric.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/class")
public class DrivingClassController {

    @Autowired
    DrivingClassService drivingClassService;

    @GetMapping("/appointments")
    public ResponseEntity<?> getClassesForUserScheduler(Principal principal){

        List<DrivingClass> drivingClasses = drivingClassService.getClassesForUserScheduler(principal.getName());

        if(drivingClasses == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().build();
    }

}
