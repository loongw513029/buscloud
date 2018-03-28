package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.UnSafeMapper;
import com.sztvis.buscloud.model.UnSafeListViewModel;
import com.sztvis.buscloud.model.UnSafeQuery;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IMemberService;
import com.sztvis.buscloud.service.IUnSafeService;
import com.sztvis.buscloud.util.DayTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnSafeService implements IUnSafeService {

    @Autowired
    private IMemberService iMemberService;
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private UnSafeMapper unSafeMapper;

    @Override
    public List<UnSafeListViewModel> GetUnsafeList(UnSafeQuery query)
    {
        List<Long> deviceIds=new ArrayList<>();
         if(StringHelper.isNotEmpty(query.getUserId()))
         {
             TramMemberInfo info=this.iMemberService.getMemberInfo(query.getUserId());
             CurrentUserInfo user=new CurrentUserInfo();
             user.setUserName(info.getUsername());
             user.setDepartmentId(info.getOwnershipid());
             deviceIds=this.iDeviceService.GetDeviceIdsByDepartmentId(user);
         }
        DayTypes types=DayTypes.getDayByType(query.getDayType());
        return this.unSafeMapper.GetUnsafeList(query,deviceIds,types);
    }
}
