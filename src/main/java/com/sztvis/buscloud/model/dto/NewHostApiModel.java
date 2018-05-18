package com.sztvis.buscloud.model.dto;

import com.sztvis.buscloud.model.entity.HostDataType;
import com.sztvis.buscloud.model.entity.NewHostDataType;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午5:04
 */
public class NewHostApiModel {
    private NewHostDataType Type;
    private Object MsgInfo;

    public NewHostDataType getType() {
        return Type;
    }

    public void setType(NewHostDataType type) {
        Type = type;
    }

    public Object getMsgInfo() {
        return MsgInfo;
    }

    public void setMsgInfo(Object msgInfo) {
        MsgInfo = msgInfo;
    }
}
