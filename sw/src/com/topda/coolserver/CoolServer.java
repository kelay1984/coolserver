package com.topda.coolserver;

import java.util.Observable;
import java.util.Observer;

import com.topda.cooldevice.Incubator;

public class CoolServer implements Observer{

	public void init(Incubator inc){
		inc.addObserver(this);
	}

	public void update(Observable o, Object arg) {
		System.out.println("Data has changed to" + (String)arg);  
		
	}

}
