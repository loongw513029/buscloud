package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sztvis.buscloud.model.dto.AlarmViewModel;
import com.sztvis.buscloud.model.dto.MemberViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/23 下午3:31
 */
@RestController
@RequestMapping("/api/v1/alarm")
public class AlarmApiController extends BaseApiController{
    @Autowired
    private IAlarmService iAlarmService;
    /**
     * get webpage alarm table list
     * @param userId userid
     * @param departmentId department id
     * @param lineId line id
     * @param type1 parent alarm type id
     * @param type2 alarm type id
     * @param date1 search start time
     * @param date2 search end time
     * @param keywords search keys contains[alarmname,devicecode]
     * @return
     */
    @RequestMapping("/gettablelist")
    public ApiResult getAlarmList(long userId,long departmentId,long lineId,long type1,long type2,String date1,String date2,String keywords,int page,int rows){
        PageHelper.startPage(page,rows);
        List<AlarmViewModel> list = this.iAlarmService.getAlarmTableList(userId,departmentId,lineId,type1,type2,date1,date2,keywords);
        int count = list.size();
        PageBean<AlarmViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "", StatusCodeEnum.Success, pageData);
    }

    /**
     * get map page's alarm list
     * @param devices deviceid collections,split(',')
     * @param starttime look map start time
     * @return
     */
    @RequestMapping(value = "/getmapalarmlist",method = RequestMethod.GET)
    public ApiResult getMapAlarmList(String devices,String starttime,int page,int rows){
        PageHelper.startPage(page,rows);
        List<AlarmViewModel> list = this.iAlarmService.getMapAlarmTableList(devices,starttime);
        int count = list.size();
        PageBean<AlarmViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "", StatusCodeEnum.Success, pageData);
    }
}
