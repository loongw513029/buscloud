package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.dto.BuildFaceViewModel;
import com.sztvis.buscloud.model.dto.SchoolFaceViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.ISchoolFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facesmailr")
public class FaceSmailrController extends BaseApiController {
    @Autowired
    private ISchoolFaceService iSchoolFaceService;

    @RequestMapping("/facelist")
    public ApiResult getFaceSmailrList(String name,int page,int rows){
        List<SchoolFaceViewModel> list = this.iSchoolFaceService.getSchoolFaceList(name,page,rows);
        int count = this.iSchoolFaceService.getSchoolFaceCountList(name);
        PageBean<SchoolFaceViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "人脸记录获取成功", StatusCodeEnum.Success, pageData);
    }
}
