package com.sztvis.buscloud.model.domain;

public class Tramgpsinfo {
  private Long id;
  private String guid;
  private Long deviceid;
  private String devicecode;
  private java.sql.Timestamp updatetime;
  private Long year;
  private Long month;
  private Long day;
  private Long hour;
  private Long minute;
  private Long second;
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

  public Long getYear() {
    return year;
  }

  public void setYear(Long year) {
    this.year = year;
  }

  public Long getMonth() {
    return month;
  }

  public void setMonth(Long month) {
    this.month = month;
  }

  public Long getDay() {
    return day;
  }

  public void setDay(Long day) {
    this.day = day;
  }

  public Long getHour() {
    return hour;
  }

  public void setHour(Long hour) {
    this.hour = hour;
  }

  public Long getMinute() {
    return minute;
  }

  public void setMinute(Long minute) {
    this.minute = minute;
  }

  public Long getSecond() {
    return second;
  }

  public void setSecond(Long second) {
    this.second = second;
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
