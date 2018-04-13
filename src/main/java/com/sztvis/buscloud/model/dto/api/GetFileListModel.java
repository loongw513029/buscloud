package com.sztvis.buscloud.model.dto.api;

import com.sztvis.buscloud.model.BaseModel;

public class GetFileListModel {
    private String time;
    private int channel;
    private String category;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
