package com.pavilion.dao;

import com.pavilion.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    @Select("select * from menu where id=#{id}")
    Menu selectByPrimaryKey(Integer id);

    @Select("select * from menu where url=#{url} and method=#{method}")
    Menu selectByUrl(@Param("url") String url, @Param("method") String method);

    int updateByPrimaryKey(Menu record);

    @Select("select DISTINCT menu.* from menu JOIN role_menu on menu.id=role_menu.menu_id\n" +
            "join user_role on role_menu.role_id=user_role.role_id\n" +
            " where user_role.user_id=#{userId} and menu.show=1 and menu.level=1")
    List<Menu> getFirstLevelMenus(int userId);

    @Select("select DISTINCT menu.* from menu JOIN role_menu on menu.id=role_menu.menu_id\n" +
            "join user_role on role_menu.role_id=user_role.role_id\n" +
            " where user_role.user_id=#{userId} and menu.show=1 and menu.level=2 and menu.parent_id=#{parentId}")
    List<Menu> getSecondLevelMenus(@Param("userId") int userId,@Param("parentId") Integer parentId);
}