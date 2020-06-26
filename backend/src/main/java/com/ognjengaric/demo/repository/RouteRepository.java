package com.ognjengaric.demo.repository;

import com.ognjengaric.demo.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
