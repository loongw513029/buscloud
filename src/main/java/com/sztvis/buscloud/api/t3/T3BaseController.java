package com.sztvis.buscloud.api.t3;

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

    @ModelAttribute
    public final void Init(HttpServletRequest request, HttpServletResponse response){
        String departmentcode = request.getHeader("departmentcode");
        String timespan = request.getHeader("timespan");
        String accesstoken = request.getHeader("accesstoken");
        String refreshtoken = request.getHeader("refreshtoken");

    }
}
