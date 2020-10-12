package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.enums.ClassStatusType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class-status")
public class ClassStatusController {

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(ClassStatusType.values());
    }
}
