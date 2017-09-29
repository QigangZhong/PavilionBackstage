package com.pavilion.controllers;

import com.pavilion.dao.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/user/getUserNameById")
    public String getUserNameById(){
        return userMapper.selectByPrimaryKey(1).getUsername();
    }
}
