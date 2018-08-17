package com.mi.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mi.account.XiaomiAccount;
import com.mi.account.XiaomiAccountWrapper;
import com.mi.account.XiaomiRegisterAndLogin;
import com.mi.address.XiaomiAddressManager;
import com.mi.initialize.XiaomiAccountInitialize;
import com.mi.personalCenter.XiaomiPersonalCenterManager;
import com.mi.product.XiaomiProductInfoManager;
import com.mi.shoppingCart.XiaomiShoppingCartManager;
import com.mi.shoppingRush.XiaomiShoppingRush;
import com.mi.timer.MutiAccountExecuteRunnable;
import com.mi.timer.ShoppingRushTimer;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.RequestConstant;


public class XiaomiLoginTest {
	public static final String PRODUCT_ID = "";
	public static void main(String[] args) {
		//testSubmitOrder();
		//shoppingRushTask();
		//xiaomiAccountParseTest();
		test();
		//testNewMethod();
	}
	
	public static void testNewMethod() {
		XiaomiRegisterAndLogin xiaomiRegisterAndLogin = new XiaomiRegisterAndLogin();
		XiaomiAccount xiaomiAccont = new XiaomiAccount();
		xiaomiAccont.setUserName("18311438517");
		xiaomiAccont.setLoginPassWd("lzz851128");
		HttpClientRequestHandler requestHandler = new HttpClientRequestHandler();
		XiaomiAccountWrapper xiaomiAccountWrapper = new XiaomiAccountWrapper(xiaomiAccont,requestHandler);
		Map<String,Object> visitXiaomiLoginHomePageMap = xiaomiRegisterAndLogin.visitXiaomiLoginHomePage(xiaomiAccountWrapper);
		Map<String,Object>  serviceLoginAuth2Map = xiaomiRegisterAndLogin.serviceLoginAuth2(requestHandler, visitXiaomiLoginHomePageMap);
		XiaomiPersonalCenterManager xiaomiPersonalCenterManager = new XiaomiPersonalCenterManager();
		xiaomiPersonalCenterManager.gotoPersonalCenterHomePage(requestHandler);
		//购物车相关方法的测试
		/*XiaomiShoppingCartManager xiaomiShoppingCartManager = new XiaomiShoppingCartManager();		
		xiaomiShoppingCartManager.gotoShoppingCartHomePage(requestHandler);
		xiaomiShoppingCartManager.getProductListInCart(requestHandler);*/
		//抢购相关方法的测试
		XiaomiShoppingRush xiaomiShoppingRush = new XiaomiShoppingRush();
		Map<String,Object> shoppingRushParamtersMap = xiaomiShoppingRush.fetchShoppingRushParamters(requestHandler, RequestConstant.PRODUCT_ID);
		xiaomiShoppingRush.fetchShoppingRushSkipUrl(requestHandler, shoppingRushParamtersMap);
		
	}
	
	public static void testSubmitOrder() {
		XiaomiRegisterAndLogin xiaomiRegisterAndLogin = new XiaomiRegisterAndLogin();
		XiaomiAccount xiaomiAccont = new XiaomiAccount();
		HttpClientRequestHandler requestHandler = new HttpClientRequestHandler();
		XiaomiAccountWrapper xiaomiAccountWrapper = new XiaomiAccountWrapper(xiaomiAccont,requestHandler);
		Map<String,Object> visitXiaomiLoginHomePageMap = xiaomiRegisterAndLogin.visitXiaomiLoginHomePage(xiaomiAccountWrapper);
		Map<String,Object>  serviceLoginAuth2Map = xiaomiRegisterAndLogin.serviceLoginAuth2(requestHandler, visitXiaomiLoginHomePageMap);
		//xiaomiRegisterAndLogin.loginSuccessRedirectLocation(requestHandler,serviceLoginAuth2Map);
		XiaomiPersonalCenterManager xiaomiPersonalCenterManager = new XiaomiPersonalCenterManager();
		xiaomiPersonalCenterManager.gotoPersonalCenterHomePage(requestHandler);
		
		
		XiaomiProductInfoManager xiaomiProductInfoManager = new XiaomiProductInfoManager();
		List<String> goodsIdList = xiaomiProductInfoManager.getGoodIdOfProduct(requestHandler, PRODUCT_ID);
		XiaomiShoppingCartManager xiaomiShoppingCartManager = new XiaomiShoppingCartManager();
		
		xiaomiShoppingCartManager.prepareForAddProductToCart(requestHandler,PRODUCT_ID, goodsIdList.get(0));
		xiaomiShoppingCartManager.addProductToCart(requestHandler, PRODUCT_ID, goodsIdList.get(0));
		xiaomiShoppingCartManager.checkoutPreCheck(requestHandler);
		Map<String,Object> map = xiaomiShoppingCartManager.checkoutOrder(requestHandler);
		XiaomiAddressManager xiaomiAddressManager = new XiaomiAddressManager();
		List<String> list = xiaomiAddressManager.fetchAddressIdList(requestHandler);
		map.put("addressId", list.get(0));
		xiaomiShoppingCartManager.submitOrder(requestHandler, map);
		//xiaomiShoppingCartManager.fetchItemIdList(requestHandler);
		//xiaomiShoppingCartManager.gotoShoppingCartHomePage(requestHandler);
	}
	
