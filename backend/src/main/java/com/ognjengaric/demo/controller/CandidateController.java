package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.dto.CourseProgressDTO;
import com.ognjengaric.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    UserService userService;

    @GetMapping("/course")
    public ResponseEntity<?> get(Principal principal){

        CourseProgressDTO dto = userService.getCourseProgress(principal.getName());

        return ResponseEntity.ok(dto);
    }
}
