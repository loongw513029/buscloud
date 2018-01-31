package com.sztvis.buscloud.model.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "TramGpsInfo")
public class TramGpsInfo {
  @Id
  private Long id;
  private Long deviceid;
  private String devicecode;
  private String updatetime;
  private String longitude;
  private String latitude;
  private Double speed;
  private Double direction;
  private String locationmode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getUpdatetime() {
    return updatetime;
  }

  public void setUpdatetime(String updatetime) {
    this.updatetime = updatetime;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public Double getSpeed() {
    return speed;
  }

  public void setSpeed(Double speed) {
    this.speed = speed;
  }

  public Double getDirection() {
    return direction;
  }

  public void setDirection(Double direction) {
    this.direction = direction;
  }

  public String getLocationmode() {
    return locationmode;
  }

  public void setLocationmode(String locationmode) {
    this.locationmode = locationmode;
  }
}
