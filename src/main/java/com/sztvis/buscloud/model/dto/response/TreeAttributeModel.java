package com.sztvis.buscloud.model.dto.response;

import java.io.Serializable;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 上午11:46
 */
public class TreeAttributeModel implements Serializable {
    private boolean state;
    private Integer channel;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }
}
