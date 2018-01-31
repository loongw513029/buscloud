package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.LineMapper;
import com.sztvis.buscloud.model.domain.TramLineInfo;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.LineViewModel;
import com.sztvis.buscloud.service.IDepartmentService;
import com.sztvis.buscloud.service.ILineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:26
 */
@Service
public class LineService implements ILineService {

    @Autowired
    private LineMapper lineMapper;
    @Autowired
    private IDepartmentService iDepartmentService;

    @Override
    public List<TramLineInfo> GetLinesByDepartmentId(long departmentId) {
        return lineMapper.GetLinesByDepartmentId(departmentId);
    }

    @Override
    public List<Long> GetLineIdsByUserId(long userId) {
        return null;
    }

    @Override
    public List<LineViewModel> getList(long userId, String linename, long departmentId) {
        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return  this.lineMapper.getList(departments,linename,departmentId);
    }

    @Override
    public TramLineInfo getLineInfo(long Id) {
        return this.lineMapper.getLineInfo(Id);
    }

    @Override
    public List<ComboTreeModel> getLineTreeList(long userId) {
        List<Long> departments = this.iDepartmentService.GetDepartmentIdsByUserId(userId);
        return this.lineMapper.getLineTreeList(departments);
    }

    @Override
    public void saveAndUpdateLine(LineViewModel lineViewModel) {
        TramLineInfo lineInfo = new TramLineInfo();
        lineInfo.setId(lineViewModel.getId());
        lineInfo.setLinecode(lineViewModel.getLinecode());
        lineInfo.setLinename(lineViewModel.getLinename());
        lineInfo.setDeparentid(lineViewModel.getDepartmentid());
        lineInfo.setLineupmileage(Double.valueOf(lineViewModel.getLineupmileage()));
        lineInfo.setLinedownmileage(Double.valueOf(lineViewModel.getLinedownmileage()));
        lineInfo.setDownsitenum(Long.valueOf(lineViewModel.getDownsitenum()));
        lineInfo.setUpsitenum(Long.valueOf(lineViewModel.getUpsitenum()));
        if(lineInfo.getId()==0)
            this.lineMapper.saveLine(lineInfo);
        else
            this.lineMapper.updateLine(lineInfo);
    }

    @Override
    public void removeLines(String lineIds) {
        this.lineMapper.removeLine(lineIds);
    }
}
