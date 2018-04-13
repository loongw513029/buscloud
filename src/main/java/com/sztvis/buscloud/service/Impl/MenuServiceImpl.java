package com.sztvis.buscloud.service.Impl;

import com.sztvis.buscloud.mapper.MenuMapper;
import com.sztvis.buscloud.model.domain.TramMenuInfo;
import com.sztvis.buscloud.model.dto.response.MenuModel;
import com.sztvis.buscloud.service.IBasicService;
import com.sztvis.buscloud.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 下午3:36
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private IBasicService iBasicService;

    public List<MenuModel> GetExtNavDataMenu(Long ParentId,long roleId){
        String roleIds = this.iBasicService.getRoleInfo(roleId).getRoleIds();
        List<String> mArr = Arrays.asList(roleIds.split(","));
        List<MenuModel> list = new ArrayList<>();
        List<TramMenuInfo> lists = menuMapper.GetMenus(ParentId);
        for(int i =0;i<lists.size();i++){
            MenuModel menuModel=new MenuModel();
            menuModel.setCls(lists.get(i).getCls());
            menuModel.setGlyph(lists.get(i).getIcon());
            menuModel.setText(lists.get(i).getMenuname());
            menuModel.setUri(lists.get(i).getUrl());
            menuModel.setMenu(GetExtNavDataMenu(lists.get(i).getId(),roleId));
            if(mArr.contains(lists.get(i).getId()+"")) {
                list.add(menuModel);
            }
        }
        return list;
    }
}
