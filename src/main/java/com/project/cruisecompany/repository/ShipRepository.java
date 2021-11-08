package com.project.cruisecompany.repository;

import com.project.cruisecompany.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {
}
