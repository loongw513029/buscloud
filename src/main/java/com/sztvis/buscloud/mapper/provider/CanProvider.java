package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.model.domain.CanHistoryEveryDayInfo;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/14 上午10:45
 */
public class CanProvider {
    public String CalcDeviceCanHistorysSQL(Map<String,Object> map){
        String type = (String)map.get("type");
        long deviceId = (long)map.get("deviceId");
        String start = (String)map.get("start");
        String end = (String)map.get("end");
        String devicecode = (String)map.get("devicecode");
        int[] arr = (int[])map.get("arr");
        CanHistoryEveryDayInfo info= (CanHistoryEveryDayInfo)map.get("info");
        SQL sql = new SQL();
        if (type=="FirstmilageSql"||type=="LastmilageSql"){
            sql.SELECT("ShortMileage").FROM("TramCanInfo").WHERE("deviceId="+ deviceId +" and UpdateTime>='"+ start +"'");
            if (type=="FirstmilageSql")
                sql.ORDER_BY("UpdateTime asc limit 1");
            else
                sql.ORDER_BY("UpdateTime desc limit 1");
        }
        if (type=="sql"){
            sql.INSERT_INTO("CanHistoryEveryDayInfo").INTO_COLUMNS("DeviceId,UpdateTime,TotalMileage,GasOnlieAvg,ElectricAvg,GasAvg,TotalFaultNumber,TotalCarFaultNumber,FaultThreeLv,FaultSecondLv,FaultOneLv,UnSafeNumber,UnSafeDriver,RunTimeLong,SpeedingTotal")
                    .INTO_VALUES(info.getDeviceid().toString(),info.getUpdatetime(),info.getTotalmileage().toString(),info.getGasonlieavg().toString(),info.getElectricavg().toString(),
                            info.getGasavg().toString(),info.getTotalfaultnumber().toString(),info.getTotalcarfaultnumber().toString(),info.getFaultthreelv().toString(),info.getFaultsecondlv().toString(),
                            info.getFaultonelv().toString(),info.getUnsafedriver().toString(),info.getRuntimelong().toString(),info.getSpeedingtotal().toString());
        }
        if (type=="timelongsql"){
            sql.SELECT("sum(TotalTime)").FROM("TramDeviceOnLineTimeLongInfo")
            .WHERE("devicecode='"+ devicecode +"' and CreateTime>='"+ start +"'' and CreateTime<='"+ end +"'");
        }
        else {
            if (type=="faultSql2"){
                sql.SELECT("count(a.Id)").FROM("TramCanAlarmInfo a left join TramBasicInfo b on a.AlarmKey=b.Id")
                        .WHERE(" a.deviceId="+ deviceId +" and a.UpdateTime>='"+ start +"' and a.UpdateTime<='"+ end +"' and b.Level={3} and a.state=0 and a.isshow=1");
            }
            else{
                sql.SELECT("count(Id)");
                if (type=="faultSql"||type=="undriversql") {
                    sql.FROM("TramCanAlarmInfo")
                            .WHERE("deviceId="+ deviceId +" and UpdateTime>='"+ start +"' and UpdateTime<='"+ end +"' and state=0 and isshow=1");
                    if (type=="undriversql")
                        sql.AND().WHERE("and AlarmKey in ("+ arr.toString() +")");
                }
                if (type=="isexitsql"){
                    sql.FROM("CanHistoryEveryDayInfo").WHERE("deviceId="+ deviceId +" and updateTime='"+ start +"'");
                }
                if  (type=="unsafeSql"||type=="speedingSQL"){
                    sql.FROM("TramUnSafeBehaviorInfo").WHERE("deviceId="+ deviceId +" and ApplyTime>='"+ start +"' and ApplyTime<='"+ end +"'");
                    if (type=="speedingSQL")
                        sql.AND().WHERE("UnSafeType=13");
                }
            }
        }
        return sql.toString();
    }

    public String GetAlarmChartList(Map<String,Object> map){
        String sql1 = (String)map.get("sqlname");
        List<Long> deviceIds = (List<Long>)map.get("deviceIds") ;
        String code = (String)map.get("code");
        long lineId = (long)map.get("lineId");
        int type = (int)map.get("type");
        String type2 = (String)map.get("type2");
        String start = (String) map.get("start");
        String end = (String)map.get("end");
        long DepartmentId = (long)map.get("departmentId");
        SQL sql = new SQL();
        if (sql1.equals("sql")) {
            sql.SELECT("count(Id)").FROM("TramCanAlarmInfo").WHERE("deviceId in"+deviceIds.toString().replace("[","(").replace("]",")"));
            if (StringHelper.isNotEmpty(type) && StringHelper.isEmpty(type2)) {
                sql.AND().WHERE("alarmKey in (select Id from tramBasicInfo where AlarmType=" + type + ")");
            } else if (StringHelper.isNotEmpty(type) && !StringHelper.isEmpty(type2)) {
                sql.AND().WHERE("alarmKey=" + type2);
            }
            if (StringHelper.isNotEmpty(code))
                sql.AND().WHERE("devicecode like '%"+ code +"%'");
            else
                sql.AND().WHERE("deviceId in(select Id from TramDeviceInfo where DepartmentId="+DepartmentId+")");
            sql.AND().WHERE(" UpdateTime>='"+ start +"'");
            sql.AND().WHERE(" UpdateTime<='"+ end +"'");
        }
        else {
            sql.SELECT("sum(Result)").FROM("TramUnSafeIndexInfo").WHERE("DriverId in"+type2)
                    .AND().WHERE("UpdateTime>="+start).AND().WHERE("UpdateTime<="+end);
        }
        return sql.toString();
    }

    public String GetAlarmChartList1(Map<String,Object> map){
        String type1 = (String)map.get("value1");
        String type2 = (String)map.get("value2");
        String sql = null;
        if (StringHelper.isNotEmpty(type1) && StringHelper.isEmpty(type2))
            sql = "select typename from TramAlarmTypeInfo where id="+ type1 +" limit 1";
        else if (StringHelper.isNotEmpty(type1) && !StringHelper.isEmpty(type2)){
            if (Integer.valueOf(type1) == 17){
                sql = "select DriverName from TramDriverInfo where id in("+ type2 +")";
            }
            else
                sql = "select alarmName from tramBasicInfo where id in("+ type2 +")";
        }
        return sql;
    }
}
