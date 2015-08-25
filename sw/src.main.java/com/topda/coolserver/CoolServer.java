package com.topda.coolserver;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topda.common.BWTypeEnum;
import com.topda.cooldevice.MyProtocolHandler;
import com.topda.dao.MonitorImeiDao;
import com.topda.dao.MonitorTemperatureDao;
import com.topda.event.DataEvent;
import com.topda.event.DataListener;
import com.topda.pojo.MonitorImei;
import com.topda.pojo.MonitorImeiExample;
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

	public MyProtocolHandler getMinaHandler() {
		return minaHandler;
	}

	public void setMinaHandler(MyProtocolHandler minaHandler) {
		this.minaHandler = minaHandler;
	}

	public void registerTemperDataListenr() {

		try {
			DataListener lis = new DataListener() {

				public void handleEvent(DataEvent de) {
					Incubator inc = (Incubator) de.getSource();

					if (inc.getType() == BWTypeEnum.TIME.getValue()) {
						MonitorTemperature mt = new MonitorTemperature();
						mt.setBoxsn(inc.getBoxSn());

						monitorTemperatureDao.insert(mt);
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
				minaHandler.setDataListener(lis);
				logger.debug("start succ");
			} else {
				logger.debug("already start succ");
			}

		} catch (Exception e) {
			logger.error("err:",e);
		}
	}

	public void removeTemperDataListenr() {

		try {
			minaHandler.setDataListener(null);

			logger.debug("remove succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
