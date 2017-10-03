package com.pavilion.dao;

import com.pavilion.model.Material;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface MaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    @Select("select count(1) from material")
    int getCount();

    @Select("select * from material limit #{offset},#{limit}")
    List<Material> getPagedMaterials(@Param("offset") int offset, @Param("limit") int limit);

    @SelectProvider(type=MaterialSqlProvider.class,method="getSearchCount")
    int getSearchCount(String cpscode, String cinvname, String cinvstd, String type);

    @SelectProvider(type=MaterialSqlProvider.class,method="getSearchPagedMaterials")
    List<Material> getSearchPagedMaterials(int offset, int limit, String cpscode, String cinvname, String cinvstd, String type);
}