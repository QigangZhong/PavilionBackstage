package com.pavilion.dao;

import com.pavilion.domain.UserRole;
import org.apache.ibatis.annotations.Delete;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    @Delete("delete from user_role where user_id=#{userId}")
    int deleteByUserId(int userId);
}