package com.sztvis.buscloud.model.dto;

import com.sztvis.buscloud.model.domain.TramCanInfo;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/2/1 上午11:39
 */
public class CanViewModel {
    private String time;
    private CanModel caninfo;
    private CanStatModel canstatinfo;
    private DispatchModel dispatchinfo;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public CanModel getCaninfo() {
        return caninfo;
    }

    public void setCaninfo(CanModel caninfo) {
        this.caninfo = caninfo;
    }

    public CanStatModel getCanstatinfo() {
        return canstatinfo;
    }

    public void setCanstatinfo(CanStatModel canstatinfo) {
        this.canstatinfo = canstatinfo;
    }

    public DispatchModel getDispatchinfo() {
        return dispatchinfo;
    }

    public void setDispatchinfo(DispatchModel dispatchinfo) {
        this.dispatchinfo = dispatchinfo;
    }
}
