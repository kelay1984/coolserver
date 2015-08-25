package com.topda.vo;

import java.util.List;
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
public class Incubator extends Observable {
	private int type;
	private String ip;
	private String port;
	/**
	 * 箱子编号
	 */
	private String boxSn;
	/**
	 * sim卡号
	 */
	private String sim;

	/**
	 * �Ƿ񱨾�
	 */
	private boolean isAlarm;

	/**
	 * �Ƿ�ɼ������¶�
	 */
	private String doorState;

	/**
	 * ���߾���ֵ
	 */
	private String HighAlarmThreshold;

	/**
	 * �����޷�ֵ
	 */
	private String HighThresholdLimit;
	/**
	 * ���;���ֵ
	 */
	private String LowAlarmThreshold;

	/**
	 * �����޷�ֵ
	 */
	private String LowThresholdLimit;
	/**
	 * �ɼ����
	 */
	private String CollectionInterval;
	/**
	 * �洢���
	 */
	private String StorageInterval;
	/**
	 * �ϴ����
	 */
	private String UploadInterval;
	/**
	 * ������洢���
	 */
	private String PostStorageInterval;
	/**
	 * �������ϴ����
	 */
	private String PostUploadInterval;
	
	private String power;
	
	private List<DeviceTemprature> devInfo;
	
	private SendMsg sendMsg;

	public void init() {

		if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(port)) {
			return;
		}

	}

	public void change(String newState) {
		System.out.println("����״̬Ϊ��" + newState);
		setChanged();

		// ֻ����setChange()�����ú�notifyObservers()�Ż�ȥ����update()������ʲô�����ɡ�
		notifyObservers();
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	
	
	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public boolean isAlarm() {
		return isAlarm;
	}

	public void setAlarm(boolean isAlarm) {
		this.isAlarm = isAlarm;
	}



	public String getDoorState() {
		return doorState;
	}

	public void setDoorState(String doorState) {
		this.doorState = doorState;
	}

	public String getHighAlarmThreshold() {
		return HighAlarmThreshold;
	}

	public void setHighAlarmThreshold(String highAlarmThreshold) {
		HighAlarmThreshold = highAlarmThreshold;
	}

	public String getHighThresholdLimit() {
		return HighThresholdLimit;
	}

	public void setHighThresholdLimit(String highThresholdLimit) {
		HighThresholdLimit = highThresholdLimit;
	}

	public String getLowAlarmThreshold() {
		return LowAlarmThreshold;
	}

	public void setLowAlarmThreshold(String lowAlarmThreshold) {
		LowAlarmThreshold = lowAlarmThreshold;
	}

	public String getLowThresholdLimit() {
		return LowThresholdLimit;
	}

	public void setLowThresholdLimit(String lowThresholdLimit) {
		LowThresholdLimit = lowThresholdLimit;
	}

	public String getCollectionInterval() {
		return CollectionInterval;
	}

	public void setCollectionInterval(String collectionInterval) {
		CollectionInterval = collectionInterval;
	}

	public String getStorageInterval() {
		return StorageInterval;
	}

	public void setStorageInterval(String storageInterval) {
		StorageInterval = storageInterval;
	}

	public String getUploadInterval() {
		return UploadInterval;
	}

	public void setUploadInterval(String uploadInterval) {
		UploadInterval = uploadInterval;
	}

	public String getPostStorageInterval() {
		return PostStorageInterval;
	}

	public void setPostStorageInterval(String postStorageInterval) {
		PostStorageInterval = postStorageInterval;
	}

	public String getPostUploadInterval() {
		return PostUploadInterval;
	}

	public void setPostUploadInterval(String postUploadInterval) {
		PostUploadInterval = postUploadInterval;
	}

	
	
	public List<DeviceTemprature> getDevInfo() {
		return devInfo;
	}

	public void setDevInfo(List<DeviceTemprature> devInfo) {
		this.devInfo = devInfo;
	}

	public SendMsg getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(SendMsg sendMsg) {
		this.sendMsg = sendMsg;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}


	
}
