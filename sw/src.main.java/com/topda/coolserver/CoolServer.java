package com.topda.coolserver;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topda.common.SpringContextUtil;
import com.topda.cooldevice.Incubator;
import com.topda.cooldevice.MyProtocolHandler;
import com.topda.dao.MonitorImeiDao;
import com.topda.dao.MonitorTemperatureDao;
import com.topda.event.DataEvent;
import com.topda.event.DataListener;
import com.topda.pojo.MonitorTemperature;
import com.topda.pojo.MonitorImei;

@Service
public class CoolServer {
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

					if(inc.getType()==BWTypeEnum.TIME.getValue()){
						MonitorTemperature mt = new MonitorTemperature();
						mt.setBoxsn(inc.getBoxSn());
				
						monitorTemperatureDao.insert(mt);
					}
					if(inc.getType()==BWTypeEnum.SIM.getValue()){
						MonitorImei mi = new MonitorImei();
						mi.setBoxsn(inc.getBoxSn());
						mi.setImei(inc.getSim());
						mi.setUpdatetime(new Date());
				
						monitorImeiDao.insert(mi);
					}
					System.out.println("coolserver:" + inc.getBoxSn());

				}

			};
			//
			if(minaHandler.getDataListener()==null){
				minaHandler.setDataListener(lis);
				System.out.println("start succ");
			}else{
				System.out.println("already start succ");
			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeTemperDataListenr() {

		try {

			//minaHandler = (MyProtocolHandler) SpringContextUtil.getBean("minaHandler");
			//
			minaHandler.setDataListener(null);

			System.out.println("remove succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
