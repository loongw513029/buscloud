package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramChannelInfo;
import com.sztvis.buscloud.model.domain.TramDeviceInfo;
import com.sztvis.buscloud.model.domain.TramLineInfo;
import com.sztvis.buscloud.model.dto.AppNumViewModel;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.LineViewModel;
import com.sztvis.buscloud.model.dto.SelectViewModel;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:26
 */
public interface ILineService {

    List<TramLineInfo> GetLinesByDepartmentId(long departmentId);

    List<Long> GetLineIdsByUserId(long userId);

    List<LineViewModel> getList(long userId, String linename, long departmentId);

    TramLineInfo getLineInfo(long Id);

    List<ComboTreeModel> getLineTreeList(long userId);

    List<TramDeviceInfo>  getDevices(long lineId,long userId);

    List<TramChannelInfo> GetChannlsByDeviceId(long deviceId);

    /**
     * 保存线路
     * @param lineViewModel
     */

    void saveAndUpdateLine(LineViewModel lineViewModel);

    /**
     * 删除线路
     * @param lineIds
     */
    void removeLines(String lineIds);

    AppNumViewModel GetAppNumByLineId(Long lineId);

    List<SelectViewModel> GetDropDownLine(Long userId);
}
