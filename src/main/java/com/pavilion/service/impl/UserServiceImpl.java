package com.pavilion.service.impl;

import com.pavilion.dao.UserMapper;
import com.pavilion.domain.User;
import com.pavilion.service.UserService;
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
}
