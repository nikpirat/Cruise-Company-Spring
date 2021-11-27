package com.project.cruisecompany.service.impl;

import com.project.cruisecompany.model.CruiseInfo;
import com.project.cruisecompany.repository.CruiseInfoRepository;
import com.project.cruisecompany.service.CruiseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CruiseInfoServiceImpl implements CruiseInfoService {
    private final CruiseInfoRepository cruiseInfoRepository;

    @Autowired
    public CruiseInfoServiceImpl(CruiseInfoRepository cruiseInfoRepository) {
        this.cruiseInfoRepository = cruiseInfoRepository;
    }


    @Override
    public List<CruiseInfo> findAllByUserId(int id) {
        return cruiseInfoRepository.findAllByUserId(id);
    }

    @Override
    public CruiseInfo create(CruiseInfo cruiseInfo) {
        return cruiseInfoRepository.save(cruiseInfo);
    }
}
