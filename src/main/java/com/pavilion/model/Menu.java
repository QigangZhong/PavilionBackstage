package com.pavilion.model;

import java.io.Serializable;

public class Menu implements Serializable {
    private Integer id;

    private String url;

    private String method;

    private String name;

    private String description;

    private int show;

    private int parentId;

    private int level;

    private String createTime;

    private String lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Menu(Integer id, String url, String name, String description, int show,int parentId,int level,String createTime, String lastUpdateTime) {
        this.id = id;
        this.url = url;
        this.name=name;
        this.description = description;
        this.show=show;
        this.parentId=parentId;
        this.level=level;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Menu() {
        super();
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
        this.url = url == null ? null : url.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime == null ? null : lastUpdateTime.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}