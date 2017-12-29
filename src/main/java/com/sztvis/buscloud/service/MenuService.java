package com.sztvis.buscloud.service;

import com.sztvis.buscloud.mapper.MenuMapper;
import com.sztvis.buscloud.model.domain.Trammenuinfo;
import com.sztvis.buscloud.model.dto.response.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 下午1:39
 */

public interface MenuService{

    List<MenuModel> GetExtNavDataMenu(Long ParentId);
}
