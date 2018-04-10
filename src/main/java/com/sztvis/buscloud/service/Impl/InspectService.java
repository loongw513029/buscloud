package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.InspectMapper;
import com.sztvis.buscloud.model.domain.OneKeyInspectRecords;
import com.sztvis.buscloud.service.IInspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/3/29 上午11:09
 */
@Service
public class InspectService implements IInspectService {

    @Autowired
    private InspectMapper inspectMapper;
    public void insertOneKeyInspectRecords(OneKeyInspectRecords oneKeyInspectRecords) {
        this.inspectMapper.insertInspectRecords(oneKeyInspectRecords);
    }
}
