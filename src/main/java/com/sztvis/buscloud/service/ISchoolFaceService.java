package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramDriverSimilarRecord;
import com.sztvis.buscloud.model.dto.SchoolFaceViewModel;

import java.util.List;

/**
 * 校车人脸识别
 */
public interface ISchoolFaceService {
    /**
     * 增加识别记录
     * @param record
     */
    void insertDriverSimilarRecord(TramDriverSimilarRecord record);

    List<SchoolFaceViewModel> getSchoolFaceList(String name,int page,int rows);

    int getSchoolFaceCountList(String name);
}
