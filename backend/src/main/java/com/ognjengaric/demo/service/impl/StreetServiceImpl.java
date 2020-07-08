package com.ognjengaric.demo.service.impl;

import com.ognjengaric.demo.domain.Street;
import com.ognjengaric.demo.repository.StreetRepository;
import com.ognjengaric.demo.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    StreetRepository streetRepository;

    private static String DEFAULT_SORT_COLUMN = "name";

    @Override
    public Page<Street> findPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(DEFAULT_SORT_COLUMN));
        return streetRepository.findAll(pageable);
    }

    @Override
    public List<Street> findAll() {
        List<Street> streets = streetRepository.findAll();
        streets.sort(Comparator.comparing(Street::getName));
        return streets;
    }

    @Override
    public void save(Street street) {
        streetRepository.save(street);
    }
}
