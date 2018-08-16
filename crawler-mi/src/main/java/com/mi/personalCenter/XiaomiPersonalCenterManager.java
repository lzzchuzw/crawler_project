package com.mi.personalCenter;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.mi.jsoup.JSoupHandler;
import com.mi.request.HttpRequestHeaderGenerator;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.ResponseRet;
import com.utils.request.ResponseType;


/**
 * 小米个人中心
 * 
 * @Description 小米个人中心
 * @author lzz
 * @time 2018年3月5日 下午2:52:12
 */
public class XiaomiPersonalCenterManager {
    
	/**
	 * 小米商城进入个人主页页面 请求分两步
	 * 1,GET https://my.mi.com/portal 302跳转 location: https://order.mi.com/portal?r=71505.1520233655
	 * 2,GET https://order.mi.com/portal?r=71505.1520233655 返回个人中心主页页面,页面上包含子页面跳转地址 比如 “收货地址” GET https://order.mi.com/user/address?r=35304.1520219562
	 * @param requestHandler
	 */
	public Map<String,Object> gotoPersonalCenterHomePage(final HttpClientRequestHandler requestHandler) {
		Map<String,Object> personalCenterHomePageMap = null;
		if (null == requestHandler) {
			System.out.println("requsetHanlder is null");
			return null;
		}
		String url = "https://my.mi.com/portal";
		ResponseRet responseRet = null;
		//两次跳转,第一次是302
		do {
			responseRet = portalRequestJump(requestHandler,url,false);
			//获取下一次请求的url
			url = responseRet.getLocationUrl();
		}while(null!=responseRet && ResponseType.LOCATION == responseRet.getRetType());
		
		//解析网页获取相应的子链接跳转url
		
		//使用JSoup解析页面获取子链接跳转
		JSoupHandler.parserResponseRet_gotoPersonalCenterHomePage(responseRet);
		
		return personalCenterHomePageMap;
		
	}
	
	public ResponseRet portalRequestJump(final HttpClientRequestHandler requestHandler,final String url,boolean isRedirectsEnabled) {
		ResponseRet responseRet = null;
		if(null==requestHandler || null==url) {
			return null;
		}
		URI uri = null;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if(null == uri) {
			return null;
		}
		//设置访问方法 GET location
		RequestBuilder requestBuilder = RequestBuilder.get()
						                              .setUri(uri);
		//设置访问的Header 
		//String url = "https://my.mi.com/portal";
		//String url2 = "https://order.mi.com/portal?r=71505.1520233655";
		String host = url.substring(0+"https://".length(),url.lastIndexOf('/'));
		HttpRequestHeaderGenerator.setGotoPersonalCenterHomePageHeaders(requestBuilder,host);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		responseRet = requestHandler.HttpRequestHandler(requestHandler, "portalRequestJump", isRedirectsEnabled);
		
		return responseRet;
		
	}

}
