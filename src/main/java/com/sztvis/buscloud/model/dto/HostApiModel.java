package com.sztvis.buscloud.model.dto;

import com.sztvis.buscloud.model.entity.HostDataType;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午5:04
 */
public class HostApiModel {
    private HostDataType Type;
    private Object MsgInfo;

    public HostDataType getType() {
        return Type;
    }

    public void setType(HostDataType type) {
        Type = type;
    }

    public Object getMsgInfo() {
        return MsgInfo;
    }

    public void setMsgInfo(Object msgInfo) {
        MsgInfo = msgInfo;
    }
}
