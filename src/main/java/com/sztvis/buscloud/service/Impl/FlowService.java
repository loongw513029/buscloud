package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.mapper.FlowMapper;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IFlowService;
import org.apache.catalina.connector.InputBuffer;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class FlowService implements IFlowService{
    @Value("${videourl}")
    private String videourl;
    @Autowired
    private IDeviceService ideviceService;
    @Autowired
    private FlowMapper flowMapper;

    @Override
    public void SensusAlarmVideo(){
        String url = videourl;
        List<String> codes = this.ideviceService.GetAllCarCodes(null);
        String now = DateUtil.getCurrentTime();
        String time = DateUtil.addDay(now,-1);
        for (String code : codes){
            TramDeviceInfo deviceInfo = this.ideviceService.GetDriverInfo(0,code);
            List<String> paths = this.flowMapper.GetCanPath(code,time,now);
            long size = 0;
            for (String path : paths){
                String filePath = url + path;
                File file = new File(filePath);
                if (file!=null)
                    size += file.length();
            }
            if (size!=0)
                this.flowMapper.UpdateHostFlowCensus(size,deviceInfo.getId(),time);
        }
    }
}
