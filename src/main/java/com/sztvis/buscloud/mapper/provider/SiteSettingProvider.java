package com.sztvis.buscloud.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class SiteSettingProvider {
    public String GetAppCharts(int type, List<Long> LineArr, Long lineId, String date, String date2)
    {
        String Line=LineArr.toString().replace("[","(").replace("]",")");
        SQL sql=new SQL();
        sql.SELECT("count(Id)");
        if (type==1 || type==2)
        {
            sql.FROM("TramDeviceInfo");
            if (type==1)
                sql.WHERE("LineId in "+Line+""+ (lineId == 0?"":" and lineId="+ lineId +"")+"");
            if (type==2)
                sql.WHERE("deviceStatus=1 and LineId in "+Line+""+ (lineId == 0?"":" and lineId="+ lineId +"")+"");
        }
        if (type==3)
        {
            sql.FROM("TramLineInfo");
            sql.WHERE("Id in "+ Line +"");
        }
        if(type==4)
        {
            sql.FROM("TramUnSafeBehaviorInfo");
            sql.WHERE("deviceId in "+ Line +" and ApplyTime>=('"+ date +"') and ApplyTime<=('"+ date2 +"')"+
                    (lineId == 0?"":" and deviceId in(select Id from TramDeviceInfo where lineId="+ lineId +")")+"");
        }
        if(type==5)
        {
            sql.FROM("TramCanAlarmInfo");
            sql.WHERE("deviceId in "+ Line +" and updateTime>=('"+ date +"') and updateTime<=('"+ date2 +"')"+
                    (lineId == 0?"":" and deviceId in(select Id from TramDeviceInfo where lineId="+ lineId +")")+"");
        }
        return sql.toString();
    }
}
