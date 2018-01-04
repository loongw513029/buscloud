package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.LineMapper;
import com.sztvis.buscloud.model.domain.Tramlineinfo;
import com.sztvis.buscloud.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:26
 */
@Service
public class LineService implements ILineService {

    @Autowired
    private LineMapper lineMapper;

    @Override
    public List<Tramlineinfo> GetLinesByDepartmentId(long departmentId) {
        return lineMapper.GetLinesByDepartmentId(departmentId);
    }
}
