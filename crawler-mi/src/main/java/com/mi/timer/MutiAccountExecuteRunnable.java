package com.mi.timer;

import java.util.Map;

import com.mi.account.XiaomiAccountWrapper;
import com.mi.account.XiaomiRegisterAndLogin;
import com.mi.personalCenter.XiaomiPersonalCenterManager;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.RequestConstant;



public class MutiAccountExecuteRunnable implements Runnable{
    
	private XiaomiAccountWrapper xiaomiAccountWrapper;
	
	public MutiAccountExecuteRunnable(XiaomiAccountWrapper xiaomiAccountWrapper) {
		this.xiaomiAccountWrapper = xiaomiAccountWrapper;
	}
	
	@Override
	public void run() {
		
		XiaomiRegisterAndLogin xiaomiRegisterAndLogin = new XiaomiRegisterAndLogin();
		HttpClientRequestHandler requestHandler = xiaomiAccountWrapper.getRequestHandler();
		//访问登录首页
		Map<String,Object> visitXiaomiLoginHomePageMap = xiaomiRegisterAndLogin.visitXiaomiLoginHomePage(xiaomiAccountWrapper);
		//登录验证
		Map<String,Object>  serviceLoginAuth2Map = xiaomiRegisterAndLogin.serviceLoginAuth2(requestHandler, visitXiaomiLoginHomePageMap);
		//登录成功后跳转,更新Session
		XiaomiPersonalCenterManager xiaomiPersonalCenterManager = new XiaomiPersonalCenterManager();
		xiaomiPersonalCenterManager.gotoPersonalCenterHomePage(requestHandler);
		//抢购
		ShoppingRushTimer srt = new ShoppingRushTimer(RequestConstant.shoppingRushTimeString,requestHandler);
		srt.setShoppingRushTimer();
	}

	public XiaomiAccountWrapper getXiaomiAccountWrapper() {
		return xiaomiAccountWrapper;
	}

	public void setXiaomiAccountWrapper(XiaomiAccountWrapper xiaomiAccountWrapper) {
		this.xiaomiAccountWrapper = xiaomiAccountWrapper;
	}

}
