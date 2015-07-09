package com.topda.coolserver;

import org.springframework.stereotype.Service;

import com.topda.common.SpringContextUtil;
import com.topda.cooldevice.Incubator;
import com.topda.cooldevice.MyProtocolHandler;
import com.topda.event.DataEvent;
import com.topda.event.DataListener;

@Service
public class CoolServer {
	private MyProtocolHandler minaHandler;

	public void registerTemperDataListenr() {

		try {
			DataListener lis = new DataListener() {

				public void handleEvent(DataEvent de) {
					Incubator inc = (Incubator) de.getSource();

					System.out.println("coolserver:" + inc.getBoxSn());

				}

			};
			System.out.println("begin get minaHandler:");
			minaHandler = (MyProtocolHandler) SpringContextUtil.getBean("minaHandler");
			System.out.println("minaHandler:" + minaHandler);
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
			DataListener lis = new DataListener() {

				public void handleEvent(DataEvent de) {
					Incubator inc = (Incubator) de.getSource();

					System.out.println("coolserver:" + inc.getBoxSn());

				}

			};
			System.out.println("begin get minaHandler:");
			minaHandler = (MyProtocolHandler) SpringContextUtil.getBean("minaHandler");
			System.out.println("minaHandler:" + minaHandler);
			//
			minaHandler.setDataListener(null);

			System.out.println("remove succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
