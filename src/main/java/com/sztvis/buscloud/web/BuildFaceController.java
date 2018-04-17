package com.sztvis.buscloud.web;

import com.sztvis.buscloud.core.helper.StringHelper;
import com.sztvis.buscloud.model.domain.TramPersonInfo;
import com.sztvis.buscloud.model.domain.TramPersonPics;
import com.sztvis.buscloud.model.dto.BuildFaceViewModel;
import com.sztvis.buscloud.service.IBuildFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buildface")
public class BuildFaceController extends BaseController {

    @Autowired
    private IBuildFaceService iBuildFaceService;
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("/buildface/list");
        return modelAndView;
    }

    @RequestMapping("/from")
    public ModelAndView from(long id){
        ModelAndView modelAndView = new ModelAndView("/buildface/from");
        TramPersonInfo model = new TramPersonInfo();
        List<TramPersonPics> list = new ArrayList<>();
        if(id!=0)
        {
            model = this.iBuildFaceService.getPersonInfo(id);
            list = this.iBuildFaceService.getPersoinPicList(id);
        }
        String images ="";
        for (TramPersonPics pic:list) {
            images+=pic.getFilePath()+",";
        }
        if(images!=""){
            images = images.substring(0,images.length()-1);
        }
        modelAndView.getModel().put("obj",model);
        modelAndView.getModel().put("images",images);
        return modelAndView;
    }
}
