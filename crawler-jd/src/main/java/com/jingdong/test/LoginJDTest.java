package com.jingdong.test;

import java.util.Map;

import com.jingdong.account.JDAccount;
import com.jingdong.account.JDAccountWrapper;
import com.jingdong.account.JDRegisterAndLogin;

import com.utils.map.MapUtils;
import com.utils.request.HttpClientRequestHandler;

/**
 * 
 * @Description 测试方法
 * @author lzz
 * @time 2018年2月8日 下午4:33:10
 */
public class LoginJDTest {
	public static void main(String[] args) {
		//LoginJDWithOutAuthCode();
		//JDAccountInit();
		JDMethodTest();
	}
	/**
	 * 测试京东不输入验证码登录及登录后相关操作
	 */
	public static void LoginJDWithOutAuthCode() {
		JDRegisterAndLogin jdRegisterAndLogin = new JDRegisterAndLogin();
		JDAccount jdAccount = new JDAccount();
		jdAccount.setUserName("jd_4c2b3d8b3d6c1");
		jdAccount.setLoginPassWd("ananky684");
		HttpClientRequestHandler requestHandler = new HttpClientRequestHandler();
		JDAccountWrapper jdAccountWraper = new JDAccountWrapper();
		jdAccountWraper.setJdAccount(jdAccount);
		jdAccountWraper.setRequestHandler(requestHandler);
		jdRegisterAndLogin.loginJDWithoutAuthCode(jdAccountWraper);
		//添加新收货地址并设为默认
		/*JDAddressManager jdam = new JDAddressManager();
		jdam.addNewAddressAndSetDefault(requestHandler);*/
		//抢购
		/*JDShoppingRush jdsr = new JDShoppingRush(FetchUrlType.AFTER_SHOPPING_RUSH, "1552946", "now");
		jdsr.fetchUrlAndrushToPurchaseTask(requestHandler, false, false);*/
	}
	
	public static void JDAccountInit() {
		/*JDAccountInitialize jdai = new JDAccountInitialize();
		jdai.parseJDAccountInfoFromText(JDAccountInitialize.jdAccountFilePath);*/
	}
	
	public static void JDMethodTest() {
		JDAccount jdAccount = new JDAccount();
		jdAccount.setUserName("jd_4c2b3d8b3d6c1");
		jdAccount.setLoginPassWd("ananky684");
		HttpClientRequestHandler requestHandler = new HttpClientRequestHandler();
		JDAccountWrapper jdAccountWrapper = new JDAccountWrapper();
		jdAccountWrapper.setJdAccount(jdAccount);
		jdAccountWrapper.setRequestHandler(requestHandler);
		JDRegisterAndLogin jdRegisterAndLogin = new JDRegisterAndLogin();
		Map<String, Object> visitJDLoginHomePageMap = jdRegisterAndLogin.visitJDLoginHomePage(jdAccountWrapper);
		MapUtils.traversalMap(visitJDLoginHomePageMap);
		jdRegisterAndLogin.fetchSlidingImage(requestHandler);
		//jdRegisterAndLogin.checkAuthCode(jdAccountWrapper);
		//jdRegisterAndLogin.fetchAuthCode(jdAccountWrapper);
		//jdRegisterAndLogin.loginJD(jdAccountWrapper,visitJDLoginHomePageMap);
		
	}

}