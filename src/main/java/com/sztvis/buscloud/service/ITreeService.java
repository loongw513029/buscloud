package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.response.TreeModel;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 下午2:15
 */
public interface ITreeService {

    List<TreeModel> GetTreeList(long userId);
}
