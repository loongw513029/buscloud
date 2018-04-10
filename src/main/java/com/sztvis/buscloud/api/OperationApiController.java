package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.core.helper.ListHelper;
import com.sztvis.buscloud.model.domain.TramBasicInfo;
import com.sztvis.buscloud.model.domain.TramDriverInfo;
import com.sztvis.buscloud.model.dto.*;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IBasicService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IDriverService;
import com.sztvis.buscloud.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/18 下午2:13
 */
@RestController
@RequestMapping("/api/v1/operation")
public class OperationApiController extends BaseApiController{

    @Autowired
    private ILineService iLineService;
    @Autowired
    private IDeviceService iDeviceService;
    @Autowired
    private IBasicService iBasicService;
    @Autowired
    private IDriverService iDriverService;
    /**
     * get line collections show web page
     * @param userId
     * @param linename
     * @param departmentId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping(value = "/linelist",method = RequestMethod.GET)
    public ApiResult getLineList(long userId,String linename,int departmentId,int page,int rows){
        PageHelper.startPage(page,rows);
        List<LineViewModel> list = this.iLineService.getList(userId,linename,departmentId);
        int count = list.size();
        PageBean<LineViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "线路列表获取成功", StatusCodeEnum.Success, pageData);
    }

    /**
     * get device list show web page
     * @param userId
     * @param devicetype deviceType(1:NVR,0:DVR)
     * @param departmentId organzation Id
     * @param lineId line Id
     * @param status device online status (1:online,-1:offline,0:fivedayoffline)
     * @param keywords search keywords,scopes devicecode,devicename,busnumber
     * @return
     */
    @RequestMapping(value = "/buslist",method = RequestMethod.GET)
    public ApiResult getBusList(long userId,int devicetype,long departmentId,long lineId,int status,String keywords,int page,int rows){
        PageHelper.startPage(page,rows);
        List<DeviceViewModel> list = this.iDeviceService.getList(userId,devicetype,departmentId,lineId,status,keywords);
        int count = list.size();
        PageBean<DeviceViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "线路设备获取成功", StatusCodeEnum.Success, pageData);
    }

    /**
     * use in map page bottom devicelist
     * @param devices
     * @return
     */
    @RequestMapping("/mapdevicelist")
    public ApiResult MapDeviceList(String devices,int page,int rows){
        //PageHelper.startPage(page,rows);
        List<MapDeviceViewModel> list = this.iDeviceService.getMapDeviceList(devices);
//        int count = list.size();
//        PageBean<MapDeviceViewModel> pageData = new PageBean<>(page, rows, count);
//        pageData.setItems(list);
        return ApiResult(true, "线路设备获取成功", StatusCodeEnum.Success, list);
    }
    /**
     * get line dropdownlist,use in easyui combotree
     * @param userid userid
     * @return
     */
    @RequestMapping(value = "/linecombo",method = RequestMethod.GET)
    public ApiResult getDepartmentComboTreeData(long userid){
        List<ComboTreeModel> list = this.iLineService.getLineTreeList(userid);
        return  ApiResult(true,"获得下拉列表成功", StatusCodeEnum.Success,ListHelper.addFirstEleComboTree(list,"-选择线路-"));
    }

    /**
     * saver line info
     * @param lineViewModel
     * @return
     */
    @RequestMapping(value = "/saveline",method = RequestMethod.POST)
    public ApiResult saveLine(LineViewModel lineViewModel){
        try{
            this.iLineService.saveAndUpdateLine(lineViewModel);
            return ApiResult(true,"保存线路成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return ApiResult(false,"保存线路失败",StatusCodeEnum.Error,null);
        }
    }

    @RequestMapping(value = "/removeline",method = RequestMethod.DELETE)
    public ApiResult removeLisr(String lineIds){
        try{
            this.iLineService.removeLines(lineIds);
            return ApiResult(true,"删除线路成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return ApiResult(false,"删除线路失败",StatusCodeEnum.Error,null);
        }
    }
    @RequestMapping("/getdrivercombo")
    public ApiResult getDriverList(long departmentid){
        try{
            List<ComboTreeModel> list = this.iDeviceService.getDriverComboList(departmentid);
            return ApiResult(true,"获得司机下拉列表成功",StatusCodeEnum.Success,ListHelper.addFirstEleComboTree(list,"-选择司机-"));
        }catch (Exception ex){
            return ApiResult(false,"获得司机下拉列表失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    /**
     * save web page bus infos
     * @param model
     * @return
     */
    @RequestMapping(value = "/savebus",method = RequestMethod.POST)
    public ApiResult saveBus(BusAndDeviceViewModel model){
        try{
            this.iDeviceService.saveDeviceInfo(model);
            return ApiResult(true,"保存车辆信息成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,"保存车辆信息失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    @RequestMapping(value = "/removebus",method = RequestMethod.DELETE)
    public ApiResult removeBus(String deviceids){
        try{
            this.iDeviceService.removeDeviceInfo(deviceids);
            return ApiResult(true,"删除车辆成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return ApiResult(false,"删除车辆失败",StatusCodeEnum.Error,null);
        }
    }
    @RequestMapping(value = "/alarmtypelist",method = RequestMethod.GET)
    public ApiResult getBasicList(int type,String keywords,int page,int rows){

        List<BasicViewModel> list = this.iBasicService.getBasicList(type,keywords,page,rows);
        int count = this.iBasicService.getBasicListCount(type,keywords);
        PageBean<BasicViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "报警类型获取成功", StatusCodeEnum.Success, pageData);
    }

    @RequestMapping(value = "/updatealarmconfig",method = RequestMethod.PUT)
    public ApiResult UpdateBasicAlarm(BasicViewModel viewModel){
        TramBasicInfo basicInfo = new TramBasicInfo();
        System.out.println(viewModel.getIsenable());
        basicInfo.setAlarmName(viewModel.getAlarmname());
        basicInfo.setCustomId(viewModel.getCustomid()+"");
        basicInfo.setEnable(viewModel.getIsenable().equals("1"));
        basicInfo.setFixe(false);
        basicInfo.setId((long)viewModel.getId());
        basicInfo.setLevel((long)viewModel.getLevel());
        basicInfo.setParentId((long)viewModel.getParentid());
        basicInfo.setPush(viewModel.getIspush().equals("1"));
        basicInfo.setTurn(viewModel.getTurn().equals("1"));
        basicInfo.setThreShold("");
        try{
            this.iBasicService.updateBasicInfo(basicInfo);
            return ApiResult(true,"修改记录成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,"修改记录失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    @RequestMapping(value = "/getdriverlist",method = RequestMethod.GET)
    public ApiResult getDriverList(String drivername,long departmentid,int page,int rows){
        List<DriverViewModel> list = this.iDriverService.getDriverList(drivername,departmentid,page,rows);
        int count = this.iDriverService.getDriverListCount(drivername,departmentid);
        PageBean<DriverViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "司机列表获取成功", StatusCodeEnum.Success, pageData);
    }

    @RequestMapping(value = "/savedriver",method = RequestMethod.POST)
    public ApiResult SaveDriver(TramDriverInfo driverInfo){
        try{
            driverInfo.setStatus(1L);
            this.iDriverService.SaveAndUpdateDriver(driverInfo);
            return ApiResult(true,"保存司机信息成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,"保存司机信息失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    @RequestMapping(value = "/removedriver",method = RequestMethod.DELETE)
    public ApiResult removeDriver(String ids){
        List<String> idsArr = Arrays.asList(ids.split(","));
        try{
            this.iDriverService.RemoveDrivers(idsArr);
            return ApiResult(true,"删除车辆成功",StatusCodeEnum.Success,null);
        }
        catch (Exception ex){
            return ApiResult(false,"删除车辆失败",StatusCodeEnum.Error,null);
        }
    }
}
