package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.TramCanInfo;
import com.sztvis.buscloud.model.domain.Tramdepartmentinfo;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午1:46
 */
public interface IDepartmentService {
    /**
     * 根据用户id获得该用户机构的顶级机构
     * @param userId
     * @return
     */
    Tramdepartmentinfo GetParentPartmentIdsByUserId(long userId);

    /**
     * 获得机构列表
     * @param departmentId
     * @return
     */
    List<Tramdepartmentinfo> GetParentsByParentId(long departmentId);

    /**
     * 获得机构Id
     * @param userId 用户id
     * @return
     */
    List<Long> GetDepartmentIdsByUserId(long userId);


}
