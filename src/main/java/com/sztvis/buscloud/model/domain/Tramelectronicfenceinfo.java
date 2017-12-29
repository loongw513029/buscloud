package com.sztvis.buscloud.model.domain;

public class Tramelectronicfenceinfo {
  private Long id;
  private String lng;
  private String lat;
  private Long radius;
  private String intrun;
  private String outtrun;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLng() {
    return lng;
  }

  public void setLng(String lng) {
    this.lng = lng;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public Long getRadius() {
    return radius;
  }

  public void setRadius(Long radius) {
    this.radius = radius;
  }

  public String getIntrun() {
    return intrun;
  }

  public void setIntrun(String intrun) {
    this.intrun = intrun;
  }

  public String getOuttrun() {
    return outtrun;
  }

  public void setOuttrun(String outtrun) {
    this.outtrun = outtrun;
  }
}
