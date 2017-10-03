package com.pavilion.service;

import com.pavilion.model.Cost;

public interface CostService {
    Cost getFirst();

    int update(Cost cost);
}
