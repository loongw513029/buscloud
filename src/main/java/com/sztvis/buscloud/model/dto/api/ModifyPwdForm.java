package com.sztvis.buscloud.model.dto.api;

public class ModifyPwdForm {
    public long userid;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    /// <summary>
    /// 旧密码
    /// </summary>
    public String oldpwd;
    /// <summary>
    /// 新密码
    /// </summary>
    public String newpwd;
}
