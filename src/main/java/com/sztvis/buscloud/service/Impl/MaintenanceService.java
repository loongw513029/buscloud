package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.MaintenanceMapper;
import com.sztvis.buscloud.model.dto.MaintenanceInfo;
import com.sztvis.buscloud.service.IBasicService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService implements IMaintenanceService{

    @Autowired
    private IBasicService iBasicService;
    @Autowired
    private MaintenanceMapper maintenanceMapper;

    @Override
    public List<MaintenanceInfo> GetCurrentAccountsMaintenanceList(long userId)
    {
        List<Long> deviceIds=this.iBasicService.getDeviceIdsByRoleLv(userId);
        return this.maintenanceMapper.GetCurrentAccountsMaintenanceList(StringHelper.getLists(deviceIds.toString()));
    }

    @Override
    public List<MaintenanceInfo> GetMaintenanceInfo(String Code,long DepartmentId,long lineId,String start,String end){
        return this.maintenanceMapper.GetMaintenance(Code,DepartmentId,lineId,start,end);}

    @Override
    public void AddMaintenanceInfo(MaintenanceInfo maintenanceInfo)
    {
        this.maintenanceMapper.AddMaintenanceInfo(maintenanceInfo);
    }
}
