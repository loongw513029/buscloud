package com.sztvis.buscloud.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TramDeviceHealthInfo")
public class TramDeviceHealthInfo {
  private String guid;
  private String devicecode;
  private String updatetime;
  private String location;

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public String getDevicecode() {
    return devicecode;
  }

  public void setDevicecode(String devicecode) {
    this.devicecode = devicecode;
  }

  public String getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(String updatetime) {
    this.updatetime = updatetime;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
