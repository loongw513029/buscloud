package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.MenuMapper;
import com.sztvis.buscloud.model.domain.Trammenuinfo;
import com.sztvis.buscloud.model.dto.response.MenuModel;
import com.sztvis.buscloud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 下午3:36
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuMapper menuMapper;

    public List<MenuModel> GetExtNavDataMenu(Long ParentId){
        List<MenuModel> list = new ArrayList<>();
        List<Trammenuinfo> lists = menuMapper.GetMenus(ParentId);
        for(int i =0;i<lists.size();i++){
            MenuModel menuModel=new MenuModel();
            menuModel.setCls(lists.get(i).getCls());
            menuModel.setGlyph(lists.get(i).getIcon());
            menuModel.setText(lists.get(i).getMenuname());
            menuModel.setUri(list.get(i).getUri());
            menuModel.setMenu(GetExtNavDataMenu(lists.get(i).getId()));
            list.add(menuModel);
        }
        return list;
    }
}
