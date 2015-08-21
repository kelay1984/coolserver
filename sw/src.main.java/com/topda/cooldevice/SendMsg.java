package com.topda.cooldevice;

import java.util.Date;
import java.util.Observable;
import org.apache.commons.lang.StringUtils;

import com.topda.common.Utils;

/**
 * 设备属性类
* @Title: Incubator.java 
* @Package com.topda.cooldevice 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wangkl
* @date 2015-8-19 下午1:47:59 
* @version V1.0
 */
public class SendMsg extends Observable {

	private byte[] result;
	private byte[] sendMsg;



	public byte[] getResult() {
		byte[] hd1 =  Utils.hex2byte("CC");
		byte[] hd2 =  Utils.hex2byte("DD");
		byte[] len =  Utils.hex2byte(String.valueOf(sendMsg.length));
		byte[] end1 =  Utils.hex2byte("99");
		byte[] end2 =  Utils.hex2byte("EF");
		
		byte[] tmpresult = new byte[5+sendMsg.length];
		
		System.arraycopy(hd1, 0, tmpresult, 0, 1);
		System.arraycopy(hd2, 0, tmpresult, 1, 1);
		System.arraycopy(len, 0, tmpresult, 2, 1);
		System.arraycopy(sendMsg, 0, tmpresult,3, sendMsg.length);
		System.arraycopy(end1, 0, tmpresult,3+sendMsg.length,1);
		System.arraycopy(end2, 0, tmpresult,3+sendMsg.length+1,1);
		
		return tmpresult;
	}

	public void setResult(byte[] result) {
		this.result = result;
	}

	public byte[] getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(byte[] sendMsg) {
		this.sendMsg = sendMsg;
	}

	
}
