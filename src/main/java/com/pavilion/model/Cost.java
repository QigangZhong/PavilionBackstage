package com.pavilion.model;

import java.io.Serializable;

public class Cost implements Serializable {
    private Integer id;

    private Double labor;

    private Double wear;

    private Double management;

    private Double sale;

    private String createTime;

    private String lastUpdateTime;

    private static final long serialVersionUID = 1L;

    public Cost(Integer id, Double labor, Double wear, Double management, Double sale, String createTime, String lastUpdateTime) {
        this.id = id;
        this.labor = labor;
        this.wear = wear;
        this.management = management;
        this.sale = sale;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Cost() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLabor() {
        return labor;
    }

    public void setLabor(Double labor) {
        this.labor = labor;
    }

    public Double getWear() {
        return wear;
    }

    public void setWear(Double wear) {
        this.wear = wear;
    }

    public Double getManagement() {
        return management;
    }

    public void setManagement(Double management) {
        this.management = management;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
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