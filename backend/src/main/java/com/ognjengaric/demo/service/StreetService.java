package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.Route;
import com.ognjengaric.demo.domain.Street;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StreetService {
    Page<Street> findPageable(int page, int size);
    List<Street> findAll();
    void save(Street street);
    void saveAll(Iterable<Street> streets);
    void createAndUpdateMultiple(Iterable<String> names, Route route);
}
