package com.sztvis.buscloud.model.dto;

import java.io.Serializable;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/9 上午9:40
 */
public class WelcomeModel implements Serializable {

    private int totelNum;

    private int onlineNum;

    private int lineNum;

    private int todayPrecent;

    private int fiveDayPrecent;

    private int unsafeNum;

    public int getTotelNum() {
        return totelNum;
    }

    public void setTotelNum(int totelNum) {
        this.totelNum = totelNum;
    }

    public int getOnlineNum() {
        return onlineNum;
    }

    public void setOnlineNum(int onlineNum) {
        this.onlineNum = onlineNum;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getTodayPrecent() {
        return todayPrecent;
    }

    public void setTodayPrecent(int todayPrecent) {
        this.todayPrecent = todayPrecent;
    }

    public int getFiveDayPrecent() {
        return fiveDayPrecent;
    }

    public void setFiveDayPrecent(int fiveDayPrecent) {
        this.fiveDayPrecent = fiveDayPrecent;
    }

    public int getUnsafeNum() {
        return unsafeNum;
    }

    public void setUnsafeNum(int unsafeNum) {
        this.unsafeNum = unsafeNum;
    }

    public WelcomeTrendModel getWelcomeTrendModel() {
        return welcomeTrendModel;
    }

    public void setWelcomeTrendModel(WelcomeTrendModel welcomeTrendModel) {
        this.welcomeTrendModel = welcomeTrendModel;
    }

    private WelcomeTrendModel welcomeTrendModel;

}

