package com.sztvis.buscloud.model.domain;

public class Tramcanalarminfo {
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
  private Long alarmkey;
  private String alarmtype;
  private String alarmvalue;
  private Long alarmlevel;
  private String extras;
  private String path;
  private java.sql.Timestamp createtime;
  private String isshow;
  private Long state;

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

  public Long getAlarmkey() {
    return alarmkey;
  }

  public void setAlarmkey(Long alarmkey) {
    this.alarmkey = alarmkey;
  }

  public String getAlarmtype() {
    return alarmtype;
  }

  public void setAlarmtype(String alarmtype) {
    this.alarmtype = alarmtype;
  }

  public String getAlarmvalue() {
    return alarmvalue;
  }

  public void setAlarmvalue(String alarmvalue) {
    this.alarmvalue = alarmvalue;
  }

  public Long getAlarmlevel() {
    return alarmlevel;
  }

  public void setAlarmlevel(Long alarmlevel) {
    this.alarmlevel = alarmlevel;
  }

  public String getExtras() {
    return extras;
  }

  public void setExtras(String extras) {
    this.extras = extras;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public java.sql.Timestamp getCreatetime() {
    return createtime;
  }

  public void setCreatetime(java.sql.Timestamp createtime) {
    this.createtime = createtime;
  }

  public String getIsshow() {
    return isshow;
  }

  public void setIsshow(String isshow) {
    this.isshow = isshow;
  }

  public Long getState() {
    return state;
  }

  public void setState(Long state) {
    this.state = state;
  }
}
