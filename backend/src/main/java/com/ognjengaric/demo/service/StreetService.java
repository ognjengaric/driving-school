package com.ognjengaric.demo.service;

import com.ognjengaric.demo.domain.Street;
import org.springframework.data.domain.Page;

public interface StreetService {
    Page<Street> findPageable(int page, int size);
    void save(Street street);
}
