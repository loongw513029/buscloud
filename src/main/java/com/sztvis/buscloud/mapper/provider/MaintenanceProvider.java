package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;

import java.util.Map;

public class MaintenanceProvider {
    public String GetMaintenance(Map<String,Object> map){
        String Code = (String)map.get("Code");
        long DepartmentId = (Long)map.get("DepartmentId");
        long lineId = (Long)map.get("lineId");
        String statr = (String)map.get("statr");
        String end = (String)map.get("end");
        StringBuilder sql = new StringBuilder();
        sql.append("select a.Id,a.DeviceId,a.MtDate,a.MtMileage,a.Project,a.NextDate,a.NextMileage,a.Description,a.CreateTime,b.DeviceCode from MaintenanceInfo a left join TramDeviceInfo b on a.DeviceId=b.Id");
        if (StringHelper.isNotEmpty(Code)){
            sql.append("where b.DeviceCode ="+Code);
        }
        else {
            if (StringHelper.isNotEmpty(String.valueOf(DepartmentId)) && StringHelper.isNotEmpty(String.valueOf(lineId)))
                sql.append("where b.DeviceCode in (select DeviceCode from where DepartmentId="+ DepartmentId +" and LineId="+ lineId +")");
            else if (StringHelper.isNotEmpty(String.valueOf(DepartmentId)) && StringHelper.isEmpty(String.valueOf(lineId)))
                sql.append("where b.DeviceCode in (select DeviceCode from where DepartmentId="+ DepartmentId +")");
        }
        if (StringHelper.isNotEmpty(statr))
            sql.append("and a.CreateTime>='"+ statr +"'");
        if (StringHelper.isNotEmpty(end))
            sql.append("and a.CreateTime=<'"+ end +"'");
        return sql.toString();
    }
}
