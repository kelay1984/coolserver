package com.topda.cooldevice;

import java.util.Date;
import java.util.Observable;
import org.apache.commons.lang.StringUtils;

/**
 * 保温箱设备类
* @Title: Incubator.java 
* @Package com.topda.cooldevice 
* @Description: TODO(用一句话描述该文件做什么) 
* @author wangkl   
* @date 2015-7-5 下午8:51:21 
* @version V1.0
 */
public class Incubator extends Observable{

	private String ip;
	private String port;
	/**
	 * 设备号
	 */
	private String boxSn;
	/**
	 * 温度
	 */
	private String temperature;
	
	/**
	 * 温度采集时间
	 */
	private Date collectTime;
	/**
	 * 是否报警
	 */
	private boolean isAlarm;
	
	/**
	 * 是否采集箱外温度
	 */
	private boolean isCBOT;
	
	/**
	 * 超高警阀值
	 */
	private Integer HighAlarmThreshold;
	
	/**
	 * 超高限阀值
	 */
	private Integer HighThresholdLimit;
	/**
	 * 超低警阀值
	 */
	private Integer LowAlarmThreshold;
	
	/**
	 * 超低限阀值
	 */
	private Integer LowThresholdLimit;	
	/**
	 * 采集间隔
	 */
	private Integer CollectionInterval;
	/**
	 * 存储间隔
	 */
	private Integer StorageInterval;
	/**
	 * 上传间隔
	 */
	private Integer UploadInterval;
	/**
	 * 超警后存储间隔
	 */
	private Integer PostStorageInterval;
	/**
	 * 超警后上传间隔
	 */
	private Integer PostUploadInterval;
	
	public void init(){
		
		if(StringUtils.isEmpty(ip)||StringUtils.isEmpty(port)){
			return;
		}
		
	}
	
   public void change(String newState){
        System.out.println("主题状态为：" + newState);
        setChanged();    
        
        //只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。  
        notifyObservers();
    }
	
   
   
	public String getIp() {
	return ip;
}

public void setIp(String ip) {
	this.ip = ip;
}

public String getPort() {
	return port;
}

public void setPort(String port) {
	this.port = port;
}

public String getBoxSn() {
	return boxSn;
}

public void setBoxSn(String boxSn) {
	this.boxSn = boxSn;
}

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

	public boolean isAlarm() {
		return isAlarm;
	}
	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}
	public boolean isCBOT() {
		return isCBOT;
	}
	public void setCBOT(boolean isCBOT) {
		this.isCBOT = isCBOT;
	}
	public Integer getHighAlarmThreshold() {
		return HighAlarmThreshold;
	}
	public void setHighAlarmThreshold(Integer highAlarmThreshold) {
		HighAlarmThreshold = highAlarmThreshold;
	}
	public Integer getHighThresholdLimit() {
		return HighThresholdLimit;
	}
	public void setHighThresholdLimit(Integer highThresholdLimit) {
		HighThresholdLimit = highThresholdLimit;
	}
	public Integer getLowAlarmThreshold() {
		return LowAlarmThreshold;
	}
	public void setLowAlarmThreshold(Integer lowAlarmThreshold) {
		LowAlarmThreshold = lowAlarmThreshold;
	}
	public Integer getLowThresholdLimit() {
		return LowThresholdLimit;
	}
	public void setLowThresholdLimit(Integer lowThresholdLimit) {
		LowThresholdLimit = lowThresholdLimit;
	}
	public Integer getCollectionInterval() {
		return CollectionInterval;
	}
	public void setCollectionInterval(Integer collectionInterval) {
		CollectionInterval = collectionInterval;
	}
	public Integer getStorageInterval() {
		return StorageInterval;
	}
	public void setStorageInterval(Integer storageInterval) {
		StorageInterval = storageInterval;
	}
	public Integer getUploadInterval() {
		return UploadInterval;
	}
	public void setUploadInterval(Integer uploadInterval) {
		UploadInterval = uploadInterval;
	}
	public Integer getPostStorageInterval() {
		return PostStorageInterval;
	}
	public void setPostStorageInterval(Integer postStorageInterval) {
		PostStorageInterval = postStorageInterval;
	}
	public Integer getPostUploadInterval() {
		return PostUploadInterval;
	}
	public void setPostUploadInterval(Integer postUploadInterval) {
		PostUploadInterval = postUploadInterval;
	}
	
	
}
