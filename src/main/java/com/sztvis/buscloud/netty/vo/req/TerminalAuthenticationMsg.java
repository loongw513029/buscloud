package com.sztvis.buscloud.netty.vo.req;

import com.sztvis.buscloud.netty.vo.PackageData;
import com.sztvis.buscloud.util.TMPSConsts;

import java.util.Arrays;
/**
 * 终端鉴权消息
 * 
 * @author hylexus
 *
 */
public class TerminalAuthenticationMsg extends PackageData {
	private String authCode;

	public TerminalAuthenticationMsg() {
	}

	public TerminalAuthenticationMsg(PackageData packageData) {
		this();
		this.channel = packageData.getChannel();
		this.checkSum = packageData.getCheckSum();
		this.msgBodyBytes = packageData.getMsgBodyBytes();
		this.msgHeader = packageData.getMsgHeader();
		this.authCode = new String(packageData.getMsgBodyBytes(), TMPSConsts.string_charset);
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	@Override
	public String toString() {
		return "TerminalAuthenticationMsg [authCode=" + authCode + ", msgHeader=" + msgHeader + ", msgBodyBytes="
				+ Arrays.toString(msgBodyBytes) + ", checkSum=" + checkSum + ", channel=" + channel + "]";
	}

}
