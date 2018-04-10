package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.DataAlarmConfigViewModel;

import java.util.Map;

public interface IConfigService {
    DataAlarmConfigViewModel GetAllConfigs()throws Exception;
}
