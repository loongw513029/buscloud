package com.sztvis.buscloud.model.domain;

public class Tramdriverinfo {
  private Long id;
  private String guid;
  private String drivername;
  private Long gender;
  private Long departmentid;
  private Long status;

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

  public String getDrivername() {
    return drivername;
  }

  public void setDrivername(String drivername) {
    this.drivername = drivername;
  }

  public Long getGender() {
    return gender;
  }

  public void setGender(Long gender) {
    this.gender = gender;
  }

  public Long getDepartmentid() {
    return departmentid;
  }

  public void setDepartmentid(Long departmentid) {
    this.departmentid = departmentid;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }
}
