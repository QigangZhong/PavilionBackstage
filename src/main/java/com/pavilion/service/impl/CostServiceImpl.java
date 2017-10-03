package com.pavilion.service.impl;

import com.pavilion.dao.CostMapper;
import com.pavilion.model.Cost;
import com.pavilion.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostServiceImpl implements CostService {
    @Autowired
    CostMapper costMapper;

    @Override
    public Cost getFirst() {
        return costMapper.getFirst();
    }

    @Override
    public int update(Cost cost) {
        return costMapper.updateByPrimaryKey(cost);
    }
}
