package com.sztvis.buscloud.model.domain;

public class Trammemberinfo {
  private Long id;
  private String guid;
  private String username;
  private String password;
  private String passwordsalt;
  private Long ownershipid;
  private Long roleid;
  private Long rolelv;
  private String managescope;
  private Long status;
  private String realname;
  private String code;
  private String phone;
  private String photo;
  private java.sql.Timestamp createtime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

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

  public String getPasswordsalt() {
    return passwordsalt;
  }

  public void setPasswordsalt(String passwordsalt) {
    this.passwordsalt = passwordsalt;
  }

  public Long getOwnershipid() {
    return ownershipid;
  }

  public void setOwnershipid(Long ownershipid) {
    this.ownershipid = ownershipid;
  }

  public Long getRoleid() {
    return roleid;
  }

  public void setRoleid(Long roleid) {
    this.roleid = roleid;
  }

  public Long getRolelv() {
    return rolelv;
  }

  public void setRolelv(Long rolelv) {
    this.rolelv = rolelv;
  }

  public String getManagescope() {
    return managescope;
  }

  public void setManagescope(String managescope) {
    this.managescope = managescope;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }
}
