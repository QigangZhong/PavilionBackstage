package com.pavilion.model.dto;

import java.io.Serializable;

public class MaterialPriceDto implements Serializable {
    private Integer id;

    private Integer materialId;

    private Integer unit;

    private Double price;

    public MaterialPriceDto(){}

    public MaterialPriceDto(Integer id, Integer materialId, Integer unit, Double price) {
        this.id = id;
        this.materialId = materialId;
        this.unit = unit;
        this.price = price;
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
}
