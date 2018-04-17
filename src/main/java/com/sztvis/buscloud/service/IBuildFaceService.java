package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramBuildFaceComparsionRecord;
import com.sztvis.buscloud.model.domain.TramPersonInfo;
import com.sztvis.buscloud.model.domain.TramPersonPics;
import com.sztvis.buscloud.model.dto.BuildFaceViewModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IBuildFaceService {

    List<BuildFaceViewModel> getBuildFaceList(long userId,String keyword,long departmentId,int sex,int page,int rows);

    int getBuildFaceCountList(long userId,String keyword,long departmentId,int sex);

    TramPersonInfo getPersonInfo(long id);

    List<TramPersonPics> getPersoinPicList(long id);

    void saveOrUpdatePerson(BuildFaceViewModel viewModel, HttpServletRequest request) throws Exception;

    void removePerson(long personId);

    void saveComparisonRecord(TramBuildFaceComparsionRecord record);

}
