package com.pavilion.dao;

import com.pavilion.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    @Select("select * from role where id=#{id}")
    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Role record);

    @Select("select r.* from role r join user_role ur on r.id=ur.role_id where ur.user_id=#{userId}")
    List<Role> getRolesByUserId(int userId);

    @Select("select * from role")
    List<Role> getAll();
}