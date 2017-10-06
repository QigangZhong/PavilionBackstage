package com.pavilion.model;

import java.io.Serializable;

public class MaterialPrice implements Serializable {
    private Integer id;

    private Integer materialId;

    private Integer unit;

    private Double price;

    private String createTime;

    private String lastUpdateTime;

    private int deleted;

    private static final long serialVersionUID = 1L;

    public MaterialPrice(Integer id, Integer materialId, Integer unit, Double price, String createTime, String lastUpdateTime,int deleted) {
        this.id = id;
        this.materialId = materialId;
        this.unit = unit;
        this.price = price;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.deleted=deleted;
    }

    public MaterialPrice() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}