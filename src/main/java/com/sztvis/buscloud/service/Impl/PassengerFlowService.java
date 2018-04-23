package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.DateStyle;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.PassengerFlowMapper;
import com.sztvis.buscloud.model.domain.TramPassengerFlow;
import com.sztvis.buscloud.service.IPassengerFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/29 上午11:20
 */
@Service
public class PassengerFlowService implements IPassengerFlowService {
    @Autowired
    private IPassengerFlowService iPassengerFlowService;
    @Autowired
    private PassengerFlowMapper passengerFlowMapper;

    @Override
    public void insertPassengerFlow(TramPassengerFlow passengerFlow) {
        this.iPassengerFlowService.insertPassengerFlow(passengerFlow);
    }

    @Override
    public List<TramPassengerFlow> Getinfo(String code){
        String time = DateUtil.getCurrentTime(DateStyle.YYYY_MM_DD_23_59_59);
        List<TramPassengerFlow> list = new ArrayList<>();
        for (int i = 0;i < 3;i++){
            list.add(this.passengerFlowMapper.Getinfo(code,DateUtil.addDay(time,-i)));
        }
        return list;
    }
}
