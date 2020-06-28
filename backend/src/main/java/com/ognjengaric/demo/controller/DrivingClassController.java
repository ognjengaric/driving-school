package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.dto.AppointmentDTO;
import com.ognjengaric.demo.service.DrivingClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/class")
public class DrivingClassController {

    @Value("${backend_uri}")
    private String backendUri;

    @Autowired
    DrivingClassService drivingClassService;

    @PostMapping
    public ResponseEntity<?> postNewClass(@RequestBody AppointmentDTO appointmentDTO, Principal principal){
        Integer id = drivingClassService.save(appointmentDTO, principal.getName());
        if(id == null)
            return ResponseEntity.badRequest().build();

        URI uri = URI.create(backendUri + "/route/" + id);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/appointments")
    public ResponseEntity<?> getClassesForUserScheduler(Principal principal){

        List<AppointmentDTO> drivingClasses = drivingClassService.getClassesForUserScheduler(principal.getName());

        if(drivingClasses == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(drivingClasses);
    }

}
