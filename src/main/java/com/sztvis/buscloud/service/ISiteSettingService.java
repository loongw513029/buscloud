package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.SiteSettingsInfo;
import com.sztvis.buscloud.model.dto.service.ChartViewModel;;import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ISiteSettingService {
    ChartViewModel GetAppCharts(long userId, long lineId);

    SiteSettingsInfo GetSiteSettings(List<String> key) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException;

    void SaveSetting(String key, Object value);

    SiteSettingsInfo GetSave();
}
