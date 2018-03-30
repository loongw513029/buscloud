package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.OneKeyInspectRecords;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/29 上午11:11
 */
@Repository
public interface InspectMapper {
    @Insert("insert into OneKeyInspectRecords(deviceId,updateTime,inspectPics)values(#{deviceId},#{updateTime},#{inspectPics})")
    void insertInspectRecords(OneKeyInspectRecords oneKeyInspectRecords);
}
