package com.sztvis.buscloud.mapper.provider;

import com.sztvis.buscloud.core.helper.StringHelper;

import java.util.Map;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/18 上午10:14
 */
public class BasicProvider {

    public String getBasicList(Map<String,Object> map){
        int type = (Integer)map.get("type");
        String keywords = (String)map.get("keywords");
        int page = (Integer)map.get("page");
        int size = (Integer)map.get("size");
        StringBuilder sb = new StringBuilder();
        sb.append("select id,parentid,alarmname,level,turn,ispush,threshold,customid,isenable from trambasicinfo");
        sb.append(" where type="+type);
        if(!StringHelper.isEmpty(keywords)){
            sb.append(" and alarmname like '%"+keywords+"%'");
        }
        sb.append(" limit "+(page-1)*size+","+size);
        return sb.toString();
    }

    public  String getBasicListCount(Map<String,Object> map){
        int type = (Integer)map.get("type");
        String keywords = (String)map.get("keywords");
        StringBuilder sb = new StringBuilder();
        sb.append("select count(id) from trambasicinfo");
        sb.append(" where type="+type);
        if(!StringHelper.isEmpty(keywords)){
            sb.append(" and alarmname like '%"+keywords+"%'");
        }
        return sb.toString();
    }
}
