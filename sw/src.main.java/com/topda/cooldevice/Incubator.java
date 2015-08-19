package com.topda.cooldevice;

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
public class Incubator extends Observable {

	private String ip;
	private String port;
	/**
	 * �豸��
	 */
	private String boxSn;
	/**
	 * �¶�
	 */
	private String temperature;

	/**
	 * �¶Ȳɼ�ʱ��
	 */
	private Date collectTime;
	/**
	 * �Ƿ񱨾�
	 */
	private boolean isAlarm;

	/**
	 * �Ƿ�ɼ������¶�
	 */
	private boolean isCBOT;

	/**
	 * ���߾���ֵ
	 */
	private Integer HighAlarmThreshold;

	/**
	 * �����޷�ֵ
	 */
	private Integer HighThresholdLimit;
	/**
	 * ���;���ֵ
	 */
	private Integer LowAlarmThreshold;

	/**
	 * �����޷�ֵ
	 */
	private Integer LowThresholdLimit;
	/**
	 * �ɼ����
	 */
	private Integer CollectionInterval;
	/**
	 * �洢���
	 */
	private Integer StorageInterval;
	/**
	 * �ϴ����
	 */
	private Integer UploadInterval;
	/**
	 * ������洢���
	 */
	private Integer PostStorageInterval;
	/**
	 * �������ϴ����
	 */
	private Integer PostUploadInterval;

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
