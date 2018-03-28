package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.model.UnSafeQuery;
import com.sztvis.buscloud.util.DayTypes;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

public class UnSafeProvider {
    public String GetUnsafeListSQL(Map<String, Object> map) {
        UnSafeQuery query = (UnSafeQuery) map.get("query");
        List<Long> deviceIds = (List<Long>) map.get("deviceIds");
        DayTypes types = (DayTypes) map.get("type");
        SQL sql = new SQL();
        sql.SELECT("a.Id,a.DeviceCode as Code,a.DeviceId,b.LineId,c.LineName,b.DepartmentId,d.DepartmentName,a.UnSafeType,a.ApplyTime as Time,a.Location");
        sql.FROM("TramUnSafeBehaviorInfo a left join TramDeviceInfo b on a.DeviceId = b.Id left join TramLineInfo c on b.LineId = c.Id left join TramDepartmentInfo d on b.DepartmentId = d.id");
        if (query.getLineId() == 0)
            sql.WHERE("1=1");
        else
            sql.WHERE("b.LineId=" + query.getLineId());
        if (deviceIds != null)
            sql.AND().WHERE("a.deviceId in(" + deviceIds + ")");
        sql.AND().WHERE("a.ApplyTime>='" + types.getStartTime() + "' and a.ApplyTime<='" + types.getEndTime() + "'");
        if (StringHelper.isNotEmpty(query.getDeviceCode()))
            sql.AND().WHERE("a.DeviceCode like '%" + query.getDeviceCode() + "%'");
        if (query.getUnSafeType() != 0)
            sql.AND().WHERE(" a.UnSafeType=" + query.getUnSafeType());
        sql.ORDER_BY("a.ApplyTime desc");
        return sql.toString();
    }
}
