package com.pavilion.dao;

import com.pavilion.domain.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenu record);

    RoleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RoleMenu record);

    @Select("select * from role_menu where role_id=#{roleId} and menu_id=#{menuId}")
    List<RoleMenu> selectByRoleIdAndMenuId(@Param("roleId") int roleId, @Param("menuId") int menuId);

}