package com.pavilion.model.dto;

public class MaterialDto {
    private Integer id;

    private String cpscode;

    private String cinvname;

    private String cinvstd;

    private Integer ipsquantity;

    private String type;

    public MaterialDto(){}

    public MaterialDto(Integer id, String cpscode, String cinvname, String cinvstd, Integer ipsquantity, String type) {
        this.id = id;
        this.cpscode = cpscode;
        this.cinvname = cinvname;
        this.cinvstd = cinvstd;
        this.ipsquantity = ipsquantity;
        this.type = type;
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
        this.cpscode = cpscode;
    }

    public String getCinvname() {
        return cinvname;
    }

    public void setCinvname(String cinvname) {
        this.cinvname = cinvname;
    }

    public String getCinvstd() {
        return cinvstd;
    }

    public void setCinvstd(String cinvstd) {
        this.cinvstd = cinvstd;
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
        this.type = type;
    }
}
