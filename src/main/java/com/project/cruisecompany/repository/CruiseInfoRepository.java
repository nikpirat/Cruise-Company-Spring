package com.project.cruisecompany.repository;

import com.project.cruisecompany.model.CruiseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CruiseInfoRepository extends JpaRepository<CruiseInfo, Long> {
}
