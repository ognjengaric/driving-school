package com.ognjengaric.demo.repository;

import com.ognjengaric.demo.domain.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street, String> {
}
