package com.topda.coolserver;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topda.common.SpringContextUtil;
import com.topda.cooldevice.Incubator;
import com.topda.cooldevice.MyProtocolHandler;
import com.topda.dao.MonitorTemperatureDao;
import com.topda.event.DataEvent;
import com.topda.event.DataListener;
import com.topda.pojo.MonitorTemperature;


@Service
public class CoolServer {
	@Resource
	private MyProtocolHandler minaHandler;
	@Autowired
	private MonitorTemperatureDao monitorTemperatureDao;
	
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

					if(inc.getType()==4){
						MonitorTemperature mt = new MonitorTemperature();
						mt.setBoxsn(inc.getBoxSn());
				
						monitorTemperatureDao.insert(mt);
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

			minaHandler = (MyProtocolHandler) SpringContextUtil.getBean("minaHandler");
			//
			minaHandler.setDataListener(null);

			System.out.println("remove succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
