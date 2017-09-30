package com.pavilion.domain;

import java.io.Serializable;

public class Menu implements Serializable {
    private Integer id;

    private String url;

    private String method;

    private String description;

    private String createTime;

    private String lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Menu(Integer id, String url, String description, String createTime, String lastUpdateTime) {
        this.id = id;
        this.url = url;
        this.description = description;
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
}