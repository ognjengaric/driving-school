package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.dto.NewRouteDTO;

public interface RouteService {
    Integer save(NewRouteDTO newRouteDTO);
    Route findById(Integer id);
}
