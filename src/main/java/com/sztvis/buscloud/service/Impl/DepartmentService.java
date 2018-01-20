package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.DepartmentMapper;
import com.sztvis.buscloud.model.domain.TramDepartmentInfo;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.response.DepartmentViewModel;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午1:47
 */
@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private IMemberService iMemberService;

    @Override
    public TramDepartmentInfo GetParentPartmentIdsByUserId(long userId) {
        TramDepartmentInfo departmentInfo = departmentMapper.GetDepartmentIdsByUserId(userId);
        if(departmentInfo.getParentid()!=0){
            departmentInfo = departmentMapper.GetDepartmentInfo(departmentInfo.getParentid());
        }
        return departmentInfo;
    }

    @Override
    public List<TramDepartmentInfo> GetParentsByParentId(long departmentId) {
        return departmentMapper.GetDepartmentsByParentId(departmentId);
    }

    @Override
    public List<Long> GetDepartmentIdsByUserId(long userId) {
        long departmentId = departmentMapper.GetDepartmentIdsByUserId(userId).getId();
        return departmentMapper.GetPartmentIdsByDepartmentId(departmentId);
    }

    @Override
    public List<DepartmentViewModel> GetList(long userId, String text) {
        List<Long> departments = this.GetDepartmentIdsByUserId(userId);
        List<DepartmentViewModel> list = this.departmentMapper.GetList(text,departments);
        return list;
    }

    @Override
    public TramDepartmentInfo getDepartmentInfo(long id) {
        return this.departmentMapper.GetDepartmentInfo(id);
    }

    @Override
    public List<ComboTreeModel> getComboTreeListData(long userId) {
        TramMemberInfo memberInfo = this.iMemberService.getMemberInfo(userId);
        long owershipId = memberInfo.getOwnershipid();
        TramDepartmentInfo departmentInfo = this.getDepartmentInfo(owershipId);
        if(departmentInfo.getParentid()!=0){
            departmentInfo = this.getDepartmentInfo(departmentInfo.getParentid());
        }
        return this.getComboTreeDataOfRecursion(departmentInfo.getParentid());
    }

    @Override
    public void addDepartmentInfo(TramDepartmentInfo departmentInfo) {
        if(departmentInfo.getId()==0)
            this.departmentMapper.insertDepartment(departmentInfo);
        else
            this.departmentMapper.updateDepartment(departmentInfo);
    }

    @Override
    public void removeDepartmentInfo(String departmentIds) {
        this.departmentMapper.removeDepartment(departmentIds);
    }

    private List<ComboTreeModel> getComboTreeDataOfRecursion(long parentId){
        List<TramDepartmentInfo> list = this.departmentMapper.GetDepartmentsByParentId(parentId);
        List<ComboTreeModel> list2 = new ArrayList<>();
        for(TramDepartmentInfo dep:list){
            ComboTreeModel comboTreeModel =new ComboTreeModel();
            comboTreeModel.setId(new Long(dep.getId()).intValue());
            comboTreeModel.setText(dep.getDepartmentname());
            comboTreeModel.setChildren(this.getComboTreeDataOfRecursion(dep.getId()));
            list2.add(comboTreeModel);
        }
        return list2;
    }

}
