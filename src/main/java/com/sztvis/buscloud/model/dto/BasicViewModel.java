package com.sztvis.buscloud.model.dto;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/24 下午2:30
 */
public class BasicViewModel {
    private int id;
    private int parentid;
    private String alarmname;
    private int level;
    private boolean turn;
    private boolean ispush;
    private int customid;
    private boolean isenable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public String getAlarmname() {
        return alarmname;
    }

    public void setAlarmname(String alarmname) {
        this.alarmname = alarmname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public boolean isIspush() {
        return ispush;
    }

    public void setIspush(boolean ispush) {
        this.ispush = ispush;
    }

    public int getCustomid() {
        return customid;
    }

    public void setCustomid(int customid) {
        this.customid = customid;
    }

    public boolean isIsenable() {
        return isenable;
    }

    public void setIsenable(boolean isenable) {
        this.isenable = isenable;
    }
}
