package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.SiteSettingsInfo;
import com.sztvis.buscloud.model.dto.service.ChartViewModel;;import java.lang.reflect.InvocationTargetException;

public interface ISiteSettingService {
    ChartViewModel GetAppCharts(long userId, long lineId);

    public SiteSettingsInfo GetSiteSettings(int type) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException;
}
