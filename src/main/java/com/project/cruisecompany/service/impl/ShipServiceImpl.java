package com.project.cruisecompany.service.impl;

import com.project.cruisecompany.model.CruiseInfo;
import com.project.cruisecompany.model.Ship;
import com.project.cruisecompany.repository.ShipRepository;
import com.project.cruisecompany.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipServiceImpl implements ShipService {
    private final ShipRepository shipRepository;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public List<Ship> findAllUserShips(List<CruiseInfo> cruiseInfos) {
        List<Ship> userShips = new ArrayList<>();
        for (CruiseInfo cruise : cruiseInfos) {
            userShips.add(shipRepository.getById((long) cruise.getShipId()));
        }
        return userShips;
    }

    public List<Ship> findAll() {
        return shipRepository.findAll();
    }

    @Override
    public Ship findById(Long id) {
        return shipRepository.getById(id);
    }
}
