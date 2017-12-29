package com.sztvis.buscloud.model.domain;

public class Tramchannelinfo {
  private Long id;
  private Long deviceid;
  private Long no;
  private String channelname;
  private String supportptz;

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

  public Long getNo() {
    return no;
  }

  public void setNo(Long no) {
    this.no = no;
  }

  public String getChannelname() {
    return channelname;
  }

  public void setChannelname(String channelname) {
    this.channelname = channelname;
  }

  public String getSupportptz() {
    return supportptz;
  }

  public void setSupportptz(String supportptz) {
    this.supportptz = supportptz;
  }
}
