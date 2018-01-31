package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;

import java.util.List;
import java.util.Map;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 下午3:42
 */
public class AlarmProvider {

    public String getAlarmTableListSQL(Map<String,Object> map){
        String departments = StringHelper.listToString((List<Long>)map.get("departments"),',');
        long departmentId = (Long)map.get("departmentId");
        long lineId = (Long)map.get("lineId");
        long type1 = (Long)map.get("type1");
        long type2 = (Long)map.get("type2");
        String date1 = (String)map.get("date1");
        String date2 = (String)map.get("date2");
        String keywords = (String)map.get("keywords");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.id,a.deviceid,a.path,a.devicecode,b.busnumber,c.linename,d.departmentname,e.alarmname,a.updatetime,a.location,a.speed,a.distance,a.isbrake,a.value");
        sb.append(" from TramAlarmInfo a left join TramDeviceInfo g on a.deviceid = g.id left join TramBusInfo b on g.busid = b.id");
        sb.append(" left join TramLineInfo c on g.lineid = c.id left join TramDepartmentInfo d on g.departmentid = d.id left join TramBasicInfo e on a.alarmType=e.customId");
        sb.append(" where a.departmentId in ("+departments+")");
        if(departmentId != 0)
            sb.append(" and a.departmentId="+departmentId);
        if(lineId != 0)
            sb.append(" and c.lineId="+lineId);
        if(type1 != 0)
            sb.append(" and a.ParentAlarmType="+type1);
        if(type2 != 0)
            sb.append(" and a.AlarmType="+type2);
        if(!StringHelper.isEmpty(date1))
            sb.append(" and a.updatetime>='"+date1+"'");
        if(!StringHelper.isEmpty(date2))
            sb.append(" and a.updatetime<='"+date2+"'");
        if(!StringHelper.isEmpty(keywords))
            sb.append(" and (a.devicecode like '%"+keywords+"%' or b.busnumber like '%"+keywords+"%')");
        return  sb.toString();
    }

    public String getMapAlarmListSQL(Map<String,Object> map){
        String devices = (String)map.get("devices");
        String starttime = (String)map.get("starttime");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.id,a.deviceid,a.path,a.devicecode,b.busnumber,c.linename,d.departmentname,e.alarmname,a.updatetime,a.location,a.speed,a.distance,a.isbrake,a.value");
        sb.append(" from TramAlarmInfo a left join TramDeviceInfo g on a.deviceid = g.id left join TramBusInfo b on g.busid = b.id");
        sb.append(" left join TramLineInfo c on g.lineid = c.id left join TramDepartmentInfo d on g.departmentid = d.id left join TramBasicInfo e on a.alarmType=e.customId");
        sb.append(" where 1=1");
        if(!StringHelper.isEmpty(devices)){
            sb.append(" and a.deviceid in ("+devices+")");
        }
        if(!StringHelper.isEmpty(starttime))
            sb.append(" and a.updatetime>='"+starttime+"'");
        return sb.toString();
    }

    public String getAlarmViewModelSQL(Map<String,Object> map){
        long id = (Long)map.get("id");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.id,a.deviceid,a.path,a.devicecode,b.busnumber,c.linename,d.departmentname,e.alarmname,a.updatetime,a.location,a.speed,a.distance,a.isbrake,a.value");
        sb.append(" from TramAlarmInfo a left join TramDeviceInfo g on a.deviceid = g.id left join TramBusInfo b on g.busid = b.id");
        sb.append(" left join TramLineInfo c on g.lineid = c.id left join TramDepartmentInfo d on g.departmentid = d.id left join TramBasicInfo e on a.alarmType=e.customId");
        sb.append(" where a.id="+id);
        return sb.toString();
    }
}
