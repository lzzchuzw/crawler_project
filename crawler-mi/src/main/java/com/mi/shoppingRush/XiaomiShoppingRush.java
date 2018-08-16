package com.mi.shoppingRush;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.mi.request.HttpRequestHeaderGenerator;
import com.utils.json.JsonUtils;
import com.utils.map.MapUtils;
import com.utils.regex.RegexUtils;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.RequestConstant;
import com.utils.request.ResponseRet;


/**
 * 
 * @Description 小米抢购
 * @author lzz
 * @time 2018年3月9日 下午4:34:51
 */
public class XiaomiShoppingRush {
	
	private Log log = LogFactory.getLog(this.getClass());
	/**
	 * 抢购前获取抢购相关参数
	 * salt=ec7f6cf401d16eca
	 * product=2172900006
	 * jsonpcallback=cn2172900006
	 * @param requestHandler
	 * @param productId
	 * @return
	 */
	public Map<String,Object> fetchShoppingRushParamters(final HttpClientRequestHandler requestHandler,String productId){
		if(null==requestHandler) {
			return null;
		}
		Map<String,Object> retMap = null;
		//构造访问的url
		long nowTimes = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer("https://tp.hd.mi.com/hdinfo/cn?jsonpcallback=")
				              .append("hdinfo")
				              .append("&storage=")
				              .append(2)
				              .append("&m=")
				              .append("1")
				              .append("&product=")
				              .append("&source=")
				              .append("&_=")
				              .append(String.valueOf(nowTimes));
		String url = new String(sb);
		System.out.println("fetchShoppingRushParamters----url = "+url);
        //设置访问方法GET https://tp.hd.mi.com/hdinfo/cn?jsonpcallback=hdinfo&storage=&m=1&product=&source=&_=1520582562811
		
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setFetchShoppingRushParamtersHeaders(requestBuilder,url,productId);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "fetchShoppingRushParamters");
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
		//解析responseString
		//String jsonString = RegexUtils.findString(responseString, "(?<=\\().*?(?=\\))");
		String jsonString = RegexUtils.getValueBetweenHeadAndTail(responseString, "\\(", "\\)");
		System.out.println("jsonString = "+jsonString);
		retMap = JsonUtils.json2Map(jsonString);
		retMap.put("jsonpcallback", "cn2172900006");
		retMap.put("salt", "ec7f6cf401d16eca");
		retMap.put("product", "2172900006");
		retMap.put("product", RequestConstant.PRODUCT_ID);
		MapUtils.traversalMap(retMap); 
		return retMap;
	}
	
	public Map<String,Object> fetchShoppingRushSkipUrl(final HttpClientRequestHandler requestHandler,Map<String,Object> map){
		if(null==requestHandler || null==map || 0==map.size()) {
			log.debug("function param is null or size is 0");
			return null;
		}
		//返回值
		Map<String,Object> retMap = null;
		//获取map中的参数
		String jsonpcallback = String.valueOf(map.get("jsonpcallback"));
		String product = String.valueOf(map.get("product"));
		String salt = String.valueOf(map.get("salt"));
		String productId = String.valueOf(map.get("productId"));
		//构造访问的url
		long nowTimes = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer("https://tp.hd.mi.com/hdget/cn?jsonpcallback=")
				              .append(jsonpcallback)
				              .append("&source=")
				              .append("bigtap")
				              .append("&product=")
				              .append(product)
				              .append("&addcart=1")
				              .append("&m=1")
				              .append("&fk=")
				              .append("&tsort=")
				              .append("&storage=2")
				              .append("&cstr1=0")
				              .append("&cstr2=0")
				              .append("&r=")
				              .append("&b=")
				              .append("&salt=")
				              .append(salt)
				              .append("&ans")
				              .append("_=")
				              .append(String.valueOf(nowTimes));
		String url = new String(sb);
		System.out.println("fetchShoppingRushUrl-----url = "+url);
        //设置访问方法GET https://tp.hd.mi.com/hdinfo/cn?jsonpcallback=hdinfo&storage=&m=1&product=&source=&_=1520582562811
		
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setFetchShoppingRushSkipUrlHeaders(requestBuilder,url,productId);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "fetchShoppingRushSkipUrl");
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
		//解析responseString
		//String jsonString = RegexUtils.findString(responseString, "(?<=\\().*?(?=\\))");
		String jsonString = RegexUtils.getValueBetweenHeadAndTail(responseString, "\\(", "\\)");
		retMap = JsonUtils.json2Map(jsonString);
		MapUtils.traversalMap(retMap); 
		return retMap;

	}
	
	

}
