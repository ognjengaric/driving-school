package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.dto.AppointmentDTO;
import com.ognjengaric.demo.dto.ClassTableViewDto;
import com.ognjengaric.demo.service.DrivingClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id){

        ClassTableViewDto dto = drivingClassService.findById(id);

        if(dto == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(dto);
    };

    @PostMapping
    public ResponseEntity<?> postNewClass(@RequestBody AppointmentDTO appointmentDTO, Principal principal){
        Integer id = drivingClassService.save(appointmentDTO, Integer.parseInt(principal.getName()));
        if(id == null)
            return ResponseEntity.badRequest().build();

        URI uri = URI.create(backendUri + "/route/" + id);

        return ResponseEntity.created(uri).build();
    }

    @GetMapping(path = "/my", params = { "page", "size" })
    public ResponseEntity<?> getUserClassesPageable(Principal principal, @RequestParam("page") int page, @RequestParam("size") int size){
        Page<ClassTableViewDto> list = drivingClassService.getMyClasses(Integer.parseInt(principal.getName()), page, size);

        if(list == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/appointments")
    public ResponseEntity<?> getClassesForUserScheduler(Principal principal){

        List<AppointmentDTO> drivingClasses = drivingClassService.getClassesForUserScheduler(Integer.parseInt(principal.getName()));

        if(drivingClasses == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(drivingClasses);
    }

    @PatchMapping("/status")
    public ResponseEntity<?> setClassStatus(@RequestBody ClassTableViewDto dto){
        if(drivingClassService.setStatus(Integer.parseInt(dto.getId()), dto.getStatus()))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

    @PatchMapping("/complete")
    public ResponseEntity<?> completeClass(@RequestBody ClassTableViewDto dto){
        if(drivingClassService.completeClass(dto))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }

}
