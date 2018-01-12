package com.sztvis.buscloud.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "TramDeviceStatusInfo")
public class Tramdevicestatusinfo {
  @Id
  private Long id;
  private String guid;
  private Long deviceid;
  private String devicecode;
  private java.sql.Timestamp updatetime;
  private Long type;
  private String value1;
  private String value2;
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

  public Long getDeviceid() {
    return deviceid;
  }

  public void setDeviceid(Long deviceid) {
    this.deviceid = deviceid;
  }

  public String getDevicecode() {
    return devicecode;
  }

  public void setDevicecode(String devicecode) {
    this.devicecode = devicecode;
  }

  public java.sql.Timestamp getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(java.sql.Timestamp updatetime) {
    this.updatetime = updatetime;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public String getValue1() {
    return value1;
  }

  public void setValue1(String value1) {
    this.value1 = value1;
  }

  public String getValue2() {
    return value2;
  }

  public void setValue2(String value2) {
    this.value2 = value2;
  }

  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }
}
