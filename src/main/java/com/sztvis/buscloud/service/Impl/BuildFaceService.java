package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.core.helper.ImageHelper;
import com.sztvis.buscloud.mapper.BuildFaceMapper;
import com.sztvis.buscloud.model.baiduAI.Resp.FaceRegisterRespModel;
import com.sztvis.buscloud.model.domain.TramBuildFaceComparsionRecord;
import com.sztvis.buscloud.model.domain.TramPersonInfo;
import com.sztvis.buscloud.model.domain.TramPersonPics;
import com.sztvis.buscloud.model.dto.BuildFaceViewModel;
import com.sztvis.buscloud.service.IBaiduAIService;
import com.sztvis.buscloud.service.IBuildFaceService;
import com.sztvis.buscloud.service.IDepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Service
public class BuildFaceService implements IBuildFaceService {

    @Autowired
    private BuildFaceMapper buildFaceMapper;
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IBaiduAIService iBaiduAIService;

    @Override
    public List<BuildFaceViewModel> getBuildFaceList(long userId,String keyword, long departmentId, int sex, int page, int rows) {
        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return this.buildFaceMapper.getBuildFaceList(departments,keyword,departmentId,sex,page,rows);
    }

    @Override
    public int getBuildFaceCountList(long userId,String keyword, long departmentId, int sex) {
        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return this.buildFaceMapper.getBuildFaceCountList(departments,keyword,departmentId,sex);
    }

    @Override
    public TramPersonInfo getPersonInfo(long id) {
        return this.buildFaceMapper.getPersonInfo(id);
    }

    @Override
    public List<TramPersonPics> getPersoinPicList(long id) {
        return this.buildFaceMapper.getPersonPicList(id);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void saveOrUpdatePerson(BuildFaceViewModel viewModel, HttpServletRequest request) throws Exception {
        long personId = 0;
        if(viewModel.getId()==0){
            this.buildFaceMapper.insertPersonInfo(viewModel);
            personId= viewModel.getId();
        }
        else
        {
            personId = viewModel.getId();
            this.buildFaceMapper.updatePersonInfo(viewModel);
        }
        this.buildFaceMapper.removeBatchPersonPics(personId);
        if(!StringUtils.isEmpty(viewModel.getImages())) {
            List<String> list = Arrays.asList(viewModel.getImages().split(","));
            for (int i=0;i<list.size();i++){
                TramPersonPics personPics = new TramPersonPics();
                personPics.setFilePath(list.get(i));
                personPics.setPersonId(personId);
                this.buildFaceMapper.insertPersonPics(personPics);
            }
        }
        //添加到百度人脸库
        String base64 = ImageHelper.ImageToBase64(viewModel.getImages(),request);
        FaceRegisterRespModel registerRespModel = iBaiduAIService.addUser("user_"+personId,"group_0",viewModel.getName(),base64);
    }

    @Override
    public void removePerson(long personId) {
        try{
            this.buildFaceMapper.removeBatchPersonPics(personId);
            this.buildFaceMapper.removePersonInfo(personId);
        }
        catch(Exception ex) {}
    }

    @Override
    public void saveComparisonRecord(TramBuildFaceComparsionRecord record) {
        this.mongoTemplate.save(record);
    }
}
