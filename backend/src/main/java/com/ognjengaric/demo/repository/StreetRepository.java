package com.ognjengaric.demo.repository;

import com.ognjengaric.demo.domain.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, String> {
    Optional<Street> findByName(String name);
}
