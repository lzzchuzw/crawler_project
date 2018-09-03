package com.jingdong.test;

import java.util.Map;

import org.opencv.core.Point;

import com.jingdong.account.JDAccount;
import com.jingdong.account.JDAccountWrapper;
import com.jingdong.account.JDRegisterAndLogin;
import com.jingdong.image.OpencvHandler;
import com.jingdong.initialize.JDAccountInitialize;
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
		JDAccountInit();
		//JDMethodTest();
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
		JDAccountInitialize jdai = new JDAccountInitialize();
		jdai.parseJDAccountInfoFromText(JDAccountInitialize.jdAccountFilePath,false);
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
		//requestHandler.getContext().getCookieStore().getCookies();
		Map<String,Object> slidingImageMap = jdRegisterAndLogin.fetchSlidingImage(requestHandler);
		System.out.println("c = "+slidingImageMap.get("challenge"));
		//System.out.println("bgImgPath = "+slidingImageMap.get("bgImgPath")+"---patchImgPath = "+slidingImageMap.get("patchImgPath"));
		//获取水平移动距离
		Point maxPoint = OpencvHandler.matchTemplate(String.valueOf(slidingImageMap.get("bgImgPath")), String.valueOf(slidingImageMap.get("patchImgPath")));
		//GenerateTraceArray
		long[][] c = GenerateTraceArray.generateArray((int)maxPoint.x, (int)maxPoint.y, 35);
		long time = c[c.length-1][2]-c[0][2];
		System.out.println("time = "+time);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//CalculateTraceArray
		String d = CalculateTraceArray.gc(c);
		System.out.println("d = "+d);
		String s = jdRegisterAndLogin.getJdtdmapSessionId(requestHandler);
		System.out.println("s = "+s);
		slidingImageMap.put("traceValueD", d);
		//slidingImageMap.put("jdtdmapSessionId", s);
		slidingImageMap.put("jdtdmapSessionId", s);
		jdRegisterAndLogin.submitSlidingVerification(requestHandler, slidingImageMap);
		//jdRegisterAndLogin.checkAuthCode(jdAccountWrapper);
		//jdRegisterAndLogin.fetchAuthCode(jdAccountWrapper);
		//jdRegisterAndLogin.loginJD(jdAccountWrapper,visitJDLoginHomePageMap);
		
	}

}
