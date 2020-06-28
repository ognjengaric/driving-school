package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.service.UserService;
import com.ognjengaric.demo.util.PdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    UserService userService;


    @GetMapping(value = "/users")
    public ResponseEntity<InputStreamResource> usersReport() {
        List<User> users = userService.findAll();

        ByteArrayInputStream bis = PdfGenerator.usersReport(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=users.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
