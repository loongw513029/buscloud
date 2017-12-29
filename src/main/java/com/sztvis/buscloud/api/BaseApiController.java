package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.dto.response.ApiResult;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 上午11:58
 */
public class BaseApiController {
    /**
     * 公共返回函数
     * @param success 数据是否成功
     * @param msg 提示信息
     * @param code 返回代码
     * @param object 具体内容
     * @return
     */
    protected ApiResult CreateApiMsg(boolean success,String msg,String code,Object object){
        ApiResult obj = new ApiResult();
        obj.setCode(code);
        obj.setInfo(msg);
        obj.setResult(object);
        obj.setSuccess(success);
        return obj;
    }
}
