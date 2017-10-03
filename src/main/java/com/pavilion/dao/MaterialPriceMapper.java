package com.pavilion.dao;

import com.pavilion.model.MaterialPrice;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MaterialPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MaterialPrice record);

    int insertSelective(MaterialPrice record);

    MaterialPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MaterialPrice record);

    int updateByPrimaryKey(MaterialPrice record);

    @Select("select * from material_price where material_id=#{materialId}")
    List<MaterialPrice> getMaterialPrices(int materialId);
}