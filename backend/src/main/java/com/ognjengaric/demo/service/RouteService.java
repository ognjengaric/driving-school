package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.dto.NewRouteDTO;

import java.util.List;

public interface RouteService {
    Integer save(NewRouteDTO newRouteDTO);
    Route findById(Integer id);
    List<Route> findAll();
}
