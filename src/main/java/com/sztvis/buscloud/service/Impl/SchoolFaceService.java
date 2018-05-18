package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.SchoolFaceMapper;
import com.sztvis.buscloud.model.domain.TramDriverSimilarRecord;
import com.sztvis.buscloud.model.dto.SchoolFaceViewModel;
import com.sztvis.buscloud.service.ISchoolFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolFaceService implements ISchoolFaceService {
    @Autowired
    private SchoolFaceMapper SchoolFaceMapper;
    @Override
    public void insertDriverSimilarRecord(TramDriverSimilarRecord record) {
        this.SchoolFaceMapper.insertDriverSimilarRecord(record);
    }

    @Override
    public List<SchoolFaceViewModel> getSchoolFaceList(String name, int page, int rows) {
        return this.SchoolFaceMapper.getSchoolFaceList(name,page,rows);
    }

    @Override
    public int getSchoolFaceCountList(String name) {
        return this.SchoolFaceMapper.getSchoolFaceCount(name);
    }

    @Override
    public void updateSchoolFaceImage(String image, String deviceCode, String updateTime) {
        this.SchoolFaceMapper.updateSchoolFaceImage(image,deviceCode,updateTime);
    }
}
