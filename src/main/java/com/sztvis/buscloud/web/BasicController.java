package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.domain.TramDepartmentInfo;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/16 上午10:20
 */
@Controller
@RequestMapping("/basic")
public class BasicController extends BaseController{
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private IMemberService iMemberService;
    /**
     *
     * @return
     */
    @RequestMapping("/department")
    public ModelAndView department(){
        ModelAndView model = new ModelAndView("/basic/organzation");
        return model;
    }
    @RequestMapping("/departmentfrom")
    public ModelAndView departmentfrom(int id){
        ModelAndView model = new ModelAndView("/basic/organzaitonfrom");
        TramDepartmentInfo departmentInfo =new TramDepartmentInfo();
        if(id!=0) {
            departmentInfo = this.iDepartmentService.getDepartmentInfo(id);
        }
        model.getModel().put("obj", departmentInfo);
        return model;
    }

    @RequestMapping("/member")
    public ModelAndView member(){
        ModelAndView model = new ModelAndView("/basic/user");
        return model;
    }

    @RequestMapping("/memberfrom")
    public ModelAndView memberfrom(int id){
        ModelAndView model = new ModelAndView("/basic/userfrom");
        TramMemberInfo memberInfo = new TramMemberInfo();
        if(id != 0){
            memberInfo = this.iMemberService.getMemberInfo(id);
        }
        model.getModel().put("obj",memberInfo);
        return model;
    }

    @RequestMapping("/role")
    public ModelAndView role(){
        ModelAndView modelAndView = new ModelAndView("/basic/role");
        return modelAndView;
    }
}
