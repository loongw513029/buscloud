package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.DepartmentMapper;
import com.sztvis.buscloud.model.domain.Tramdepartmentinfo;
import com.sztvis.buscloud.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午1:47
 */
@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public Tramdepartmentinfo GetParentPartmentIdsByUserId(long userId) {
        Tramdepartmentinfo departmentInfo = departmentMapper.GetDepartmentIdsByUserId(userId);
        if(departmentInfo.getParentid()!=0){
            departmentInfo = departmentMapper.GetDepartmentInfo(departmentInfo.getParentid());
        }
        return departmentInfo;
    }

    @Override
    public List<Tramdepartmentinfo> GetParentsByParentId(long departmentId) {
        return departmentMapper.GetDepartmentsByParentId(departmentId);
    }

}
