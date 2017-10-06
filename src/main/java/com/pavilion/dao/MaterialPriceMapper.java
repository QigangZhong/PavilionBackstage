package com.pavilion.dao;

import com.pavilion.model.MaterialPrice;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("insert into material_price \n" +
            "(material_id,unit,price,create_time,last_update_time,deleted) \n" +
            "values(#{materialId},#{unit},#{price},datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0)")
    int add(MaterialPrice mp);
}