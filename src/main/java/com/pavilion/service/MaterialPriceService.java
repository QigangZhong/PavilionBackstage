package com.pavilion.service;

import com.pavilion.model.Material;
import com.pavilion.model.MaterialPrice;

import java.util.List;

public interface MaterialPriceService {

    List<MaterialPrice> getMaterialPrices(int materialId);

    int add(MaterialPrice mp);

    int deleteById(int mtlPriceId);
}
