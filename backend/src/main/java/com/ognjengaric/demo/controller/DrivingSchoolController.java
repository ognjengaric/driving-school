package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.DrivingSchool;
import com.ognjengaric.demo.service.DrivingSchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/driving-school")
public class DrivingSchoolController {


    @Value("${backend_uri}")
    private String backendUri;

    @Autowired
    DrivingSchoolService drivingSchoolService;

//    @GetMapping(params = { "page", "size", "sort", "direction" })
//    public Page<DrivingSchool> getPageable(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sort") String sort, @RequestParam("direction") String direction){
//        return drivingSchoolService.getPageable(page, size, sort, direction);
//    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok(drivingSchoolService.get());
    }

        @GetMapping(params = { "page", "size" })
    public Page<DrivingSchool> getPageable(@RequestParam("page") int page, @RequestParam("size") int size){
        return drivingSchoolService.getPageable(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDrivingSchool(@PathVariable String id){
        DrivingSchool drivingSchool = drivingSchoolService.findById(id);

        if(drivingSchool == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(drivingSchool);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> postDrivingSchool(@RequestBody DrivingSchool drivingSchool){
        drivingSchoolService.save(drivingSchool);

        URI uri = URI.create(backendUri + "/drivingSchool/" + drivingSchool.getId());

        return ResponseEntity.created(uri).build();
    }

}
