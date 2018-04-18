package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/16 下午4:59
 */
public class DepartmentProvider {

    public String GetDepartmentListSQL(Map<String,Object> map){
        List<Long> departments = (List<Long>)map.get("departments");
        String text = (String)map.get("text");
        String sql ="select id,code,departmentname,parentid,contactname,contactphone,islookcan,ishavevedio from TramDepartmentInfo where 1=1";
        String arrs= StringHelper.listToString(departments,',');
        sql +=" and parentId in ("+arrs+") or Id in ("+arrs+")";
        if(!StringHelper.isEmpty(text)){
            sql +=" and departmentname like '%"+text+"%'";
        }
        return sql;
    }

    public String GetListByDepartmentsSQL(Map<String,Object> map){
        List<Long> departments = (List<Long>)map.get("departmentIds");
        String sql = "select * from TramDepartmentInfo where 1=1";
        String arrs= StringHelper.listToString(departments,',');
        sql +=" and parentId in ("+arrs+") or Id in ("+arrs+")";
        return sql;
    }
}
