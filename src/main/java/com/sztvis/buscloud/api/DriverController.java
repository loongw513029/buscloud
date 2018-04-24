package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.api.BaseApiController;
import com.sztvis.buscloud.model.UnSafeListViewModel;
import com.sztvis.buscloud.model.UnSafeQuery;
import com.sztvis.buscloud.model.dto.BicycleViewModel;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.DeviceInspectViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IAlarmService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.IDriverService;
import com.sztvis.buscloud.service.IUnSafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver/")
public class DriverController extends BaseApiController {

    @Autowired
    private IUnSafeService iUnSafeService;

    @Autowired
    private IAlarmService iAlarmService;
    @Autowired
    private IDriverService iDriverService;

    /**
     * 获得不安全行为列表
     * @param daytype 日期类型
     * @param unsafetype 不安全类型
     * @param lineId 线路ID
     * @param page 页码
     * @param limit 页数
     * @return 不安全列表
     */
    @RequestMapping(value = "/unsafelist",method = RequestMethod.GET)
    public ApiResult GetUnSafeList(long userid, int daytype, int unsafetype, long lineId, int page, int limit){
        PageHelper.startPage(page,limit);
        UnSafeQuery query=new UnSafeQuery();
        query.setDayType(daytype);
        query.setDeviceCode("");
        query.setLineId(lineId);
        query.setPageNo(page);
        query.setPageSize(limit);
        query.setUnSafeType(unsafetype);
        if(String.valueOf(userid)!=null)
            query.setUserId(userid);
        else
            query.setUserId(0);
        List<UnSafeListViewModel> list=this.iUnSafeService.GetUnsafeList(query);
        int count = list.size();
        PageBean<UnSafeListViewModel> pageData = new PageBean<>(page, limit, count);
        pageData.setItems(list);
        return ApiResult(true, "巡检列表获取成功", StatusCodeEnum.Success, pageData);
    }

    /**
     * 获得单车不安全行为分析
     * @param daytype
     * @param deviceId
     * @return
     */

    @RequestMapping(value = "/getbicycleunsafeinfo",method = RequestMethod.GET)
    public ApiResult GetBicycleUnSafeInfo(int daytype,long deviceId){
        try {
            BicycleViewModel model=this.iAlarmService.GetBicycleUnSafeInfo(daytype,deviceId);
            return ApiResult(true, "巡检列表获取成功", StatusCodeEnum.Success, model);
        }
        catch (Exception ex) {
            return ApiResult(false, "获取数据失败", StatusCodeEnum.Error, null);
        }
    }

}
