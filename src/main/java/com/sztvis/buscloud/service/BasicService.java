package com.sztvis.buscloud.service;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.MemberMapper;
import com.sztvis.buscloud.model.domain.Trammemberinfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/28 下午5:49
 */
public class BasicService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 获得该用户所能管理的设备编码集合
     * @param userId 用户Id
     * @return
     */
    public List<String> GetDeviceScopeByUserId(Long userId){
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
