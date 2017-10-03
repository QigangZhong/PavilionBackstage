package com.pavilion.model.dto;

public class UserDto {
    private int id;
    private String username;
    private String mobile;
    private String email;
    private String nickname;

    public UserDto(){}
    public UserDto(int id,String username,String mobile,String email,String nickname){
        this.id=id;
        this.username=username;
        this.mobile=mobile;
        this.email=email;
        this.nickname=nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
