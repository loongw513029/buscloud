package com.sztvis.buscloud.api.t3;

import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/30 上午9:16
 */
@RestController
public class T3BaseController {

    private String departmentCode;

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    @ModelAttribute
    public final void Init(HttpServletRequest request, HttpServletResponse response){
        String departmentcode = request.getHeader("departmentcode");
        this.setDepartmentCode(departmentcode);
    }

    protected ApiResult ApiResult(boolean success, String msg, StatusCodeEnum code, Object object){
        ApiResult obj = new ApiResult();
        obj.setCode(code.getValue());
        obj.setInfo(msg);
        obj.setResult(object);
        obj.setSuccess(success);
        return obj;
    }
}
