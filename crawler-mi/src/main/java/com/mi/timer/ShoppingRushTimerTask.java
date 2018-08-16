package com.mi.timer;

import java.util.Date;
import java.util.TimerTask;

import com.utils.request.HttpClientRequestHandler;



public class ShoppingRushTimerTask extends TimerTask{
    
	private HttpClientRequestHandler requestHandler;
	
	

	public ShoppingRushTimerTask(HttpClientRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	
	@Override
	public void run() {
		long times = System.currentTimeMillis();
		System.out.println("执行抢购时间:"+times+"---date: "+new Date(times));
		ShoppingRushRunnable srr = new ShoppingRushRunnable(requestHandler);
		Thread shoppingRushThread = new Thread(srr,"shoppingRush");
		shoppingRushThread.run();
	}
	
	
	public HttpClientRequestHandler getRequestHandler() {
		return requestHandler;
	}

	public void setRequestHandler(HttpClientRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	

}
