package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.SiteSettingProvider;
import com.sztvis.buscloud.model.dto.SiteSettingsInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface SiteSettingMapper {
    @SelectProvider(type = SiteSettingProvider.class,method = "GetAppCharts")
    int GetAppCharts(int type, List<Long> LineArr, Long lineId, String date, String date2);

    @Select("select * from TramSiteSettingInfo where type=#{type}")
    LinkedList<SiteSettingsInfo> GetSiteSettings(int type);

    @Select("select * from TramSiteSettingInfo where Key=#{Key} and Type=#{Type}")
    SiteSettingsInfo GetGetSiteSettingsKey(@Param("key") String key,@Param("type") int type);

    @Insert("insert into TramSiteSettingInfo values(#{key},#{value},#{type})")
    void InsertSaveSetting(@Param("key") String key,@Param("value") Object value,@Param("type") int type);

    @Update("update TramSiteSettingInfo set Value=#{value} where Key=#{Key} and Type=#{Type}")
    void updateSaveSetting(@Param("key") String key,@Param("value") Object value,@Param("type") int type);
}
