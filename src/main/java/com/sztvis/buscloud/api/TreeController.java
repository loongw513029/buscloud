package com.sztvis.buscloud.api;

import com.sztvis.buscloud.model.dto.response.TreeModel;
import com.sztvis.buscloud.service.Impl.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/4 上午11:40
 */
@RestController
@RequestMapping("/api/v1/tree")
public class TreeController extends BaseApiController {

    @Autowired
    private TreeService treeService;

    @RequestMapping("/list")
    public List<TreeModel> GetTreeList(long userId){
        List<TreeModel> list= treeService.GetTreeList(userId);
        return list;
    }
}
