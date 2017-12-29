package com.sztvis.buscloud.model.dto;

public class LoginParams {
    private String username;
    private String password;
    private String clientod;
    private Integer logintype;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getClientod() {
        return clientod;
    }

    public void setClientod(String clientod) {
        this.clientod = clientod;
    }

    public Integer getLogintype() {
        return logintype;
    }

    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }



}
