package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramLineInfo;
import com.sztvis.buscloud.model.dto.ComboTreeModel;
import com.sztvis.buscloud.model.dto.LineViewModel;

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
}
