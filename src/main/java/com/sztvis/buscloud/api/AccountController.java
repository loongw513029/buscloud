package com.sztvis.buscloud.api;

import com.sztvis.buscloud.core.RedisUtil;
import com.sztvis.buscloud.core.TramException;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.LoginParams;
import com.sztvis.buscloud.model.dto.api.ModifyPwdForm;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午4:59
 */
@RestController
@RequestMapping("/api/v1/account")
public class AccountController extends BaseApiController{

    @Autowired
    private IMemberService iMemberService;
    @Autowired
    private RedisUtil redisUtil;
    /**
     * 登录
     * @param loginParams
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ApiResult Login(LoginParams loginParams, HttpSession httpSession) throws Exception {
        CurrentUserInfo userInfo = null;
        try{
            userInfo=iMemberService.Login(loginParams);
            String uuid = UUID.randomUUID().toString().replace("-","");
            redisUtil.set(uuid,userInfo.getId().toString());
            userInfo.setAccessToken(uuid);
            if(loginParams.getLogintype()==3) {
                httpSession.setAttribute("user",userInfo);

            }
            return ApiResult(true,"登录成功", StatusCodeEnum.Success,userInfo);
        }
        catch (TramException ex){
            return ApiResult(false,ex.getMessage(),StatusCodeEnum.Error,null);
        }
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/loginout",method = RequestMethod.GET)
    public ApiResult LoginOut(HttpServletRequest request,HttpSession httpSession){
        String uuid = request.getHeader("access_token");
        redisUtil.delete(uuid);
        try{
            httpSession.removeAttribute("user");
        }catch (Exception ex){

        }
        return ApiResult(true,"退出登录登录",StatusCodeEnum.Success,null);
    }

    /**
     * 修改密码
     * @param modifyPwdForm
     * @return
     */
    @RequestMapping(value = "/ModifyPwd",method = RequestMethod.POST)
    public ApiResult ModifyPwd(ModifyPwdForm modifyPwdForm)
    {
        if (modifyPwdForm.getUserid()==0|| StringHelper.isNotEmpty(modifyPwdForm.getOldpwd())||StringHelper.isNotEmpty(modifyPwdForm.getNewpwd()))
            return ApiResult(false,"请求参数不完整或不正确",StatusCodeEnum.ParameterError,null);
        try {
            this.iMemberService.ChangePassWord(modifyPwdForm.getUserid(),modifyPwdForm.getOldpwd(),modifyPwdForm.getNewpwd());
            return ApiResult(true,"修改密码成功",StatusCodeEnum.Success,null);
        }
        catch (TramException ex){
            return ApiResult(false,ex.getMessage(),StatusCodeEnum.ParameterError,null);
        }
        catch (Exception ex){
            return ApiResult(false,ex.getMessage(),StatusCodeEnum.Error,null);
        }
    }
}
