package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 上午11:58
 */
public class BaseApiController {

    public BaseApiController(){

    }


    private CurrentUserInfo CurrentUserInfo;

    protected CurrentUserInfo getCurrentUserInfo() {
        //redisService.get()
        return CurrentUserInfo;
    }
    /**
     * 公共返回函数
     * @param success 数据是否成功
     * @param msg 提示信息
     * @param code 返回代码
     * @param object 具体内容
     * @return
     */
    protected ApiResult ApiResult(boolean success, String msg, StatusCodeEnum code, Object object){
        ApiResult obj = new ApiResult();
        obj.setCode(code.getValue());
        obj.setInfo(msg);
        obj.setResult(object);
        obj.setSuccess(success);
        return obj;
    }

    @ModelAttribute
    public final void Init(HttpServletRequest request,HttpServletResponse response){

    }


}
