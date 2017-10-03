package com.pavilion.dao;

import com.pavilion.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    @Select("select * from menu where id=#{id}")
    Menu selectByPrimaryKey(Integer id);

    @Select("select * from menu where url=#{url} and method=#{method}")
    Menu selectByUrl(@Param("url") String url, @Param("method") String method);

    int updateByPrimaryKey(Menu record);
}