package com.topda.vo;

import java.util.Date;
import java.util.Observable;
import org.apache.commons.lang.StringUtils;

/**
 * 设备属性类
* @Title: Incubator.java 
* @Package com.topda.cooldevice 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wangkl
* @date 2015-8-19 下午1:47:59 
* @version V1.0
 */
public class DeviceTemprature extends Observable {
	/**
	 * 温度
	 */
	private String temperature;
	/**
	 * 温度
	 */
	private String outTemperature;

	/**
	 * 采集时间
	 */
	private Date collectTime;


	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public String getOutTemperature() {
		return outTemperature;
	}

	public void setOutTemperature(String outTemperature) {
		this.outTemperature = outTemperature;
	}


}
