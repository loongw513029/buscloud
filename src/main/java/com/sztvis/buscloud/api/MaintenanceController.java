package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.api.BaseApiController;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.model.UnSafeListViewModel;
import com.sztvis.buscloud.model.dto.MaintenanceInfo;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/maintenance")
public class MaintenanceController extends BaseApiController {

    @Autowired
    private IMaintenanceService iMaintenanceService;

    @RequestMapping(value= "/list" ,method= RequestMethod.GET )
    public ApiResult GetMaintenanceList(long userId, int page, int limit)
    {
        PageHelper.startPage(page,limit);
        if (userId==0)
            return ApiResult(false,"用户Id错误", StatusCodeEnum.ParameterError,null);
        List<MaintenanceInfo> list=this.iMaintenanceService.GetCurrentAccountsMaintenanceList(userId);
        int count = list.size();
        PageBean<MaintenanceInfo> pageData = new PageBean<>(page, limit, count);
        pageData.setItems(list);
        return ApiResult(true, "Success", StatusCodeEnum.Success, pageData);
    }

    @RequestMapping(value= "/add" ,method= RequestMethod.POST )
    public ApiResult AddMaintenanceInfo(MaintenanceInfo maintenanceInfo)
    {
        if (maintenanceInfo.getDeviceId()==0)
            return ApiResult(false,"请选择设备", StatusCodeEnum.ParameterError,null);
        if (StringHelper.isNotEmpty(maintenanceInfo.getMtDate())||StringHelper.isNotEmpty(maintenanceInfo.getNextDate())|maintenanceInfo.getMtMileage()== BigDecimal.ZERO||maintenanceInfo.getNextMileage()==BigDecimal.ZERO)
            return ApiResult(false,"信息不完整", StatusCodeEnum.ParameterError,null);
        try {
            this.iMaintenanceService.AddMaintenanceInfo(maintenanceInfo);
            return ApiResult(true, "添加维保记录成功", StatusCodeEnum.Success, null);
        }
        catch (Exception ex)
        {
            return ApiResult(false,ex.getMessage(), StatusCodeEnum.Error,null);
        }
    }
}
