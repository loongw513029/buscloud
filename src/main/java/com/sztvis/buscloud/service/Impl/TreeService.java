package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.model.domain.Tramdepartmentinfo;
import com.sztvis.buscloud.model.domain.Tramdeviceinfo;
import com.sztvis.buscloud.model.domain.Tramlineinfo;
import com.sztvis.buscloud.model.dto.response.TreeAttributeModel;
import com.sztvis.buscloud.model.dto.response.TreeModel;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.IDeviceService;
import com.sztvis.buscloud.service.ILineService;
import com.sztvis.buscloud.service.ITreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午2:16
 */
@Service
public class TreeService implements ITreeService {

    @Autowired
    private IDepartmentService iDepartmentService;
    @Autowired
    private ILineService iLineService;
    @Autowired
    private IDeviceService iDeviceService;

    @Override
    public TreeModel GetTreeList(long userId) {
        TreeModel treeModel=new TreeModel();
        Tramdepartmentinfo departmentinfo = iDepartmentService.GetParentPartmentIdsByUserId(userId);
        treeModel.setText(departmentinfo.getDepartmentname());
        treeModel.setId(departmentinfo.getId());
        treeModel.setState("open");
        treeModel.setIconCls("tree-department");
        TreeAttributeModel m2=new TreeAttributeModel();
        m2.setState(false);
        treeModel.setAttributes(m2);
        treeModel.setChildren(this.GetChindDepartmentList(departmentinfo.getId()));
        return  treeModel;
    }
    private List<TreeModel> GetChindDepartmentList(long departmentId){
        List<TreeModel> list=new ArrayList<>();
        List<Tramdepartmentinfo> dlist = iDepartmentService.GetParentsByParentId(departmentId);
        for(int i=0;i<dlist.size();i++){
            TreeModel model =new TreeModel();
            model.setState("open");
            model.setId(dlist.get(i).getId());
            model.setText(dlist.get(i).getDepartmentname());
            model.setChecked(false);
            model.setIconCls("tree-child-department");
            model.setChildren(this.GetChildLineList(dlist.get(i).getId()));
            list.add(model);
        }
        return list;
    }

    private List<TreeModel> GetChildLineList(long departmentId){
        List<TreeModel> list=new ArrayList<>();
        List<Tramlineinfo> dlist = iLineService.GetLinesByDepartmentId(departmentId);
        for(int i=0;i<dlist.size();i++){
            TreeModel model =new TreeModel();
            model.setState("open");
            model.setId(dlist.get(i).getId());
            model.setText(dlist.get(i).getLinename());
            model.setChecked(false);
            model.setIconCls("tree-line");
            model.setChildren(this.GetChindDevicesByLineId(dlist.get(i).getId()));
            list.add(model);
        }
        return list;
    }

    private List<TreeModel> GetChindDevicesByLineId(long lineId){
        List<TreeModel> list=new ArrayList<>();
        List<Tramdeviceinfo> dlist = iDeviceService.GetDevicesByLineId(lineId);
        for(int i=0;i<dlist.size();i++){
            TreeModel model =new TreeModel();
            model.setState("open");
            model.setId(dlist.get(i).getId());
            model.setText(dlist.get(i).getDevicecode());
            model.setChecked(false);
            model.setIconCls("tree-line");
            list.add(model);
        }
        return list;
    }
}
