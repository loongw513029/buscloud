package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramBuildFaceComparsionRecord;
import com.sztvis.buscloud.model.domain.TramPersonInfo;
import com.sztvis.buscloud.model.domain.TramPersonPics;
import com.sztvis.buscloud.model.dto.BuildFaceViewModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 大厦人脸识别接口
 */
public interface IBuildFaceService {
    /**
     * 获得人脸库
     * @param userId
     * @param keyword
     * @param departmentId
     * @param sex
     * @param page
     * @param rows
     * @return
     */
    List<BuildFaceViewModel> getBuildFaceList(long userId,String keyword,long departmentId,int sex,int page,int rows);

    /**
     * 获得人脸库数量
     * @param userId
     * @param keyword
     * @param departmentId
     * @param sex
     * @return
     */

    int getBuildFaceCountList(long userId,String keyword,long departmentId,int sex);

    /**
     * 人员实体
     * @param id
     * @return
     */
    TramPersonInfo getPersonInfo(long id);

    /**
     * 人脸图片
     * @param id
     * @return
     */
    List<TramPersonPics> getPersoinPicList(long id);

    /**
     * 保存人脸记录
     * @param viewModel
     * @param request
     * @throws Exception
     */
    void saveOrUpdatePerson(BuildFaceViewModel viewModel, HttpServletRequest request) throws Exception;

    void removePerson(long personId);

    /**
     * 保存比对记录
     * @param record
     */
    void saveComparisonRecord(TramBuildFaceComparsionRecord record);

}
