package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.DrivingSchool;
import com.ognjengaric.demo.service.DrivingSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/drivingSchool")
public class DrivingSchoolController {


    @Value("${backend_uri}")
    private String backendUri;

    @Autowired
    DrivingSchoolService drivingSchoolService;

    @GetMapping("/{id}")
    public ResponseEntity getDrivingSchool(@PathVariable String id){
        DrivingSchool drivingSchool = drivingSchoolService.findById(id);

        if(drivingSchool == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(drivingSchool);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity postDrivingSchool(@RequestBody DrivingSchool drivingSchool){

        if(drivingSchoolService.count() != 0)
            return ResponseEntity.badRequest().build();

        drivingSchoolService.save(drivingSchool);

        URI uri = URI.create(backendUri + "/drivingSchool/" + drivingSchool.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/exists")
    public ResponseEntity existsDrivingSchool(){
        if(drivingSchoolService.count() == 1)
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
