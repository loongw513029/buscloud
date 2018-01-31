package com.sztvis.buscloud.model.dto;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/22 下午4:50
 */
public class ChannelViewModel {
    private String channelname;
    private int no;

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public boolean isSupportptz() {
        return supportptz;
    }

    public void setSupportptz(boolean supportptz) {
        this.supportptz = supportptz;
    }

    private boolean supportptz;

}
