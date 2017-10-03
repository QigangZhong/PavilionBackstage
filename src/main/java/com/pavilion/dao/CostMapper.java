package com.pavilion.dao;

import com.pavilion.model.Cost;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cost record);

    @Update("update cost set labor=#{labor},wear=#{wear},management=#{management},sale=#{sale},last_update_time=datetime(current_timestamp,'localtime') where id=#{id}")
    int updateByPrimaryKey(Cost record);

    @Select("select * from cost limit 0,1")
    Cost getFirst();
}