package com.sztvis.buscloud.model.domain;

public class Trambasicinfo {
  private Long id;
  private String alarmname;
  private Long level;
  private Long type;
  private String fixe;
  private String turn;
  private String ispush;
  private String threshold;
  private Long alarmtype;
  private String shorthand;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAlarmname() {
    return alarmname;
  }

  public void setAlarmname(String alarmname) {
    this.alarmname = alarmname;
  }

  public Long getLevel() {
    return level;
  }

  public void setLevel(Long level) {
    this.level = level;
  }

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  public String getFixe() {
    return fixe;
  }

  public void setFixe(String fixe) {
    this.fixe = fixe;
  }

  public String getTurn() {
    return turn;
  }

  public void setTurn(String turn) {
    this.turn = turn;
  }

  public String getIspush() {
    return ispush;
  }

  public void setIspush(String ispush) {
    this.ispush = ispush;
  }

  public String getThreshold() {
    return threshold;
  }

  public void setThreshold(String threshold) {
    this.threshold = threshold;
  }

  public Long getAlarmtype() {
    return alarmtype;
  }

  public void setAlarmtype(Long alarmtype) {
    this.alarmtype = alarmtype;
  }

  public String getShorthand() {
    return shorthand;
  }

  public void setShorthand(String shorthand) {
    this.shorthand = shorthand;
  }
}
