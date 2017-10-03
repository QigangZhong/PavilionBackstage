package com.pavilion.service.impl;

import com.pavilion.dao.MaterialMapper;
import com.pavilion.model.Material;
import com.pavilion.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    MaterialMapper materialMapper;

    @Override
    public int getCount() {
        return materialMapper.getCount();
    }

    @Override
    public List<Material> getPagedMaterials(int page, int limit) {
        int offset=(page-1)*limit;
        return materialMapper.getPagedMaterials(offset,limit);
    }

    @Override
    public int getSearchCount(String cpscode,String cinvname,String cinvstd,String type) {
        return materialMapper.getSearchCount(cpscode,cinvname,cinvstd,type);
    }

    @Override
    public List<Material> getSearchPagedMaterials(int page, int limit, String cpscode,String cinvname,String cinvstd,String type) {
        int offset=(page-1)*limit;
        return materialMapper.getSearchPagedMaterials(offset,limit,cpscode,cinvname,cinvstd,type);
    }
}
