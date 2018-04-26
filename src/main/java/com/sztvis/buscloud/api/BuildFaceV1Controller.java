package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.dto.BuildFaceViewModel;
import com.sztvis.buscloud.model.dto.DeviceInspectViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.*;
import com.sztvis.buscloud.service.Impl.FlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v1/buildface")
public class BuildFaceV1Controller extends BaseApiController{

    @Autowired
    private IBuildFaceService iBuildFaceService;
    @Autowired
    private IUnSafeService iUnSafeService;

    @RequestMapping("/getbuildfacelist")
    public ApiResult getBuildFaceList(long userId,String keywords,long departmentId,int sex,int page,int rows){
        List<BuildFaceViewModel> list = this.iBuildFaceService.getBuildFaceList(userId,keywords,departmentId,sex,page,rows);
        int count = this.iBuildFaceService.getBuildFaceCountList(userId,keywords,departmentId,sex);
        PageBean<BuildFaceViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "人员资料获取成功", StatusCodeEnum.Success, pageData);
    }


    @RequestMapping(value = "saveperson",method = RequestMethod.POST)
    public ApiResult saveOrUpdatePerson(BuildFaceViewModel viewModel, HttpServletRequest request){
        try{
            this.iBuildFaceService.saveOrUpdatePerson(viewModel,request);
            return ApiResult(true, "新增人员资料成功", StatusCodeEnum.Success, null);
        }
        catch (Exception ex){
            return ApiResult(false, "新增人员资料失败", StatusCodeEnum.Success, ex.getMessage());
        }
    }

    @RequestMapping(value = "getCanHistoryCode",method = RequestMethod.GET)
    public void EveryDayCanHandle() throws Exception {
        this.iUnSafeService.CalcUnsafeIndex();
    }

}
