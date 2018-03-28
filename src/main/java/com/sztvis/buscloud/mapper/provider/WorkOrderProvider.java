package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class WorkOrderProvider {
    public String GetWorkOrdersSQL(Map<String,Object> map)
    {
        String dids=(String) map.get("dids");
        Long userId=(Long)map.get("userId");
        String userName=(String)map.get("userName");
        int type=(int)map.get("type");
        SQL sql=new SQL();
        sql.SELECT("a.Id,a.Title,a.Number,b.deviceCode,a.remark,a.deviceId,a.FaultType,a.Audit,a.ReparUserId,c.realName,a.LimitTime,a.ApplyTime,a.HandleTime,a.State");
        sql.FROM("TramWorkOrderInfo a left join TramDeviceInfo b on a.deviceId = b.Id left join TramReparInfo c on a.Number = c.Number");
        sql.WHERE("a.Type="+type);
        sql.AND().WHERE("a.deviceId in ("+dids+")");
        if (StringHelper.comma_contains(userName,"维修员"))
            sql.AND().WHERE("a.ReparUserId="+userId);
        sql.ORDER_BY("a.ApplyTime desc");
        return sql.toString();
    }
}
