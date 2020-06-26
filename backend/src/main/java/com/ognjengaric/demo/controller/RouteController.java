package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Value("${backend_uri}")
    private String backendUri;

    @Autowired
    RouteService routeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoute(@PathVariable String id){
        Route route = routeService.findById(Integer.parseInt(id));

        if(route == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(route);
    }

    @PostMapping
    public ResponseEntity<?> postRoute(@RequestBody NewRouteDTO newRouteDTO){
        Integer id = routeService.save(newRouteDTO);

        URI uri = URI.create(backendUri + "/route/" + id);

        return ResponseEntity.created(uri).build();
    }
}
