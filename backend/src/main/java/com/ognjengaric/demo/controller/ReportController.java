package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.DrivingClass;
import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.domain.Street;
import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.service.DrivingClassService;
import com.ognjengaric.demo.service.RouteService;
import com.ognjengaric.demo.service.StreetService;
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

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {
    @Autowired
    UserService userService;

    @Autowired
    DrivingClassService drivingClassService;

    @Autowired
    StreetService streetService;

    @Autowired
    RouteService routeService;


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

    @GetMapping(value = "/classes")
    public ResponseEntity<InputStreamResource> classesReport() {
        List<DrivingClass> drivingClasses = drivingClassService.findAll();

        ByteArrayInputStream bis = PdfGenerator.classesReport(drivingClasses);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=classes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/streets")
    public ResponseEntity<InputStreamResource> routesReport() {
        List<Street> streets = streetService.findAll();

        ByteArrayInputStream bis = PdfGenerator.streetsReport(streets);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=routes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/routes")
    public ResponseEntity<InputStreamResource> streetsReport() {
        List<Route> routes = routeService.findAll();

        ByteArrayInputStream bis = PdfGenerator.routesReport(routes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=routes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
