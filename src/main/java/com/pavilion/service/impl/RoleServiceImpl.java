package com.pavilion.service.impl;

import com.pavilion.dao.RoleMapper;
import com.pavilion.domain.Role;
import com.pavilion.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<Role> getRolesByUserId(int userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    @Override
    public List<Role> getAll() {
        return roleMapper.getAll();
    }
}
