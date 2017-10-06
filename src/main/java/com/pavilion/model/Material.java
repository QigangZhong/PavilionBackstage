package com.pavilion.model;

import java.io.Serializable;

public class Material implements Serializable {
    private Integer id;

    private String cpscode;

    private String cinvname;

    private String cinvstd;

    private Integer ipsquantity;

    private String type;

    private Double totalPrice;

    private String createTime;

    private String lastUpdateTime;

    private int deleted;

    private static final long serialVersionUID = 1L;

    public Material(Integer id, String cpscode, String cinvname, String cinvstd, Integer ipsquantity, String type, String createTime, String lastUpdateTime,Double totalPrice,int deleted) {
        this.id = id;
        this.cpscode = cpscode;
        this.cinvname = cinvname;
        this.cinvstd = cinvstd;
        this.ipsquantity = ipsquantity;
        this.type = type;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
        this.totalPrice=totalPrice;
        this.deleted=deleted;
    }

    public Material() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpscode() {
        return cpscode;
    }

    public void setCpscode(String cpscode) {
        this.cpscode = cpscode == null ? null : cpscode.trim();
    }

    public String getCinvname() {
        return cinvname;
    }

    public void setCinvname(String cinvname) {
        this.cinvname = cinvname == null ? null : cinvname.trim();
    }

    public String getCinvstd() {
        return cinvstd;
    }

    public void setCinvstd(String cinvstd) {
        this.cinvstd = cinvstd == null ? null : cinvstd.trim();
    }

    public Integer getIpsquantity() {
        return ipsquantity;
    }

    public void setIpsquantity(Integer ipsquantity) {
        this.ipsquantity = ipsquantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
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

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}