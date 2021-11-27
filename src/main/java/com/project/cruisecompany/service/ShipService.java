package com.project.cruisecompany.service;

import com.project.cruisecompany.model.CruiseInfo;
import com.project.cruisecompany.model.Ship;

import java.util.List;

public interface ShipService {
    List<Ship> findAllUserShips(List<CruiseInfo> cruiseInfos);
    List<Ship> findAll();
    Ship findById(Long id);
}
