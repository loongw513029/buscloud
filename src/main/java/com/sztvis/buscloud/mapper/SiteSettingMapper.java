package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.SiteSettingProvider;
import com.sztvis.buscloud.model.dto.SiteSettingsInfo;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public interface SiteSettingMapper {
    @SelectProvider(type = SiteSettingProvider.class,method = "GetAppCharts")
    int GetAppCharts(int type, List<Long> LineArr, Long lineId, String date, String date2);

    @Select("select * from TramSiteSettingInfo where type=#{type}")
    LinkedList<SiteSettingsInfo> GetSiteSettings(int type);
}
