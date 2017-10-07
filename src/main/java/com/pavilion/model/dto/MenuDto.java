package com.pavilion.model.dto;

import java.io.Serializable;
import java.util.List;

public class MenuDto implements Serializable {
    private Integer id;

    private String url;

    private String method;

    private String name;

    private String description;

    private int show;

    private int parentId;

    private int level;

    private List<MenuDto> children;

    public MenuDto(int id,String url,String method,String name,int level,int parentId){
        this.id=id;
        this.url=url;
        this.method=method;
        this.name=name;
        this.level=level;
        this.parentId=parentId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<MenuDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDto> children) {
        this.children = children;
    }
}
