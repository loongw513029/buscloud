package com.sztvis.buscloud.service;

import com.sztvis.buscloud.model.dto.response.MenuModel;

import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 下午1:39
 */

public interface IMenuService {

    List<MenuModel> GetExtNavDataMenu(Long ParentId);
}
