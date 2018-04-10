package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.DepartmentMapper;
import com.sztvis.buscloud.mapper.DeviceMapper;
import com.sztvis.buscloud.mapper.LineMapper;
import com.sztvis.buscloud.mapper.MemberMapper;
import com.sztvis.buscloud.model.domain.TramChannelInfo;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.domain.TramLineInfo;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.*;
import com.sztvis.buscloud.model.dto.AppNumViewModel;
import com.sztvis.buscloud.model.dto.SelectViewModel;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:26
 */
@Service
public class LineService implements ILineService {

    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private MemberMapper iMemberMapper;
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private DepartmentMapper iDepartmentMapper;
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<TramLineInfo> GetLinesByDepartmentId(long departmentId) {
        return lineMapper.GetLinesByDepartmentId(departmentId);
    }

    @Override
    public List<Long> GetLineIdsByUserId(long userId) {
        return null;
    }

    @Override
    public List<LineViewModel> getList(long userId, String linename, long departmentId) {
        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return  this.lineMapper.getList(departments,linename,departmentId);
    }

    @Override
    public TramLineInfo getLineInfo(long Id) {
        return this.lineMapper.getLineInfo(Id);
    }

    @Override
    public List<ComboTreeModel> getLineTreeList(long userId) {
        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return this.lineMapper.getLineTreeList(departments);
    }

    @Override
    public void saveAndUpdateLine(LineViewModel lineViewModel) {
        TramLineInfo lineInfo = new TramLineInfo();
        lineInfo.setId(lineViewModel.getId());
        lineInfo.setLinecode(lineViewModel.getLinecode());
        lineInfo.setLinename(lineViewModel.getLinename());
        lineInfo.setDepartmentid(lineViewModel.getDepartmentid());
        lineInfo.setLineupmileage(Double.valueOf(lineViewModel.getLineupmileage()));
        lineInfo.setLinedownmileage(Double.valueOf(lineViewModel.getLinedownmileage()));
        lineInfo.setDownsitenum(Long.valueOf(lineViewModel.getDownsitenum()));
        lineInfo.setUpsitenum(Long.valueOf(lineViewModel.getUpsitenum()));
        if(lineInfo.getId()==0)
            this.lineMapper.saveLine(lineInfo);
        else
            this.lineMapper.updateLine(lineInfo);
    }

    @Override
    public void removeLines(String lineIds) {
        this.lineMapper.removeLine(lineIds);
    }

    @Override
    public AppNumViewModel GetAppNumByLineId(Long lineId){
        AppNumViewModel appNumViewModel=new AppNumViewModel();
        Date date=new Date();
        DateFormat time=new SimpleDateFormat("yyyy-MM-dd");
        String Nowtime=time.format(date.getTime());
        appNumViewModel.setCarNum(this.lineMapper.carNum(lineId));
        appNumViewModel.setOnLineNum(this.lineMapper.onlineNum(lineId));
        appNumViewModel.setUnSafeNum(this.lineMapper.unsafeNum(lineId,Nowtime));
        appNumViewModel.setLineNum(1);
        return appNumViewModel;
    }

    @Override
    public List<SelectViewModel> GetDropDownLine(Long userId){
        List<SelectViewModel> list=new ArrayList<>();
        TramMemberInfo user=this.iMemberMapper.getMemberById(userId);
        List<Long> arr=this.iDepartmentMapper.GetPartmentIdsByDepartmentId(userId);
        if (user.getRolelv()<3)
        {
            if (user.getRolelv()==0){
                if (user.getUsername()=="admin")
                {
                    list = this.lineMapper.GetDropDownLine(user,1,null,null);
                }
                else {
                    list = this.lineMapper.GetDropDownLine(user,2,arr,null);
                }
            }
            else {

                list = this.lineMapper.GetDropDownLine(user,2,arr,null);
            }
        }
        else {
            String msg=user.getManagescope();
            list = this.lineMapper.GetDropDownLine(user,4,null,msg);
        }
         return list;
    }

    @Override
    public List<TramDeviceInfo>  getDevices(long lineId,long userId)
    {
        userId=0;
        List<Long> deviceIds=new ArrayList<>();
        if (userId!=0)
        {
            TramMemberInfo info = this.iMemberMapper.getMemberById(userId);
            deviceIds=this.deviceMapper.getLineIdsByDepartmentId(info.getOwnershipid());
        }
        List<TramDeviceInfo> list=this.deviceMapper.getDevices(deviceIds,lineId);
        return list;
    }

    @Override
    public List<TramChannelInfo> GetChannlsByDeviceId(long deviceId)
    {
        return this.lineMapper.GetChannlsByDeviceId(deviceId);
    }
}
