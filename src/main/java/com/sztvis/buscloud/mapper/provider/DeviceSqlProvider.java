package com.sztvis.buscloud.mapper.provider;


import com.sztvis.buscloud.core.helper.StringHelper;

import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SelectBuilder.*;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/16 下午4:15
 */
public class DeviceSqlProvider {
    private static String TABLE_NAME = "TramDeviceInfo";

    public String getAllSql(){
        BEGIN();
        SELECT("*");
        FROM(TABLE_NAME);
        return SQL();
    }

    public String getWebListSQL(Map<String,Object> map){
        String departments = StringHelper.listToString(((List<Long>)map.get("departments")),',');
        int deviceType = (Integer)map.get("devicetype");
        Long departmentId = (Long)map.get("departmentId");
        Long lineId = (Long)map.get("lineId");
        int status = (Integer)map.get("status");
        String keywords = (String)map.get("keywords");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.id,a.devicecode,a.devicename,b.departmentname,c.linename,d.drivername,e.busnumber,a.clientIp,a.hostSoftType,a.deviceMode,a.videoSupport,a.can,a.radar,a.aerialView,a.supportBehavior,a.supportAdas,a.deviceStatus,a.lastOnlineTime");
        sb.append(" from TramDeviceInfo a left join TramDepartmentInfo b on a.departmentId=b.Id");
        sb.append(" left join TramBusInfo e on a.busId=e.Id");
        sb.append(" left join TramLineInfo c on a.lineId=c.Id");
        sb.append(" left join TramDriverInfo d on e.driverId=d.Id");
        sb.append(" where a.departmentId in ("+departments+")");
        if(deviceType>0){
            sb.append(" and a.hostSoftType="+deviceType);
        }
        if(departmentId>0){
            sb.append(" and a.departmentId="+departmentId);
        }
        if(lineId>0){
            sb.append(" and a.lineId="+lineId);
        }
        if(status!=-100){
            sb.append(" and a.deviceStatus="+status);
        }
        if(!StringHelper.isEmpty(keywords)){
            sb.append(" and (a.devicecode like '%"+keywords+"%' or a.devicename like '%"+keywords+"%' or b.busNumber like '%"+keywords+"%')");
        }
        return sb.toString();
    }
}
