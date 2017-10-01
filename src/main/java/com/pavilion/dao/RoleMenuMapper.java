package com.pavilion.dao;

import com.pavilion.domain.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    @Select("select rm.* from role_menu rm join user_role ur on rm.role_id=ur.role_id where rm.menu_id=#{menuId} and ur.user_id=#{userId}")
    List<RoleMenu> selectByUserIdAndMenuId(@Param("userId") int roleId, @Param("menuId") int menuId);
}