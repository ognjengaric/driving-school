package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.repository.RouteRepository;
import com.ognjengaric.demo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public Integer save(NewRouteDTO newRouteDTO) {
       Route route = routeRepository.saveAndFlush(new Route(newRouteDTO));
       return route.getId();
    }

    @Override
    public Route findById(Integer id){
        return routeRepository.findById(id).orElse(null);
    }
}
