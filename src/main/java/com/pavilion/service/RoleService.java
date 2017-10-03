package com.pavilion.service;

import com.pavilion.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesByUserId(int userId);

    List<Role> getAll();
}
