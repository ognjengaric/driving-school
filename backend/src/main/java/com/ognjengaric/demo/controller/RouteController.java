package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.domain.Street;
import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Value("${backend_uri}")
    private String backendUri;

    @Autowired
    RouteService routeService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<Route> routes = routeService.findAll();
        return ResponseEntity.ok(routes);
    }

    @GetMapping(params = { "page", "size" })
    public Page<Route> getPageable(@RequestParam("page") int page, @RequestParam("size") int size){
        return routeService.getPageable(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id){
        Route route = routeService.findById(Integer.parseInt(id));

        if(route == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(route);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> post(@RequestBody NewRouteDTO newRouteDTO){
        Integer id = routeService.save(newRouteDTO);

        URI uri = URI.create(backendUri + "/route/" + id);

        return ResponseEntity.created(uri).build();
    }
}
