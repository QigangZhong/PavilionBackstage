package com.pavilion.dao;

import com.pavilion.model.Material;
import org.apache.ibatis.annotations.*;

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

    @Select("select sum(total_price) as totalPrice from material")
    Double getTotalPrice();

    @Update("update material set ipsquantity=#{ipsquantity},total_price=(#{ipsquantity}*(select material_price.price from material_price where material_id=#{id} and material_price.unit<=#{ipsquantity} ORDER BY material_price.unit desc)) where id=#{id}")
    int updateIpsQty(@Param("id") int id, @Param("ipsquantity") int ipsquantity);

    @Insert("insert into material \n" +
            "(cpscode,cinvname,cinvstd,type,ipsquantity,total_price,create_time,last_update_time,deleted) \n" +
            "values(#{cpscode},#{cinvname},#{cinvstd},#{type},#{ipsquantity},#{totalPrice},datetime(CURRENT_TIMESTAMP,'localtime'),datetime(CURRENT_TIMESTAMP,'localtime'),0)")
    int add(Material mtl);

    @Select("select * from material where cpscode=#{cpscode}")
    Material getByCpscode(String cpscode);
}