package com.sztvis.buscloud.api;

import com.github.pagehelper.PageHelper;
import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;
import com.sztvis.buscloud.core.TramException;
import com.sztvis.buscloud.core.helper.ListHelper;
import com.sztvis.buscloud.model.domain.TramDepartmentInfo;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.EasyDataGridResult;
import com.sztvis.buscloud.model.dto.MemberViewModel;
import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.dto.response.DepartmentViewModel;
import com.sztvis.buscloud.model.dto.response.RoleViewModel;
import com.sztvis.buscloud.model.entity.PageBean;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IBasicService;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/16 上午10:40
 */
@RestController
@RequestMapping("/api/v1/basic")
public class BasicApiController extends BaseApiController {
    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private IMemberService iMemberService;
    @Autowired
    private IBasicService iBasicService;
    /**
     * get department collections and show webpage
     * @param page pageno
     * @param rows pagesize
     * @param title departmentname
     * @return
     */
    @RequestMapping(value = "/departmentlist",method = RequestMethod.GET)
    public ApiResult GetDepartmentList(long userId,int page,int rows,String title) {
        PageHelper.startPage(page, rows);
        List<DepartmentViewModel> list = this.iDepartmentService.GetList(userId, title);
        int count = list.size();
        PageBean<DepartmentViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "", StatusCodeEnum.Success, pageData);
    }

    /**
     * get department single record
     * @param id departmentid
     * @return
     */
    @RequestMapping(value = "/departmentinfo",method = RequestMethod.GET)
    public ApiResult GetDepartmentInfo(long id){
        TramDepartmentInfo info = this.iDepartmentService.getDepartmentInfo(id);
        return ApiResult(true,"获得机构信息成功",StatusCodeEnum.Success,info);
    }

    /**
     * save department info
     * @param departmentInfo
     * @return
     */
    @RequestMapping(value = "/savedepartment",method = RequestMethod.POST)
    public ApiResult SaveDepartment(TramDepartmentInfo departmentInfo){
        try{
            this.iDepartmentService.addDepartmentInfo(departmentInfo);
            return ApiResult(true,"保存机构信息成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,"保存机构信息失败",StatusCodeEnum.Success,null);
        }
    }

    /**
     * remove department info
     * @param departmentIds
     * @return
     */
    @RequestMapping(value = "/removedepartment",method = RequestMethod.DELETE)
    public ApiResult RemoveDepartments(String departmentIds){
        try{
            this.iDepartmentService.removeDepartmentInfo(departmentIds);
            return ApiResult(true,"删除机构成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(true,"删除机构失败",StatusCodeEnum.Success,null);
        }
    }
    /**
     * get department dropdownlist,use in easyui combotree
     * @param userid userid
     * @return
     */
    @RequestMapping(value = "/departmentcombo",method = RequestMethod.GET)
    public ApiResult getDepartmentComboTreeData(long userid){
        List<ComboTreeModel> list = this.iDepartmentService.getComboTreeListData(userid);
        return  ApiResult(true,"获得下拉列表成功", StatusCodeEnum.Success, ListHelper.addFirstEleComboTree(list,"-选择机构-"));
    }

    /**
     * get user collections show web page
     * @param username username serach key
     * @param departments department ids
     * @param page pageno
     * @param rows pagesize
     * @param userId userid
     * @return
     */
    @RequestMapping(value = "/userlist",method = RequestMethod.GET)
    public ApiResult getUserList(String username,String departments,int page,int rows,long userId){
        PageHelper.startPage(page,rows);
        List<MemberViewModel> list = this.iMemberService.getMemberList(userId,departments,username);
        int count = list.size();
        PageBean<MemberViewModel> pageData = new PageBean<>(page, rows, count);
        pageData.setItems(list);
        return ApiResult(true, "", StatusCodeEnum.Success, pageData);
    }

    /**
     * save user
     * @param memberInfo
     * @return
     */
    @RequestMapping(value = "/saveuser",method = RequestMethod.POST)
    public ApiResult saveUser(TramMemberInfo memberInfo){
        try{
            this.iMemberService.saveAndUpdateMember(memberInfo);
            return  ApiResult(true,"用户新增成功",StatusCodeEnum.Success,null);
        }
        catch (TramException ex){
            return ApiResult(false,ex.getMessage(),StatusCodeEnum.Error,null);
        }
        catch (Exception ex){
            System.out.println(ex);
            return ApiResult(false,"用户新增失败",StatusCodeEnum.Error,null);
        }
    }

    @RequestMapping(value = "/removeuser",method = RequestMethod.DELETE)
    public ApiResult RemoveUser(String userIds){
        try{
            this.iMemberService.removeUser(userIds);
            return ApiResult(true,"删除用户成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(true,"删除用户失败",StatusCodeEnum.Success,null);
        }
    }
    /**
     * get role collections
     * @return
     */
    @RequestMapping(value = "/rolecombo",method = RequestMethod.GET)
    public ApiResult getRoleList(){
        List<ComboTreeModel> list = this.iBasicService.getRoleList();
        return  ApiResult(true,"获得角色下拉列表成功", StatusCodeEnum.Success, ListHelper.addFirstEleComboTree(list,"-选择角色-"));
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/menulist",method = RequestMethod.GET)
    public ApiResult getMenuList(){
        List<ComboTreeModel> list = this.iBasicService.getMenuList();
        return  ApiResult(true,"获得菜单下拉列表成功",StatusCodeEnum.Success,list);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/roleinfo",method = RequestMethod.GET)
    public ApiResult getRoleInfo(long id){
        RoleViewModel model = this.iBasicService.getRoleInfo(id);
        return  ApiResult(true,"获得权限信息成功",StatusCodeEnum.Success,model);
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/updaterole",method = RequestMethod.POST)
    public ApiResult updateRole(RoleViewModel model){
        try{
            this.iBasicService.saveAndUpdateRole(model);
            return ApiResult(true,"角色保存成功",StatusCodeEnum.Success,null);
        }catch (Exception ex){
            return ApiResult(false,"角色保存失败",StatusCodeEnum.Error,ex.getMessage());
        }
    }

}
