package com.project.cruisecompany.service;


import com.project.cruisecompany.model.CruiseInfo;

import java.util.List;

public interface CruiseInfoService {
    List<CruiseInfo> findAllByUserId(int id);
    CruiseInfo create(CruiseInfo cruiseInfo);
}
