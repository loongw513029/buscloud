package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.model.domain.TramBasicInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午5:32
 */

@Repository
public interface BasicMapper {

    @Select("select * from TramBasicInfo where id=#{Id}")
    TramBasicInfo getBasicInfoById(long Id);

    @Select("select customId from TramBasicInfo where type=#{type} and parentId>0 and customId<>''")
    List<Integer> getCustomIdsByType(int type);

    @Select("select * from TramBasicInfo where customId=#{customId} and parentId>0 and customId<>''")
    TramBasicInfo getBasicInfoByCustomId(int customId);
}
