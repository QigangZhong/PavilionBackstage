package com.pavilion.model.dto;

import java.util.List;

public class PrivilegeDto {
    private int userId;
    private List<RoleDto> currentRoles;
    private List<RoleDto> allRoles;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<RoleDto> getCurrentRoles() {
        return currentRoles;
    }

    public void setCurrentRoles(List<RoleDto> currentRoles) {
        this.currentRoles = currentRoles;
    }

    public List<RoleDto> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<RoleDto> allRoles) {
        this.allRoles = allRoles;
    }
}
