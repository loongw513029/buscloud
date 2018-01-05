package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class BaseController {
    public CurrentUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(CurrentUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    private CurrentUserInfo userInfo;


    @ModelAttribute
    public final void init(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CurrentUserInfo user = (CurrentUserInfo)request.getSession().getAttribute("user");
        if(user == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // 跳到登录页面
            return;
        }else{
            setUserInfo(user);
        }
    }


}

