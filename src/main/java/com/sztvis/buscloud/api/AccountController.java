package com.sztvis.buscloud.api;

import com.sztvis.buscloud.core.RedisUtil;
import com.sztvis.buscloud.core.TramException;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.LoginParams;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.service.IMemberService;
import com.sztvis.buscloud.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
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
    private RedisService redisService;
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
            return ApiResult(true,"登录成功","200",userInfo);
        }
        catch (TramException ex){
            return ApiResult(false,ex.getMessage(),"501",null);
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
        return ApiResult(true,"退出登录登录","200",null);
    }
}
