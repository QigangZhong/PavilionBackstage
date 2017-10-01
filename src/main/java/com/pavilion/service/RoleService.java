package com.pavilion.service;

import com.pavilion.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> getRolesByUserId(int userId);
}
