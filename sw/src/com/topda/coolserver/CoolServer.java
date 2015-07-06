package com.topda.coolserver;

import java.util.Observable;
import java.util.Observer;

import com.topda.cooldevice.Incubator;

public class CoolServer implements Observer{

	public void init(Incubator inc){
		inc.addObserver(this);
	}
	@Override
	public void update(Observable observable, Object obj) {
		 System.out.println("Data has changed to" + (String)obj);    
	}

}
