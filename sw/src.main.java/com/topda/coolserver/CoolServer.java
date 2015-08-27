package com.topda.coolserver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.omg.CORBA.PRIVATE_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.topda.common.BWTypeEnum;
import com.topda.common.Utils;
import com.topda.cooldevice.MyProtocolHandler;
import com.topda.dao.MonitorImeiDao;
import com.topda.dao.MonitorInitDao;
import com.topda.dao.MonitorTemperatureDao;
import com.topda.event.DataEvent;
import com.topda.event.DataListener;
import com.topda.pojo.MonitorImei;
import com.topda.pojo.MonitorImeiExample;
import com.topda.pojo.MonitorInit;
import com.topda.pojo.MonitorInitExample;
import com.topda.pojo.MonitorTemperature;
import com.topda.pojo.MonitorTemperatureExample;
import com.topda.vo.DeviceTemprature;
import com.topda.vo.Incubator;

@Service
public class CoolServer {
	private static final Logger logger = LoggerFactory
			.getLogger(CoolServer.class);
	
	@Resource
	private MyProtocolHandler minaHandler;
	@Autowired
	private MonitorTemperatureDao monitorTemperatureDao;
	@Autowired
	private MonitorImeiDao monitorImeiDao;
	@Autowired
	private MonitorInitDao monitorInitDao;

	public MyProtocolHandler getMinaHandler() {
		return minaHandler;
	}

	public void setMinaHandler(MyProtocolHandler minaHandler) {
		this.minaHandler = minaHandler;
	}

	public String registerTemperDataListenr() {

		String rtnString="";
		try {
			DataListener lis = new DataListener() {

				public void handleEvent(DataEvent de) {
					Incubator inc = (Incubator) de.getSource();

					if (inc.getType() == BWTypeEnum.TIME.getValue()) {
/*						MonitorTemperature mt = new MonitorTemperature();
						mt.setBoxsn(inc.getBoxSn());

						monitorTemperatureDao.insert(mt);*/
					}
					if (inc.getType() == BWTypeEnum.SIM.getValue()) {

						MonitorImeiExample example = new MonitorImeiExample();
						example.createCriteria()
								.andBoxsnEqualTo(inc.getBoxSn())
								.andImeiEqualTo(inc.getSim());

						if (monitorImeiDao.countByExample(example) == 0) {

							MonitorImei mi = new MonitorImei();
							mi.setBoxsn(inc.getBoxSn());
							mi.setImei(inc.getSim());
							mi.setUpdatetime(new Date());

							monitorImeiDao.insert(mi);
						}
					}
					if (inc.getType() == BWTypeEnum.TEMP.getValue()) {
						List<DeviceTemprature> devlst = inc.getDevInfo();
						for (DeviceTemprature tem : devlst) {
							
							if(StringUtils.isNullOrEmpty(tem.getTemperature())){
								continue;
							}
							
							MonitorTemperatureExample example = new MonitorTemperatureExample();
							example.createCriteria()
									.andBoxsnEqualTo(inc.getBoxSn())
									.andDatehappenEqualTo(tem.getCollectTime());
							if (monitorTemperatureDao.countByExample(example) > 0)
								continue;

							MonitorTemperature montemp = new MonitorTemperature();
							montemp.setBoxsn(inc.getBoxSn());
							montemp.setDatehappen(tem.getCollectTime());
							montemp.setOuttemperature(Float.parseFloat(tem.getOutTemperature()));
							montemp.setTemperature(Float.parseFloat(tem
									.getTemperature()));
							montemp.setDoorstate(Integer.parseInt(inc
									.getDoorState()));
							montemp.setSwarning(Integer.parseInt(inc
									.getHighAlarmThreshold()));
							montemp.setSrestriction(Integer.parseInt(inc
									.getHighThresholdLimit()));
							montemp.setLwarning(Integer.parseInt(inc
									.getLowAlarmThreshold()));
							montemp.setLrestriction(Integer.parseInt(inc
									.getLowThresholdLimit()));
							montemp.setPower(Integer.parseInt(inc.getPower()));
							montemp.setServerdatetime(new Date());

							monitorTemperatureDao.insert(montemp);

						}
					}
					logger.debug("coolserver:" + inc.getBoxSn());

				}

			};
			//
			if (minaHandler.getDataListener() == null) {
				if(enc("")){
					minaHandler.setDataListener(lis);
					logger.debug("start succ");
					rtnString = "设置成功";
									
				}else {
					logger.debug("access denied");	
					rtnString = "请输入正确的授权码";
				}

			} else {
				logger.debug("already start succ");
				rtnString = "请不要重复设置";
			}
		} catch (Exception e) {
			logger.error("err:",e);
			rtnString = "服务器异常，请联系技术人员";
		}
		
		return rtnString;
	}
	
	private boolean enc(String accessString) throws UnknownHostException, ParseException {
		
		InetAddress ia;
		ia = InetAddress.getLocalHost();
		String mac = Utils.getLocalMac(ia);

		String tmp = mac.replace("-", "我").replace("+", "是")
				.replace("*", "谁");
		String code = Utils.GetMD5Code(tmp);

		if(StringUtils.isNullOrEmpty(accessString)){
			MonitorInitExample example = new MonitorInitExample();
			
			List<MonitorInit> lst=monitorInitDao.selectByExample(example);
			
			if(lst!=null&&lst.size()>0){
				MonitorInit init = lst.get(0);
				accessString = init.getCode();
				
			}else {
				return false;
			}

		}		
		return compare(accessString,code);
	}
		
	private boolean compare(String encodeString,String realString) throws ParseException {
		
		String real=Utils.convertMD5(encodeString);
		

		String[] arr = real.split("\\|");
		if(arr.length!=2)
			return false;	
		String access = arr[0];
		String time = arr[1];
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date endDate = df.parse(time);
		
		if(endDate.before(new Date())){
			logger.debug("access expire");
			return false;
		}
		
		if(!access.equals(realString)){
			return false;
		}
		
		return true;
		
	}

	public void removeTemperDataListenr() {

		try {
			minaHandler.setDataListener(null);

			logger.debug("remove succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean access(String code) {

		boolean isSucc = false;
		try {
			MonitorInitExample example = new MonitorInitExample();
			List<MonitorInit> lst = monitorInitDao.selectByExample(example);
			
			int nsucc = 0;
				
			if(lst!=null&&lst.size()>0){
				MonitorInit rec = lst.get(0);
				rec.setCode(code);
				nsucc=monitorInitDao.updateByPrimaryKey(rec);
			}else{
				MonitorInit record = new MonitorInit();
				record.setCode(code);
				nsucc = monitorInitDao.insert(record);
			}

			if(nsucc>0){
				logger.debug("access save succ");
				isSucc = true;
			}else {
				logger.debug("access save fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSucc;
	}

}
