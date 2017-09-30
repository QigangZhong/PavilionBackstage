package com.pavilion.dao;

import com.pavilion.domain.Role;
import org.apache.ibatis.annotations.Select;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    @Select("select * from role where id=#{id}")
    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}