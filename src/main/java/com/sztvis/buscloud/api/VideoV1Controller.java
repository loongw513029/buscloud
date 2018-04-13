package com.sztvis.buscloud.api;

import com.alibaba.fastjson.JSON;
import com.sztvis.buscloud.core.helper.HttpHelp;
import com.sztvis.buscloud.model.BaseModel;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.dto.api.GetFileListModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/video")
public class VideoV1Controller extends BaseApiController {
    @Value("${forward.getfilelisturl}")
    private String getFileListUrl;
    @Autowired
    private IDeviceService iDeviceService;
    /**
     * 获得历史回放文件
     * @param deviceId
     * @param time
     * @param channel
     * @param category
     * @return
     */
    @RequestMapping("/getvideohistoryfilelist")
    public ApiResult getVideoHistoryFileList(long deviceId, String time, int channel, String category){
        TramDeviceInfo deviceInfo = this.iDeviceService.getDeviceInfoById(deviceId);
        if(deviceInfo == null){
            return  ApiResult(false,"设备不存在", StatusCodeEnum.DataNotFound,null);
        }
        GetFileListModel fileListModel = new GetFileListModel();
        fileListModel.setCategory(category);
        fileListModel.setChannel(channel);
        fileListModel.setTime(time);
        BaseModel baseModel = new BaseModel();
        baseModel.setMsgInfo(fileListModel);
        baseModel.setType(3);
        baseModel.setSource(UUID.randomUUID().toString().replaceAll("-",""));
        baseModel.setTarget(deviceInfo.getDevicecode());
        String jsonData = JSON.toJSONString(baseModel);
        String result = HttpHelp.sendHttpPostJson(getFileListUrl,jsonData);
        BaseModel baseModel1 = JSON.parseObject(result,BaseModel.class);
        return ApiResult(true,"获得历史回放数据成功",StatusCodeEnum.Success,baseModel1);
    }
}
