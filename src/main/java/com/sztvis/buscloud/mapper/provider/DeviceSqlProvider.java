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

    public String getDeviceViewModel(Map<String,Object> map){
        long id = (Long) map.get("id");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.id,e.bustype,a.devicecode,a.devicename,a.dchannel,a.carriageChannel,b.departmentname,c.linename,d.drivername,e.busnumber,a.clientIp,a.hostSoftType,a.deviceMode,a.videoSupport,a.can,a.radar,a.aerialView,a.supportBehavior,a.supportAdas,a.deviceStatus,a.lastOnlineTime");
        sb.append(" from TramDeviceInfo a left join TramDepartmentInfo b on a.departmentId=b.Id");
        sb.append(" left join TramBusInfo e on a.busId=e.Id");
        sb.append(" left join TramLineInfo c on a.lineId=c.Id");
        sb.append(" left join TramDriverInfo d on e.driverId=d.Id");
        sb.append(" where a.id="+id);
        return sb.toString();
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

    public String updateRealtimeInspectSQL(Map<String,Object> map){
        String field = (String)map.get("field");
        Object value = map.get("value");
        String devicecode = (String)map.get("deviceCode");
        int fieldtype = (Integer)map.get("fieldtype");
        String sql ="update tramdevicestateinspectrealtimeinfo set "+field;
        switch (fieldtype){
            case 1:
                sql+="='"+(String)value+"'";
                break;
            case 2:
                sql+="="+(Double)value;
                break;
            case 3:
                sql+="="+((Boolean)value?1:0);
                break;
        }
        sql+=" where devicecode='"+devicecode+"'";
        return sql;
    }

    public String getDeviceInspectSQL(Map<String,Object> map){
        List<Long> departments = (List<Long>)map.get("departments");
        long departmentid = (Long)map.get("departmentid");
        long lineid = (Long)map.get("lineid");
        int type = (Integer)map.get("type");
        String keywords = (String)map.get("keywords");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.deviceid,a.videotape,a.video,a.harddisk,a.sdcard,a.cpuuserate,a.cputemp,a.mermoryuserate,a.disktemp,a.gpsstate,a.canstate,a.internetstate,a.gpssignelstate,a.onlinestate,a.simbalance,a.gpsinspectstate,a.caninspectstate,a.behaviorinspectstate,a.radarinspectstate,a.adasinspectstate,a.timingstate,a.deviceCode,a.surplusDiskSize,a.surplusSdcardSize,b.id as departmentid,b.departmentname,c.id as lineid,c.linename");
        sb.append(" from tramdevicestateinspectrealtimeinfo a left join tramdeviceinfo f on a.deviceid=f.id left join tramdepartmentinfo b on f.departmentid=b.id left join tramlineinfo c on c.id=f.lineid");
        sb.append(" where f.departmentid in ("+StringHelper.listToString(departments,',')+")");
        if(departmentid != 0)
            sb.append(" and f.departmentid="+departmentid);
        if(lineid != 0)
            sb.append(" and f.lineid="+lineid);
        if(!StringHelper.isEmpty(keywords))
            sb.append(" and a.devicecode like '%"+keywords+"%'");
        return sb.toString();
    }

    public String getMapDeviceListSQL(Map<String,Object> map){
        String devices = (String)map.get("devices");
        String sql="select a.id,a.devicecode,b.departmentname,c.linename,e.busnumber from tramdeviceinfo a left join trambusinfo e on a.busid=e.id left join tramdepartmentinfo b on a.departmentid=b.id left join tramlineinfo c on a.lineid=c.id where a.id in("+devices+")";
        return sql;
    }
}
