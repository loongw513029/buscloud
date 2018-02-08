package com.sztvis.buscloud.config;

import com.alibaba.fastjson.JSON;
import com.sztvis.buscloud.model.domain.TramDepartmentInfo;
import com.sztvis.buscloud.model.domain.V3TokenInfo;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.ITokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/2/7 下午3:43
 */
@WebFilter(urlPatterns = {"/api/v3/*"},filterName = "tokenAuthorFilter")
public class V3TokenAuthorFilter implements Filter {
    @Autowired
    private ITokenService iTokenService;
    @Autowired
    private IDepartmentService iDepartmentService;

    private static Logger logger =
            LoggerFactory.getLogger(V3TokenAuthorFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse rep = (HttpServletResponse)servletResponse;
        String url = req.getRequestURL().toString().toLowerCase();
        boolean isFilter = false;
        if(!url.contains("/api/v3/token")){
            String departmentcode = req.getHeader("departmentcode");
            String timespan = req.getHeader("timespan");
            String access_token = req.getHeader("access_token");
            String refresh_token = req.getHeader("refresh_token");
            V3TokenInfo tokenInfo = this.iTokenService.getTokenInfo(departmentcode);
            ApiResult result = new ApiResult();
            if((null == departmentcode || departmentcode.isEmpty())||(null == timespan || timespan.isEmpty()) ||
                    (null == access_token || access_token.isEmpty()) || (null == refresh_token ||refresh_token.isEmpty()))
            {
                result.setSuccess(false);
                result.setInfo("Header参数不完整");
                result.setCode(StatusCodeEnum.ParameterError.toString());
            }else{
                TramDepartmentInfo departmentInfo = this.iDepartmentService.getDepartmentInfoByCode(departmentcode);
                if(null == departmentInfo){
                    result.setSuccess(false);
                    result.setInfo("机构不存在");
                    result.setCode(StatusCodeEnum.DataNotFound.toString());
                }else{
                    if(System.currentTimeMillis() - Long.valueOf(timespan) > 10){
                        result.setSuccess(false);
                        result.setInfo("请求超时");
                        result.setCode(StatusCodeEnum.URLExpireError.toString());
                    }else{
                        if(tokenInfo.getAccesstoken()!=access_token){
                            result.setSuccess(false);
                            result.setInfo("token校验失败");
                            result.setCode(StatusCodeEnum.TokenInvaild.toString());
                        }else{
                            result.setSuccess(true);
                            result.setCode(StatusCodeEnum.Success.toString());
                            result.setInfo("token校验成功");
                            isFilter = true;
                        }
                    }
                }
            }
            if(!result.isSuccess()){
                PrintWriter writer = null;
                OutputStreamWriter osw = null;
                try {
                    osw = new OutputStreamWriter(servletResponse.getOutputStream(),
                            "UTF-8");
                    writer = new PrintWriter(osw, true);
                    String jsonStr = JSON.toJSONString(result);
                    writer.write(jsonStr);
                    writer.flush();
                    writer.close();
                    osw.close();
                } catch (UnsupportedEncodingException e) {
                    logger.error("过滤器返回信息失败:" + e.getMessage(), e);
                } catch (IOException e) {
                    logger.error("过滤器返回信息失败:" + e.getMessage(), e);
                } finally {
                    if (null != writer) {
                        writer.close();
                    }
                    if (null != osw) {
                        osw.close();
                    }
                }
                return;
            }
            if(isFilter){
                logger.info("token filter过滤ok!");
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
