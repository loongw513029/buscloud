package com.sztvis.buscloud.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sztvis.buscloud.core.RedisUtil;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.BasicMapper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.MemberMapper;
import com.sztvis.buscloud.model.domain.TramBasicInfo;
import com.sztvis.buscloud.model.domain.Trammemberinfo;
import com.sztvis.buscloud.service.IBasicService;
import com.sztvis.buscloud.util.Constant;
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
    @Autowired
    private BasicMapper basicMapper;
    @Autowired
    private RedisUtil redisUtil;

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
            return deviceMapper.getDeviceCodesByLineIds(LineIds);
        }else{
            if(user.getUsername().equals("admin")){
                return deviceMapper.getDeviceCodes();
            }else{
                return deviceMapper.getDeviceCodesByDepartmentId(user.getOwnershipid());
            }
        }
    }

    @Override
    public List<Integer> getCustomIdsByType(int type) throws Exception{
        String data = redisUtil.get(Constant.BasicTypeRedis+type);
        if(data != null){
            return JSON.parseObject(data,new TypeReference<List<Integer>>(){});
        }else{
            List<Integer> list = this.basicMapper.getCustomIdsByType(type);
            redisUtil.set(Constant.BasicTypeRedis+type, JSON.toJSONString(list));
            return this.basicMapper.getCustomIdsByType(type);
        }
    }

    @Override
    public TramBasicInfo getBasicInfoByCustomId(int customId) {
        return this.basicMapper.getBasicInfoByCustomId(customId);
    }
}
