package com.pavilion.service.impl;

import com.pavilion.dao.MenuMapper;
import com.pavilion.model.Menu;
import com.pavilion.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getFirstLevelMenus(int userId) {
        return menuMapper.getFirstLevelMenus(userId);
    }

    @Override
    public List<Menu> getChildrenMenus(int userId,Integer parentId) {
        return menuMapper.getSecondLevelMenus(userId,parentId);
    }
}
