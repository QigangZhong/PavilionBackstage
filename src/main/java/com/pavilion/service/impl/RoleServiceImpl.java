package com.pavilion.service.impl;

import com.pavilion.dao.RoleMapper;
import com.pavilion.domain.Role;
import com.pavilion.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public String getRoleNameById(int id) {
        Role role=roleMapper.selectByPrimaryKey(id);
        return role.getName();
    }
}
