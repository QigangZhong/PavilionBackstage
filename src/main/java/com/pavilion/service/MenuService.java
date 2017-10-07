package com.pavilion.service;

import com.pavilion.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getFirstLevelMenus(int userId);

    List<Menu> getChildrenMenus(int userId,Integer parentId);
}
