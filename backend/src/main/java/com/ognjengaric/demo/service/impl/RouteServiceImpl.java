package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.repository.RouteRepository;
import com.ognjengaric.demo.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Override
    public void save(Route route) {
        routeRepository.save(route);
    }
}
