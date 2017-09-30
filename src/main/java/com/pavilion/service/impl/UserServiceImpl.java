package com.pavilion.service.impl;

import com.pavilion.dao.UserMapper;
import com.pavilion.domain.User;
import com.pavilion.service.UserService;
import com.pavilion.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    public User getUserById(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public User getLoginUser(String userName) {
        return userMapper.getLoginUser(userName);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        String pwd=user.getPassword();
        if(pwd.length()!=32){
            user.setPassword(MD5Util.encode(pwd));
        }

        return userMapper.updateByPrimaryKey(user);
    }
}
