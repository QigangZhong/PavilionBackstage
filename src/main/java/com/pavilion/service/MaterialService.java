package com.pavilion.service;

import com.pavilion.model.Material;

import java.util.List;

public interface MaterialService {
    int getCount();

    List<Material> getPagedMaterials(int page, int limit);

    int getSearchCount(String cpscode,String cinvname,String cinvstd,String type);

    List<Material> getSearchPagedMaterials(int page, int limit, String cpscode,String cinvname,String cinvstd,String type);

    int updateIpsQty(int mtlId,int ipsQty);

    Double getTotalPrice();

    int add(Material mtl);

    Material getByCpscode(String cpscode);
}
