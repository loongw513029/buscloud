package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;

import java.util.List;
import java.util.Map;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/18 下午2:19
 */
public class LineProvider {

    public String getListSQL(Map<String,Object> map){
        List<Long> departments = (List<Long>)map.get("departments");
        String lineName = (String)map.get("linename");
        Long seldepartmentId = (Long)map.get("departmentId");
        String sql= "select a.id,a.linecode,a.linename,b.departmentname,a.lineupmileage,a.linedownmileage,a.upsitenum,a.downsitenum";
        sql+=" from TramLineInfo a left join TramDepartmentInfo b on a.departmentid = b.id  where a.departmentId in("+ StringHelper.listToString(departments,',')+")";
        if(!StringHelper.isEmpty(lineName)){
            sql+=" and (a.linename like '%"+lineName+"%' or a.linecode like '%"+lineName+"%')";
        }
        if(seldepartmentId!=0){
            sql+=" and a.departmentId="+seldepartmentId;
        }
        sql+=" order by a.sort asc";
        return sql;
    }

    public String getLineTreeSQL(Map<String,Object> map){
        String departments = StringHelper.listToString(((List<Long>)map.get("departments")),',');
        String sql = "select id,linename as text from TramLineInfo where departmentId in ("+departments+")";
        return  sql;
    }
}
