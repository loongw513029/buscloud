package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.domain.Tramlineinfo;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午6:26
 */
public interface ILineService {

    List<Tramlineinfo> GetLinesByDepartmentId(long departmentId);

    List<Long> GetLineIdsByUserId(long userId);
}
