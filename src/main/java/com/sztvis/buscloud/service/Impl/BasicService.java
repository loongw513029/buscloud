package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.MemberMapper;
import com.sztvis.buscloud.model.domain.Trammemberinfo;
import com.sztvis.buscloud.service.IBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午5:49
 */
@Service
public class BasicService implements IBasicService{

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 获得该用户所能管理的设备编码集合
     * @param userId 用户Id
     * @return
     */
    @Override
    public List<String> GetDeviceScopeByUserId(long userId){
        Trammemberinfo user = memberMapper.getMemberById(userId);
        List<Long> LineIds= null;
        if(!StringHelper.isEmpty(user.getManagescope())){
            LineIds =StringHelper.StringsToLongs(user.getManagescope().split(","));
            return deviceMapper.GetDeviceCodesByLineIds(LineIds);
        }else{
            if(user.getUsername().equals("admin")){
                return deviceMapper.GetDeviceCodes();
            }else{
                return deviceMapper.GetDeviceCodesByDepartmentId(user.getOwnershipid());
            }
        }
    }
}
