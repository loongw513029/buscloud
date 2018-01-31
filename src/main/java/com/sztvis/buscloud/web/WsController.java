package com.sztvis.buscloud.web;

import com.sztvis.buscloud.model.dto.push.PushModel;
import com.sztvis.buscloud.service.IPushService;
import com.sztvis.buscloud.service.Impl.PushService;
import com.sztvis.buscloud.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longweiqian
 * @company tvis
 * @date 2018/1/29 上午10:00
 */
@Controller
public class WsController {

    @Autowired
    private IPushService iPushService;

    @MessageMapping(Constant.ForeToServerPath)
    @SendTo(Constant.ProducerPath)
    public PushModel say(PushModel pushModel){
        List<String> users = new ArrayList<>();
        this.iPushService.sendToUsers(users,pushModel);
        return pushModel;
    }
}
