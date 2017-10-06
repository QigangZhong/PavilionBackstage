package com.pavilion.service.impl;

import com.pavilion.dao.MaterialPriceMapper;
import com.pavilion.model.MaterialPrice;
import com.pavilion.service.MaterialPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialPriceServiceImpl implements MaterialPriceService {
    @Autowired
    MaterialPriceMapper materialPriceMapper;

    @Override
    public List<MaterialPrice> getMaterialPrices(int materialId) {
        return materialPriceMapper.getMaterialPrices(materialId);
    }

    @Override
    public int add(MaterialPrice mp) {
        return materialPriceMapper.add(mp);
    }
}
