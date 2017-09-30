package com.pavilion.domain;

import java.io.Serializable;

public class RoleMenu implements Serializable {
    private Integer id;

    private Integer roleId;

    private Integer menuId;

    private static final long serialVersionUID = 1L;

    public RoleMenu(Integer id, Integer roleId, Integer menuId) {
        this.id = id;
        this.roleId = roleId;
        this.menuId = menuId;
    }

    public RoleMenu() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}