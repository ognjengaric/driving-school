package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.enums.LicenceCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/licence")
public class LicenceCategoryController {

    @GetMapping
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(LicenceCategory.values());
    }

}
