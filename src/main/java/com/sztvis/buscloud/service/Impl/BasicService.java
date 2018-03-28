package com.sztvis.buscloud.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.sztvis.buscloud.core.RedisUtil;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.mapper.*;
import com.sztvis.buscloud.model.domain.*;
import com.sztvis.buscloud.model.dto.BasicViewModel;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.response.RoleViewModel;
import com.sztvis.buscloud.service.IBasicService;
import com.sztvis.buscloud.util.Constant;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private LineMapper lineMapper;


    /**
     * 获得该用户所能管理的设备编码集合
     * @param userId 用户Id
     * @return
     */
    @Override
    public List<String> GetDeviceScopeByUserId(long userId){
        TramMemberInfo user = memberMapper.getMemberById(userId);
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

    @Override
    public List<ComboTreeModel> getRoleList() {
        return this.getRoleTreeData(0);
    }

    @Override
    public List<ComboTreeModel> getMenuList() {
        return this.getMenuTreeData(0);
    }

    @Override
    public RoleViewModel getRoleInfo(long id) {
        return this.basicMapper.getRoleInfo(id);
    }

    @Override
    public void saveAndUpdateRole(RoleViewModel model) {
        TramRoleInfo roleInfo = new TramRoleInfo();
        roleInfo.setRolename(model.getRolename());
        roleInfo.setRemark(model.getRemark());
        roleInfo.setParentid(model.getParentId());
        roleInfo.setId(model.getId());
        if(model.getId() == 0){
            this.basicMapper.insertRoleInfo(roleInfo);
        }else{
            this.basicMapper.updateRoleInfo(roleInfo);
        }
        this.basicMapper.deleteRoelRelInfo(roleInfo.getId());
        this.basicMapper.insertRoleRelInfo(roleInfo.getId(),model.getRoleIds());
    }

    @Override
    public List<ComboTreeModel> getAlarmTypeListByParentId(long parentId) {
        return this.basicMapper.getAlarmTypeListByParentId(parentId);
    }

    @Override
    public List<BasicViewModel> getBasicList(int type, String keywords,int page,int size) {
        return this.basicMapper.getBasicList(type,keywords,page,size);
    }

    @Override
    public int getBasicListCount(int type, String keywords) {
        return this.basicMapper.getBasicListCount(type,keywords);
    }

    @Override
    public void insertBasicInfo(TramBasicInfo basicInfo) {
        this.basicMapper.insertAlarmConfig(basicInfo);
    }

    @Override
    public void updateBasicInfo(TramBasicInfo basicInfo) {
        this.basicMapper.updateAlarmConfig(basicInfo);
    }

    @Override
    public List<Long> getDeviceIdsByRoleLv(long userId) {
        TramMemberInfo user=this.memberMapper.getMemberById(userId);
        List<Long> LineIds = null;
        List<String> msg = null;
        Long roleLv=user.getRolelv();
        Long ownershipId=user.getOwnershipid();
        if (roleLv==1||roleLv==2)
        {
            LineIds=this.lineMapper.LineId(ownershipId);
        }
        else if(roleLv==3)
        {
            TramDepartmentInfo dp=this.departmentMapper.GetDepartmentsById(ownershipId);
            if (dp.getParentid()==0)
                LineIds=this.lineMapper.LineId(ownershipId);
            else {
                msg = Arrays.asList(user.getManagescope().split(","));
                for (String r : msg) {
                    LineIds.add(Long.valueOf(r));
                }
            }
        }
        if(roleLv==0)
        {
            if (user.getUsername()=="admin")
                return this.deviceMapper.getTramDeviceId();
            else
                return this.deviceMapper.getLineIdsByDepartmentId(ownershipId);
        }
        else
        {
            String Ids=LineIds.toString().replace("[","").replace("]","");
            return this.deviceMapper.getDeviceByLineIds(Ids);
        }
    }

    @Override
    public List<BasicViewModel> getBasicList(int type, String keywords) {
        return null;
    }

    private List<ComboTreeModel> getRoleTreeData(long parentId){
        List<TramRoleInfo> list = this.basicMapper.getRoleList(parentId);
        List<ComboTreeModel> list2 = new ArrayList<>();
        for(TramRoleInfo r:list){
            ComboTreeModel comboTreeModel = new ComboTreeModel();
            comboTreeModel.setId(new Long(r.getId()).intValue());
            comboTreeModel.setText(r.getRolename());
            comboTreeModel.setChildren(this.getRoleTreeData(r.getId()));
            list2.add(comboTreeModel);
        }
        return list2;
    }

    private List<ComboTreeModel> getMenuTreeData(long parentId){
        List<TramMenuInfo> list = this.basicMapper.getMenuList(parentId);
        List<ComboTreeModel> list2 = new ArrayList<>();
        for(TramMenuInfo r:list){
            ComboTreeModel comboTreeModel = new ComboTreeModel();
            comboTreeModel.setId(new Long(r.getId()).intValue());
            comboTreeModel.setText(r.getMenuname());
            comboTreeModel.setChildren(this.getMenuTreeData(r.getId()));
            list2.add(comboTreeModel);
        }
        return list2;
    }

    @Override
    public List<Long> GetAlarmKeysByUserId(long userId)
    {
        TramMemberInfo user=this.memberMapper.getMemberById(userId);
        return this.basicMapper.GetAlarmKeysByUserId(userId,user.getRolelv());
    }
}
