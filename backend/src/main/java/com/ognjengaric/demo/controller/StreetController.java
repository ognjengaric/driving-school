package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.Street;
import com.ognjengaric.demo.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/street")
public class StreetController {

    @Autowired
    StreetService streetService;

    @GetMapping(params = { "page", "size" })
    public Page<Street> getPageable(@RequestParam("page") int page, @RequestParam("size") int size){
        return streetService.getPageable(page, size);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStreet(@RequestBody Street street){
        streetService.save(street);
        return ResponseEntity.ok().build();
    }
}
