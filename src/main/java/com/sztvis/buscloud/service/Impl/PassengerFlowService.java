package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.model.domain.TramPassengerFlow;
import com.sztvis.buscloud.service.IPassengerFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/29 上午11:20
 */
@Service
public class PassengerFlowService implements IPassengerFlowService {
    @Autowired
    private IPassengerFlowService iPassengerFlowService;
    @Override
    public void insertPassengerFlow(TramPassengerFlow passengerFlow) {
        this.iPassengerFlowService.insertPassengerFlow(passengerFlow);
    }
}
