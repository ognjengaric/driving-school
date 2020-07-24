package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.domain.Street;
import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.repository.RouteRepository;
import com.ognjengaric.demo.service.RouteService;
import com.ognjengaric.demo.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    StreetService streetService;

    @Override
    public Integer save(NewRouteDTO newRouteDTO) {
        Route route = new Route(newRouteDTO);
        streetService.createAndUpdateMultiple(newRouteDTO.getStreets(), route);
        Route saved = routeRepository.saveAndFlush(route);

       return saved.getId();
    }

    @Override
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route findById(Integer id){
        return routeRepository.findById(id).orElse(null);
    }
}
