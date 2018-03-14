package com.sztvis.buscloud.api;


import com.sztvis.buscloud.api.BaseApiController;
import com.sztvis.buscloud.model.dto.AppHomeViewModel;
import com.sztvis.buscloud.model.dto.AppSelectViewModel;
import com.sztvis.buscloud.model.dto.SelectViewModel;
import com.sztvis.buscloud.model.dto.WelcomeTrendModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IAlarmService;
import com.sztvis.buscloud.service.IHomeService;
import com.sztvis.buscloud.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/home")
public class HomeV1Controller extends BaseApiController {

    @Autowired
    private ILineService iLineService;
    @Autowired
    private IHomeService iHomeService;
    //获取首页图表数据
    @RequestMapping(value = "/lines",method = RequestMethod.GET)
    public ApiResult GetLines(long userId,long departmentId)
    {
        try {
            List<SelectViewModel> list = this.iLineService.GetDropDownLine(userId);
            List<AppSelectViewModel> list1 = new ArrayList<>();
            for (SelectViewModel r : list) {
                AppSelectViewModel model = new AppSelectViewModel();
                model.setId(r.getId());
                model.setName(r.getValue());
                model.setOnline(true);
                model.setNumData(iLineService.GetAppNumByLineId((long) r.getId()));
                list1.add(model);
            }
            AppHomeViewModel model = new AppHomeViewModel();
            model.setLines(list1);
            return ApiResult(true,"获取数据成功",StatusCodeEnum.Success,model);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ApiResult result = new ApiResult();
            result.setSuccess(false);
            result.setCode(StatusCodeEnum.Error.toString());
            result.setInfo("获取数据失败！");
            return result;
        }
    }

    /**
     * 获得web页面趋势数据
     * @param userId
     * @return
     */
    @RequestMapping("/getWebTrends")
    public ApiResult getWebTrends(long userId){
        try {
            WelcomeTrendModel welcomeTrendModel = this.iHomeService.getWelcomeTrendModels(userId);
            return ApiResult(true, "获取WEB页面趋势数据成功", StatusCodeEnum.Success, welcomeTrendModel);
        }
        catch (Exception ex){
            return ApiResult(false, "获取WEB页面趋势数据失败", StatusCodeEnum.Error, ex.getMessage());
        }
    }
//
//    @RequestMapping(value = "/getcharts",method = RequestMethod.GET)
//    public ApiResult GetCharts(long userId,long lineId)
//    {
//
//    }
}
