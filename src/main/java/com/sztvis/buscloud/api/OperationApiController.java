package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.core.helper.ListHelper;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.DeviceViewModel;
import com.sztvis.buscloud.model.dto.LineViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    }

}
