package com.ognjengaric.demo.repository;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.enums.LicenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Integer> {
    List<Route> findByCategoryType(LicenceCategory category);
}
