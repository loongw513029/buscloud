package com.sztvis.buscloud.netty.service;

import com.alibaba.fastjson.JSON;
import com.sztvis.buscloud.netty.SessionManager;
import com.sztvis.buscloud.netty.service.codec.MsgEncoder;
import com.sztvis.buscloud.netty.vo.PackageData;
import com.sztvis.buscloud.netty.vo.Session;
import com.sztvis.buscloud.netty.vo.req.LocationInfoUploadMsg;
import com.sztvis.buscloud.netty.vo.req.TerminalAuthenticationMsg;
import com.sztvis.buscloud.netty.vo.req.TerminalRegisterMsg;
import com.sztvis.buscloud.netty.vo.resp.ServerCommonRespMsgBody;
import com.sztvis.buscloud.netty.vo.resp.TerminalRegisterMsgRespBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminalMsgProcessService extends BaseMsgProcessService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private MsgEncoder msgEncoder;
    private SessionManager sessionManager;

    public TerminalMsgProcessService() {
        this.msgEncoder = new MsgEncoder();
        this.sessionManager = SessionManager.getInstance();
    }

    public void processRegisterMsg(TerminalRegisterMsg msg) throws Exception {
        log.debug("终端注册:{}", JSON.toJSONString(msg, true));

        final String sessionId = Session.buildId(msg.getChannel());
        Session session = sessionManager.findBySessionId(sessionId);
        if (session == null) {
            session = Session.buildSession(msg.getChannel(), msg.getMsgHeader().getTerminalPhone());
        }
        session.setAuthenticated(true);
        session.setTerminalPhone(msg.getMsgHeader().getTerminalPhone());
        sessionManager.put(session.getId(), session);

        TerminalRegisterMsgRespBody respMsgBody = new TerminalRegisterMsgRespBody();
        respMsgBody.setReplyCode(TerminalRegisterMsgRespBody.success);
        respMsgBody.setReplyFlowId(msg.getMsgHeader().getFlowId());
        // TODO 鉴权码暂时写死
        respMsgBody.setReplyToken("123");
        int flowId = super.getFlowId(msg.getChannel());
        byte[] bs = this.msgEncoder.encode4TerminalRegisterResp(msg, respMsgBody, flowId);

        super.send2Client(msg.getChannel(), bs);
    }

    public void processAuthMsg(TerminalAuthenticationMsg msg) throws Exception {
        // TODO 暂时每次鉴权都成功

        log.debug("终端鉴权:{}", JSON.toJSONString(msg, true));

        final String sessionId = Session.buildId(msg.getChannel());
        Session session = sessionManager.findBySessionId(sessionId);
        if (session == null) {
            session = Session.buildSession(msg.getChannel(), msg.getMsgHeader().getTerminalPhone());
        }
        session.setAuthenticated(true);
        session.setTerminalPhone(msg.getMsgHeader().getTerminalPhone());
        sessionManager.put(session.getId(), session);

        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody();
        respMsgBody.setReplyCode(ServerCommonRespMsgBody.success);
        respMsgBody.setReplyFlowId(msg.getMsgHeader().getFlowId());
        respMsgBody.setReplyId(msg.getMsgHeader().getMsgId());
        int flowId = super.getFlowId(msg.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(msg, respMsgBody, flowId);
        super.send2Client(msg.getChannel(), bs);
    }

    public void processTerminalHeartBeatMsg(PackageData req) throws Exception {
        log.debug("心跳信息:{}", JSON.toJSONString(req, true));
        final PackageData.MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId = super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId);
        super.send2Client(req.getChannel(), bs);
    }

    public void processTerminalLogoutMsg(PackageData req) throws Exception {
        log.info("终端注销:{}", JSON.toJSONString(req, true));
        final PackageData.MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId = super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId);
        super.send2Client(req.getChannel(), bs);
    }

    public void processLocationInfoUploadMsg(LocationInfoUploadMsg req) throws Exception {
        log.debug("位置 信息:{}", JSON.toJSONString(req, true));
        final PackageData.MsgHeader reqHeader = req.getMsgHeader();
        ServerCommonRespMsgBody respMsgBody = new ServerCommonRespMsgBody(reqHeader.getFlowId(), reqHeader.getMsgId(),
                ServerCommonRespMsgBody.success);
        int flowId = super.getFlowId(req.getChannel());
        byte[] bs = this.msgEncoder.encode4ServerCommonRespMsg(req, respMsgBody, flowId);
        super.send2Client(req.getChannel(), bs);
    }
}