	public static List<XiaomiAccountWrapper> xiaomiAccountParseTest() {
		List<XiaomiAccountWrapper> xiaomiAccountWrapperList = new ArrayList<XiaomiAccountWrapper>();
		XiaomiAccountInitialize xiaomiAccountInitialize = new XiaomiAccountInitialize();
		List<XiaomiAccount>  xiaomiAccountList = xiaomiAccountInitialize.parseXiaomiAccountFromText(XiaomiAccountInitialize.XIAOMI_ACCOUNT_FILE_PATH);
		System.out.println("xiaomiAccountList.size() = "+xiaomiAccountList.size());
		for(int index=0;index<xiaomiAccountList.size();index++) {
			XiaomiAccount xiaomiAccount = xiaomiAccountList.get(index);
			System.out.println("-----------------index = "+index+"--------------------");
			System.out.println("xiaomiAccount = "+xiaomiAccount.toString());
			System.out.println("xiaomiAccount.userName = "+xiaomiAccount.getUserName());
			System.out.println("xiaomiAccount.pwd = "+xiaomiAccount.getLoginPassWd());
			HttpClientRequestHandler requestHandler = new HttpClientRequestHandler();
			XiaomiAccountWrapper xiaomiAccountWrapper = new XiaomiAccountWrapper(xiaomiAccount,requestHandler);
			xiaomiAccountWrapperList.add(xiaomiAccountWrapper);
		}
		return xiaomiAccountWrapperList;
		
	}
	
	public static void shoppingRushTask() {
		XiaomiRegisterAndLogin xiaomiRegisterAndLogin = new XiaomiRegisterAndLogin();
		XiaomiAccount xiaomiAccont = new XiaomiAccount();
		HttpClientRequestHandler requestHandler = new HttpClientRequestHandler();
		XiaomiAccountWrapper xiaomiAccountWrapper = new XiaomiAccountWrapper(xiaomiAccont,requestHandler);
		Map<String,Object> visitXiaomiLoginHomePageMap = xiaomiRegisterAndLogin.visitXiaomiLoginHomePage(xiaomiAccountWrapper);
		Map<String,Object>  serviceLoginAuth2Map = xiaomiRegisterAndLogin.serviceLoginAuth2(requestHandler, visitXiaomiLoginHomePageMap);
		//xiaomiRegisterAndLogin.loginSuccessRedirectLocation(requestHandler,serviceLoginAuth2Map);
		XiaomiPersonalCenterManager xiaomiPersonalCenterManager = new XiaomiPersonalCenterManager();
		xiaomiPersonalCenterManager.gotoPersonalCenterHomePage(requestHandler);
		
		ShoppingRushTimer srt = new ShoppingRushTimer(RequestConstant.shoppingRushTimeString,requestHandler);
		srt.setShoppingRushTimer();
	}
	
	public static void test() {
		List<XiaomiAccountWrapper> xiaomiAccountWrapperList = xiaomiAccountParseTest();
		for(XiaomiAccountWrapper xiaomiAccountWrapper:xiaomiAccountWrapperList) {
		    MutiAccountExecuteRunnable maer = new MutiAccountExecuteRunnable(xiaomiAccountWrapper);
		    Thread thread = new Thread(maer,"xiaomiShoppingRush");
		    thread.start();
		}
	}
	
	public static void xiaomiShoppingRushTask(XiaomiAccountWrapper xiaomiAccountWrapper) {
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
		/*ShoppingRushTimer srt = new ShoppingRushTimer(RequestConstant.shoppingRushTimeString,requestHandler);
		srt.setShoppingRushTimer();*/
		
	}

}
