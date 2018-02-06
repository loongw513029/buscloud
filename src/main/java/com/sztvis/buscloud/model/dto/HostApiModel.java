package com.sztvis.buscloud.model.dto;

import com.sztvis.buscloud.model.entity.HostDataType;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/11 下午5:04
 */
public class HostApiModel {
    private HostDataType Type;
    private String Target;
    private String Source;
    private Object MsgInfo;

    public HostDataType getType() {
        return Type;
    }

    public void setType(HostDataType type) {
        Type = type;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String target) {
        Target = target;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public Object getMsgInfo() {
        return MsgInfo;
    }

    public void setMsgInfo(Object msgInfo) {
        MsgInfo = msgInfo;
    }
}
