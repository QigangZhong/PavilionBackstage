package com.pavilion.controllers;

import com.pavilion.model.Menu;
import com.pavilion.model.User;
import com.pavilion.model.dto.MenuDto;
import com.pavilion.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    MenuService menuService;

    @RequestMapping("/")
    public String index(HttpServletRequest request,Model model){
        User user= (User) request.getSession().getAttribute("user");

        if(user!=null) {
            //加载菜单信息
            //1. 先加载一级菜单信息
            List<Menu> firstLevelMenus = menuService.getFirstLevelMenus(user.getId());

            List<MenuDto> menuList = new ArrayList<>();
            //2. 加载二级菜单信息
            for (int i = 0; i < firstLevelMenus.size(); i++) {
                Menu tmp1 = firstLevelMenus.get(i);

                MenuDto dto1 = new MenuDto(tmp1.getId(), tmp1.getUrl(), tmp1.getMethod(), tmp1.getName(), tmp1.getLevel(), tmp1.getParentId());

                List<Menu> secondLevelMenus = menuService.getChildrenMenus(user.getId(),tmp1.getId());
                List<MenuDto> menu2List = new ArrayList<>();
                for (int j = 0; j < secondLevelMenus.size(); j++) {
                    Menu tmp2 = secondLevelMenus.get(j);
                    MenuDto dto2 = new MenuDto(tmp2.getId(), tmp2.getUrl(), tmp2.getMethod(), tmp2.getName(), tmp2.getLevel(), tmp2.getParentId());
                    menu2List.add(dto2);
                }
                dto1.setChildren(menu2List);

                menuList.add(dto1);
            }

            model.addAttribute("menuList",menuList);
        }

        return "home/index";
    }

    @RequestMapping("/home/right")
    public String body(){
        return "home/right";
    }
}
