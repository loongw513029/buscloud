package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.MemberMapper;
import com.sztvis.buscloud.mapper.WorkOrderMapper;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.domain.TramRoleInfo;
import com.sztvis.buscloud.model.dto.WorkOrderViewModel;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.service.IMemberService;
import com.sztvis.buscloud.service.IWorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkOrderService implements IWorkOrderService {

    @Autowired
    private MemberMapper iMemberMapper;
    @Autowired
    private WorkOrderMapper iWorkOrderMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<WorkOrderViewModel> GetWorkOrders(String code,String start,String end)
    {
        return this.iWorkOrderMapper.GetWorkOrders(code,start,end);
    }
}
