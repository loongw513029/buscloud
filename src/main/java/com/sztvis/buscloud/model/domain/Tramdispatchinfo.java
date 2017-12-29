package com.sztvis.buscloud.model.domain;

public class Tramdispatchinfo {
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
  private Long dispatchno;
  private String dispatchname;
  private Long dispatchtype;
  private Long drivingdirection;
  private Long analytical;
  private Long failtype;
  private String image;
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

  public Long getDispatchno() {
    return dispatchno;
  }

  public void setDispatchno(Long dispatchno) {
    this.dispatchno = dispatchno;
  }

  public String getDispatchname() {
    return dispatchname;
  }

  public void setDispatchname(String dispatchname) {
    this.dispatchname = dispatchname;
  }

  public Long getDispatchtype() {
    return dispatchtype;
  }

  public void setDispatchtype(Long dispatchtype) {
    this.dispatchtype = dispatchtype;
  }

  public Long getDrivingdirection() {
    return drivingdirection;
  }

  public void setDrivingdirection(Long drivingdirection) {
    this.drivingdirection = drivingdirection;
  }

  public Long getAnalytical() {
    return analytical;
  }

  public void setAnalytical(Long analytical) {
    this.analytical = analytical;
  }

  public Long getFailtype() {
    return failtype;
  }

  public void setFailtype(Long failtype) {
    this.failtype = failtype;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }
}
