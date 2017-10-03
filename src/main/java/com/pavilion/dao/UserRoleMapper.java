package com.pavilion.dao;

import com.pavilion.model.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

public interface UserRoleMapper {
    @Insert("insert into user_role values(#{userId},#{roleId})")
    int insert(UserRole record);

    @Delete("delete from user_role where user_id=#{userId}")
    int deleteByUserId(int userId);
}