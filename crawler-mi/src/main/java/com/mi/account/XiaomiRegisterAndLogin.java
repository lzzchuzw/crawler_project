package com.mi.account;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;

import com.mi.request.HttpRequestHeaderGenerator;
import com.utils.encryption.MD5Util;
import com.utils.json.JsonUtils;
import com.utils.map.MapUtils;
import com.utils.regex.RegexUtils;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.ResponseRet;
import com.utils.request.ResponseType;



/**
 * 
 * @Description 小米官网注册与登录类
 * @author lzz
 * @time 2018年3月1日 上午11:13:04
 */
public class XiaomiRegisterAndLogin {
	private Log log = LogFactory.getLog(this.getClass()); 
	/**
	 * 访问小米登录主页面
	 * @param requestHandler
	 */
	public Map<String,Object>  visitXiaomiLoginHomePage(final XiaomiAccountWrapper xiaomiAccountWrapper) {
		if(null==xiaomiAccountWrapper) {
			log.error("xiaomiAccountWrapper is null");
			return null;
		}
		HttpClientRequestHandler requestHandler = xiaomiAccountWrapper.getRequestHandler();
		XiaomiAccount xiaomiAccount = xiaomiAccountWrapper.getXiaomiAccount();
		if(null==requestHandler || null==xiaomiAccount) {
			log.error("requestHandler is null or xiaomiAccount is null");
			return null;
		}
		//设置访问方法 GET https://account.xiaomi.com/pass/serviceLogin
		RequestBuilder requestBuilder = RequestBuilder.get()
				                                      .setUri("https://account.xiaomi.com/pass/serviceLogin");
		//设置访问的Header
		HttpRequestHeaderGenerator.setVisitXiaomiLoginHomePageHeaders(requestBuilder);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "visitXiaomiLoginHomePage");
		//获取responseString
		String responseString = null;
		try {
			responseString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			log.error("parse responseString error");
			e.printStackTrace();
		}
		if(null==responseString) {
			return null;
		}
		/**解析返回信息,获取相应form data参数 :
		 * callback:https://account.xiaomi.com                      (?<=callback:").*?(?=")
		 * sid:passport                                             (?<=sid:").*?(?=")
		 * qs:%3Fsid%3Dpassport                                     (?<=qs:").*?(?=")
		 * _sign:2&V1_passport&wqS4omyjALxMm//3wLXcVcITjEc=         (?<="_sign":").*?(?=")
		 * serviceParam:{"checkSafePhone":false}                    (?<=serviceParam\s:').*?(?=')
		 */
		Map<String,Object> visitXiaomiLoginHomePageMap = new HashMap<String,Object>();
		visitXiaomiLoginHomePageMap.put("callback", RegexUtils.getValueBetweenHeadAndTail(responseString, "callback:\"", "\""));
		visitXiaomiLoginHomePageMap.put("sid", RegexUtils.getValueBetweenHeadAndTail(responseString, "sid:\"", "\""));
		visitXiaomiLoginHomePageMap.put("qs", RegexUtils.getValueBetweenHeadAndTail(responseString, "qs:\"", "\""));
		visitXiaomiLoginHomePageMap.put("_sign", RegexUtils.getValueBetweenHeadAndTail(responseString, "\"_sign\":\"", "\""));
		visitXiaomiLoginHomePageMap.put("serviceParam", RegexUtils.getValueBetweenHeadAndTail(responseString, "serviceParam\\s:'", "'"));
		visitXiaomiLoginHomePageMap.put("user",xiaomiAccount.getUserName());
		visitXiaomiLoginHomePageMap.put("hash",MD5Util.EncodeMd5(xiaomiAccount.getLoginPassWd(), true));
		MapUtils.traversalMap(visitXiaomiLoginHomePageMap);
		//可能需要处理下Cookie Cookie之前已经保存到了responseRet中
		//访问小米登录主页面返回27个Cookie,但在提交登录请求的时候只用到了其中的5个Cookie 
		return visitXiaomiLoginHomePageMap;
	}
	/**
	 * 小米登录验证方法
	 * 1,提交的密码使用了md5加密;
	 * 2,返回值带有登录成功后的调整
	 * @param requestHandler
	 * @param visitXiaomiLoginHomePageMap
	 * @return 
	 */
	public Map<String,Object> serviceLoginAuth2(final HttpClientRequestHandler requestHandler,final Map<String,Object> visitXiaomiLoginHomePageMap) {
		Map<String,Object>  serviceLoginAuth2Map = null;
		if(null==requestHandler || null==visitXiaomiLoginHomePageMap) {
			return serviceLoginAuth2Map;
		}
		//设置访问方法POST https://account.xiaomi.com/pass/serviceLoginAuth2?_dc=1519866150050
		//参数_dc 是当前时间 System.currentTimeMillis()
		RequestBuilder requestBuilder = RequestBuilder.post()
                                                      .setUri("https://account.xiaomi.com/pass/serviceLoginAuth2?_dc="+System.currentTimeMillis());
		//设置访问的Header
		HttpRequestHeaderGenerator.setServiceLoginAuth2Headers(requestBuilder);
	    //设置访问携带Form
		visitXiaomiLoginHomePageMap.put("_json","true");
		
		MapUtils.traversalMap(visitXiaomiLoginHomePageMap);
		try {
			requestBuilder.setEntity(new UrlEncodedFormEntity(
					HttpClientRequestHandler.generateNameValuePairs(visitXiaomiLoginHomePageMap), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "serviceLoginAuth2");
		//(?<=&&&START&&&).*
		//获取responseString
		String responseString = null;
		try {
			responseString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			log.error("parse responseString error");
			e.printStackTrace();
		}
		if(null==responseString) {
			return serviceLoginAuth2Map;
		}
		/**
		 * 解析返回的Json
		 * &&&START&&&{"desc":"成功",
		 *	"location":"https://account.xiaomi.com/?ticket=0&pwd=1&d=wb_b4dad3bc-280c-4221-bb37-3e944859af21&p_ts=1519962030000&fid=0&auth=2%26V1_passport%26j%2FqaTmZrqEeMmpPHny4VR%2FZWeh9rTDiOl%2Bf9O%2B1F%2BNg7QhxMiXfHgeg2ZrkgAMJt%2BYKgczoajd7GViZBPPQKJkYiEomf7AnR%2FrifekcXtYiA0fKPyl73FQfjlKvxdY5QtZkMW5XCAbryWhiCNiAyM7M095dIjDnhplIb4XppzMM%3D&m=1&nonce=pFBI8N%2F2NQoBgovc&_ssign=2%26V1_passport%26sw9MLR7iZMyRHQhYBFyQ26g2mrA%3D",
		 *	"captchaUrl":null,
		 *	"code":0,
		 *	"notificationUrl":"",
		 *	"passToken":"V1:OXlzXsiLRK8cheFEdkAWy1z4drznxj87F7fsZOz5ZlY6H0Kfco8DyYRJzSvWrAoDnG7T7PilAJ63Ta4XyLu5ueRsUmRIkx2JcLKwwdjNpexR2EPuaMb/+Mi6UyBEdr+URjhnwKIRsoMG3p9VEG20ROSOpKqDN4YgPN9BvIoHMYenEOX237kecDpsczNPkkHI2r0YQmcUh3W3qm/mahzZqY6RbJ56MMy2tRTRoN2mo1iWXOuWDx9vB9Yw68/SXFZ+",
		 *	"securityStatus":0,
		 *	"pwd":1,
		 *	"ssecurity":"hYwPZjQvYg0khTNxBNCgVw==",
		 *	"nonce":9033964123932059648,
		 *	"cUserId":"oE4lLK850KxWBMcjcTYhpnGV6ns",
		 *	"userId":249332099,
		 *	"psecurity":"4PKEnPehifSJjfwHuvJK5Q==",
		 *	"qs":"%3Fsid%3Dpassport"}
		 */
		responseString = RegexUtils.getValueBetweenHeadAndTail(responseString, "&&&START&&&", null);
		System.out.println("responseString = "+responseString);
		serviceLoginAuth2Map = JsonUtils.json2Map(responseString);
		MapUtils.traversalMap(serviceLoginAuth2Map);
		return serviceLoginAuth2Map;		
	}
	/**
	 * 小米登录成功后的跳转方法,共有5次跳转,其中前面4次为302跳转
	 * @param requestHandler
	 * @param map 小米登录验证后解析获取到的map
	 * @return
	 */
	public Map<String,Object> loginSuccessRedirectLocation(final HttpClientRequestHandler requestHandler,final Map<String,Object>  map) {
		Map<String,Object> retMap = null;
		if(null==requestHandler || null==map ||0==map.size()) {
			return retMap;
		}
		String locationUrl = String.valueOf(map.get("location"));
		int count = 1;
		ResponseRet responseRet = null;
		do {
			
			System.out.println("count = "+count+"---locationUrl = "+locationUrl);
			responseRet = loginSuccessJump(requestHandler,locationUrl,false);
			
			//获取下一次请求的url
			locationUrl = responseRet.getLocationUrl();
			count++;
			System.out.println("responseType = "+responseRet.getRetType()+"---get locationUrl = "+locationUrl);
		}while(null!=responseRet && ResponseType.LOCATION == responseRet.getRetType());
		
		return retMap;
	}
	
	/**
	 * 小米登录成功后的跳转方法,共有5次跳转,其中前面4次为302跳转
	 * @param requestHandler
	 * @param locationUrl 重定向跳转的url
	 * @param isRedirectsEnabled 是否支持重定向
	 * @return
	 */
	public ResponseRet loginSuccessJump(final HttpClientRequestHandler requestHandler,final String locationUrl, boolean isRedirectsEnabled){
		ResponseRet responseRet = null;
		if(null==requestHandler || null==locationUrl) {
			return responseRet;
		}
		System.out.println("-----------------loginSuccessJump   begin----------------------");
		URI uri = null;
		try {
			uri = new URI(locationUrl);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if(null == uri) {
			return null;
		}
		//设置访问方法 GET location
		RequestBuilder requestBuilder = RequestBuilder.get()
				                                      .setUri(uri);
		//设置参数配置
		HttpClientContext context = requestHandler.getContext();
		RequestConfig requestConfig = RequestConfig.custom().setMaxRedirects(5)
				                                            .setCircularRedirectsAllowed(true)
				                                            .setConnectTimeout(10000)
				                                            .setConnectionRequestTimeout(10000)
				                                            .build();
		context.setRequestConfig(requestConfig);
		//设置访问的Header
		HttpRequestHeaderGenerator.setloginSuccessRedirectLocationHeaders(requestBuilder);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		responseRet = requestHandler.HttpRequestHandler(requestHandler, "loginSuccessJump", isRedirectsEnabled);
		System.out.println("-----------------loginSuccessJump   end----------------------");		
		return responseRet;
	}
	
	public void visitXiaomiShoppingMallHomePage(final HttpClientRequestHandler requestHandler) {
		if(null==requestHandler) {
			return;
		}
	}
	/*public void xiaomiLoginFromXiaomiShoppingMallHomePage(final HttpClientRequestHandler requestHandler) {
		
	}*/
	

}
