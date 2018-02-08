package com.sztvis.buscloud.api.t3;

import com.sztvis.buscloud.model.domain.TramDepartmentInfo;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/2/8 上午10:36
 */
@RestController
@RequestMapping("api/v3/department")
public class DepartmentV3Controller extends T3BaseController {
    @Autowired
    private IDepartmentService iDepartmentService;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ApiResult GetDepartments(){
        List<TramDepartmentInfo> list = this.iDepartmentService.getDepartmentListByCode(getDepartmentCode());
        return ApiResult(true,"机构列表获取成功",StatusCodeEnum.Success,list);
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ApiResult SaveDepartments(TramDepartmentInfo departmentInfo){
        try{
            this.iDepartmentService.addDepartmentInfo(departmentInfo);
            return ApiResult(true,"机构保存成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,"机构保存失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

    @RequestMapping(value = "remove",method = RequestMethod.DELETE)
    public ApiResult RemoveDepartments(String departmentids){
        try{
            this.iDepartmentService.removeDepartmentInfo(departmentids);
            return ApiResult(true,"删除机构成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,"删除机构失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

}
