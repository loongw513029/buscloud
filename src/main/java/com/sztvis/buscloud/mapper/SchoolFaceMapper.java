package com.sztvis.buscloud.mapper;

import com.sztvis.buscloud.mapper.provider.SchoolFaceProvider;
import com.sztvis.buscloud.model.domain.TramDriverSimilarRecord;
import com.sztvis.buscloud.model.dto.SchoolFaceViewModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolFaceMapper {

    @Insert("insert into TramDriverSimilarRecord(deviceId,deviceCode,fingerPrintPass,drunkDrive,faceCompairison,similar,driverPics,updateTime)values(#{deviceId},#{deviceCode},#{fingerPrintPass},#{drunkDrive},#{faceCompairison},#{similar},#{driverPics},#{updateTime})")
    void insertDriverSimilarRecord(TramDriverSimilarRecord record);

    @SelectProvider(type = SchoolFaceProvider.class,method = "getSchoolFaceListSQL")
    List<SchoolFaceViewModel> getSchoolFaceList(@Param("name") String name,@Param("page") int page,@Param("rows") int rows);

    @SelectProvider(type = SchoolFaceProvider.class,method = "getSchoolFaceListCountSQL")
    int getSchoolFaceCount(@Param("name") String name);

}
