package com.pavilion.service.impl;

import com.pavilion.dao.UserRoleMapper;
import com.pavilion.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UseRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public int deleteByUserId(int userId) {
        return userRoleMapper.deleteByUserId(userId);
    }
}
