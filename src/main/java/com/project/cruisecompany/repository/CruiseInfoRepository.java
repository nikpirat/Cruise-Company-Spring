package com.project.cruisecompany.repository;

import com.project.cruisecompany.model.CruiseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CruiseInfoRepository extends JpaRepository<CruiseInfo, Integer> {
    List<CruiseInfo> findAllByUserId(int id);
}
