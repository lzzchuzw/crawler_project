package com.mi.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import com.utils.request.HttpClientRequestHandler;



public class ShoppingRushTimer {
	
	private String shoppingRushTimeString;
	private HttpClientRequestHandler requestHandler;
	
	
	public ShoppingRushTimer(String shoppingRushTimeString,HttpClientRequestHandler requestHandler) {
		this.shoppingRushTimeString = shoppingRushTimeString;
		this.requestHandler = requestHandler;
	}
	
	
	public void setShoppingRushTimer() {
		Date startTime = null;
		try {
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").parse(shoppingRushTimeString);
		} catch (ParseException e) {
			
			e.printStackTrace();
		} 
		Timer timer = new Timer();
		ShoppingRushTimerTask srtt = new ShoppingRushTimerTask(requestHandler);
		//long delayTimes = startTime.getTime()+990-System.currentTimeMillis();
		long nowTimes = System.currentTimeMillis();
		long delayTimes = startTime.getTime()-nowTimes;
		System.out.println("startTime = "+startTime+"---currentTime = "+nowTimes+"----delayTimes = "+delayTimes);
		//timer.scheduleAtFixedRate(srtt, delayTimes, 1000);
		timer.schedule(srtt, delayTimes);
	}
	
	public String getShoppingRushTimeString() {
		return shoppingRushTimeString;
	}
	public void setShoppingRushTimeString(String shoppingRushTimeString) {
		this.shoppingRushTimeString = shoppingRushTimeString;
	}
	public HttpClientRequestHandler getRequestHandler() {
		return requestHandler;
	}
	public void setRequestHandler(HttpClientRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	
	

}
