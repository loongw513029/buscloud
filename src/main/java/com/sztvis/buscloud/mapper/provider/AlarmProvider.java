package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.util.DayTypes;
import org.apache.ibatis.jdbc.SQL;

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

    public String getTop6AlarmSQL(Map<String,Object> map){
        List<Long> devices = (List<Long>)map.get("devices");
        StringBuilder sb = new StringBuilder();
        sb.append("select a.id,b.alarmname,a.updatetime");
        sb.append(" from TramAlarmInfo a left join TramBasicInfo b on a.alarmType=b.customId ");
        sb.append(" where a.deviceid in ("+StringHelper.listToString(devices,',')+") order by updateTime desc limit 0,6");
        return sb.toString();
    }

    public String getAlarmCountByUserId(Map<String,Object> map){
        List<Long> devices = (List<Long>)map.get("devices");
        String startTime = (String)map.get("startTime");
        String endTime = (String)map.get("endTime");
        int type = (Integer) map.get("type");
        SQL sql = new SQL();
        sql.SELECT("count(Id)");
        sql.FROM("TramAlarmInfo");
        sql.WHERE("deviceId in ("+ StringHelper.listToString(devices,',')+") and updateTime>='"+startTime+"' and updateTime<='"+endTime+"' and alarmType="+type);
        return sql.toString();
    }

    public String GetListSQL(Map<String,Object> map){
        List<Long> DeviceIds=(List<Long>)map.get("DeviceIds");
        List<Long> AlarmKeys=(List<Long>)map.get("AlarmKeys");
        int dayType=(int)map.get("dayType");
        Long typeId=(Long)map.get("typeId");
        Long alarmType=(Long)map.get("alarmType");
        String code=(String)map.get("code");
        Long lineId=(Long)map.get("lineId");
        String device=DeviceIds.size()==0?"":StringHelper.listToString(DeviceIds,',');
        String alarmkey=AlarmKeys.size()==0?"":StringHelper.listToString(AlarmKeys,',');
        SQL sql = new SQL();
        sql.SELECT("a.Id,b.DeviceCode,a.Extras,b.Id as DeviceId,c.BusNumber" +
                ",d.LineName,e.DepartmentName,f.AlarmName,f.Level,a.UpdateTime,a.AlarmValue,a.AlarmKey,a.AlarmType,a.Path");
        sql.FROM("TramCanAlarmInfo a left join TramDeviceInfo b on a.DeviceId = b.Id left join TramBusInfo c on " +
                "b.BusId = c.Id left join TramLineInfo d on b.LineId = d.Id left join TramDepartmentInfo e on " +
                "b.DepartmentId = e.Id left join TramBasicInfo f on a.AlarmKey = f.Id");
        sql.WHERE("a.IsShow=1 and a.deviceId in("+device+") and AlarmKey in("+alarmkey+")");
        if (String.valueOf(dayType)!=null&&dayType!=0)
        {
            DayTypes types = DayTypes.getDayByType(dayType);
            sql.AND().WHERE("and datediff(dd,'"+ types.getStartTime() +"',a.UpdateTime)>=0  and datediff(dd,'"+ types.getEndTime() +"',a.UpdateTime)<=0");
        }
        if (String.valueOf(typeId)!=null&&typeId!=0)
            sql.AND().WHERE("and a.AlarmKey in (select Id from TramBasicInfo where AlarmType="+ typeId +"");
        if (String.valueOf(alarmType)!=null&&alarmType!=0)
            sql.AND().WHERE("and a.AlarmKey="+ alarmType +"");
        if (code!=null&&code!="")
            sql.AND().WHERE("and (b.DeviceCode like '%"+ code +"%' or c.BusNumber like '"+ code +"%')");
        if (String.valueOf(lineId)!=null&&lineId!=0)
            sql.AND().WHERE("and d.Id="+ lineId +"");
        sql.ORDER_BY("a.UpdateTime desc");
        return sql.toString();
    }

    public String GetAppAlarmChartsSQL(Map<String,Object> map)
    {
        Long Id=(Long)map.get("Id");
        int type=(int)map.get("type");
        String startTime=(String)map.get("startTime");
        String endTime=(String)map.get("endTime");
        Long lineId=(Long)map.get("lineId");
        List<Long> deviceIds=(List<Long>)map.get("deviceIds");
        SQL sql=new SQL();
        sql.SELECT("count(Id)");
        sql.FROM("TramCanAlarmInfo");
        if (type==1)
            sql.WHERE("AlarmKey in (select Id from TramBasicInfo where AlarmType="+ Id +") and CreateTime>='"+ startTime +"' and CreateTime<='"+ endTime +"' and isShow=1 and deviceId in ("+ deviceIds +")");
        else
            sql.WHERE("AlarmKey in(select Id from TramBasicInfo where AlarmType in (select id from TramAlarmTypeInfo where parentId="+ Id +")) and CreateTime>='"+ startTime +"' and CreateTime<='"+ endTime +"' and isShow=1 and deviceId in ("+ deviceIds +")");
        if (StringHelper.isNotEmpty(lineId))
            sql.AND().WHERE("DeviceId in(select Id from TramDeviceInfo where LineId=" + lineId + ")");
        return sql.toString();
    }

    public String xValCharsSQL(Map<String,Object> map)
    {
        Long Id=(Long)map.get("Id");
        Long roleLv=(Long)map.get("roleLv");
        int type=(int)map.get("type");
        SQL sql=new SQL();
        sql.SELECT("id,TypeName as Name");
        sql.FROM("TramAlarmTypeInfo");
        if (type==1) {
            sql.WHERE("parentId=0");
            if (roleLv>0)
            {
                int k = roleLv == 1 ? 1 : roleLv == 2 ? 2 : 9;
                sql.AND().WHERE("Id="+k);
            }
        }
        else
            sql.WHERE("parentId="+Id);
        return sql.toString();
    }
}
