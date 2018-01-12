package com.sztvis.buscloud.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TramDeviceHealthInfo")
public class TramDeviceHealthInfo {
  @Id
  private Long id;
  private String devicecode;
  private java.sql.Timestamp updatetime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
}
