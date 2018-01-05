package com.sztvis.buscloud.model.dto;

public class LoginParams {
    private String username;
    private String password;
    private String clientid;
    /**
     * 登录类型 1：安卓 2：IOS 3：Web
     */
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
    public String getClientId() {
        return clientid;
    }

    public void setClientId(String clientid) {
        this.clientid = clientid;
    }

    public Integer getLogintype() {
        return logintype;
    }

    public void setLogintype(Integer logintype) {
        this.logintype = logintype;
    }



}
