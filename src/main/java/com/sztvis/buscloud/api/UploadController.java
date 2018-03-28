package com.sztvis.buscloud.api;


import com.sun.xml.internal.ws.api.policy.PolicyResolver;
import com.sztvis.buscloud.Application;
import com.sztvis.buscloud.core.DateStyle;
import com.sztvis.buscloud.core.DateUtil;
import com.sztvis.buscloud.core.helper.ImageHelper;
import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IMemberService;
import com.sztvis.buscloud.util.Constant;
import com.sztvis.buscloud.util.HttpHelp;
import net.sf.jsqlparser.schema.Server;
import org.apache.tomcat.jni.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.spi.http.HttpContext;
import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController extends BaseApiController {

    @Autowired
    private IMemberService iMemberService;
    @Value("${upload.dir}")
    private String dir;
    @RequestMapping(value= "/UploadBaseImage" ,method= RequestMethod.POST )
    public ApiResult UploadPicBase64(UploadBase64Model model,HttpServletRequest request) throws IOException {
        String data=DateUtil.StringToString(DateUtil.getCurrentTime(),DateStyle.YYYY_MM_DD);
        String filenames= DateUtil.StringToString(DateUtil.getCurrentTime(), DateStyle.YYYY_MM_DD_HH_SS_SSS).replaceAll("-","").replaceAll(" ","").replaceAll(":","")+ 1 + ".png";
        String file="imgupload/user/";
        if (StringHelper.isEmpty(model.getBase64Str())|| model.getUserId()==0)
            return ApiResult(false,"上传内容缺失", StatusCodeEnum.ParameterError,null);
        else{
            ImageHelper.generateImage(model.getBase64Str(),file,filenames,request);
            String image=file+filenames;
            this.iMemberService.ModifyUserPhoto(model.getUserId(),image.toString());
            return ApiResult(true,"上传成功！", StatusCodeEnum.Success,image);
        }
    }

    }

    class UploadBase64Model {
        public String Base64Str;
        public long UserId;

        public String getBase64Str() {
            return Base64Str;
        }

        public void setBase64Str(String base64Str) {
            Base64Str = base64Str;
        }

        public long getUserId() {
            return UserId;
        }

        public void setUserId(long userId) {
            UserId = userId;
        }
     }

