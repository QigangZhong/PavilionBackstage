package com.pavilion.service;

public interface UserRoleService {
    int deleteByUserId(int userId);

    int insert(Integer id, int roleId);
}
