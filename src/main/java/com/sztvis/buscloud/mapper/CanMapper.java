package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.TramUnsafeBehaviorInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/1 下午3:19
 */
@Repository
public interface CanMapper {

    @Insert("insert into TramUnsafeBehaviorInfo(deviceid,devicecode,unsafetype,unsafetime,speed,ratespeed,applytime,location,createtime)values(#{deviceid},#{devicecode},#{unsafetype},#{unsafetime},#{speed},#{ratespeed},#{applytime},#{location},#{createtime})")
    void insertUnsafeInfo(TramUnsafeBehaviorInfo unsafeBehaviorInfo);
}
