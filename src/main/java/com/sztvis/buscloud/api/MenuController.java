package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.dto.response.ApiResult;
import com.sztvis.buscloud.model.dto.response.MenuModel;
import com.sztvis.buscloud.model.entity.StatusCodeEnum;
import com.sztvis.buscloud.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2017/12/29 上午11:57
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController extends BaseApiController{

    @Autowired
    private IMenuService menuService;

    /**
     * 获得导航菜单列表
     * @return
     */
    @RequestMapping("navlist")
    public ApiResult GetExtNavData(){
        Long ParentId = 0L;
        List<MenuModel> data = menuService.GetExtNavDataMenu(ParentId);
        return ApiResult(true,"导航菜单获得成功", StatusCodeEnum.Success,data);
    }
}
