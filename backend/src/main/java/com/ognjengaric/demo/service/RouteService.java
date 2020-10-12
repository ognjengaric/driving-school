package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.dto.NewRouteDTO;
import com.ognjengaric.demo.enums.LicenceCategory;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RouteService {
    Integer save(NewRouteDTO newRouteDTO);
    Route findById(Integer id);
    List<Route> findAll();
    List<Route> findByCategory(LicenceCategory category);
    Page<Route> getPageable(int page, int size);
}
