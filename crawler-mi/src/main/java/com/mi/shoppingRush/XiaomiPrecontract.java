package com.mi.shoppingRush;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.mi.request.HttpRequestHeaderGenerator;
import com.utils.json.JsonUtils;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.ResponseRet;


/**
 * 
 * @Description 小米预约
 * @author lzz
 * @time 2018年3月6日 上午9:15:21
 */
public class XiaomiPrecontract {
	
private Log log = LogFactory.getLog(this.getClass()); 
	
	/**
	 * 获取预抢购商品的预约Url,该请求返回json,对json解析得到相应的url
	 * 返回json实例:fetchJSON({"type":"1","yueEtime":"2018-01-19 09:30:00","plusType":0,"d":55323,"stime":"2018-01-17 16:13:45","plusEtime":"","state":2,"qiangEtime":"2018-01-19 12:00:00","sku":6138263,"url":"//yushou.jd.com/toYuyue.action?sku=6138263&key=c422b1c98126552e8b94dfd53c846bce","isJ":0,"info":"\u9884\u7ea6\u8fdb\u884c\u4e2d","category":"4","isBefore":0,"num":125946,"flag":false,"etime":"2018-01-19 09:30:00","qiangStime":"2018-01-19 10:00:00","plusD":55323,"yueStime":"2018-01-17 16:13:45","plusStime":""});
	 * @param requestHandler
	 * @param productId 待抢购商品的Id
	 * @return 
	 */
	public Map<String,Object> fetchPrecontractShoppingRushUrl(HttpClientRequestHandler requestHandler,String productId) {
		Map<String,Object>  fetchPrecontractShoppingRushUrlMap = null;
		if(null==requestHandler || null==productId) {
			System.out.println("fetchPrecontractShoppingRushUrl 参数非法");
			return fetchPrecontractShoppingRushUrlMap;
		}
		// 设置访问方法GET "https://yushou.jd.com/youshouinfo.action?callback=fetchJSON&sku=6138263"
		RequestBuilder requestBuilder = RequestBuilder.get()// Get 方法
				                                      .setUri("https://yushou.jd.com/youshouinfo.action?callback=fetchJSON&sku=6138263");// 设置访问的Uri
		// 设置访问的Header
		HttpRequestHeaderGenerator.setFetchPrecontractShoppingRushUrlHeaders(requestBuilder,productId);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		// 发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "fetchPrecontractShoppingRushUrl");
		String initString = null;
		try {
			initString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("initString = "+initString);
		String parseString = initString.substring(initString.indexOf("fetchJSON(")+"fetchJSON(".length(), initString.length()-2);//返回的json最后面还有个分号 所以-2
		System.out.println("parseString = "+parseString);
		fetchPrecontractShoppingRushUrlMap = JsonUtils.json2Map(parseString);
		return fetchPrecontractShoppingRushUrlMap;
	}
	
	/**
	 * 预约抢购,获取抢购资格
	 * @param requestHandler
	 * @param precontractUrl  https://yushou.jd.com/toYuyue.action?sku=6138263&key=c422b1c98126552e8b94dfd53c846bce
	 * @return 是否预约成功
	 */
	public boolean precontractShoppingRush(HttpClientRequestHandler requestHandler,Map<String,Object>  fetchPrecontractShoppingRushUrlMap) {
		boolean flag = false;
		if(null==requestHandler || null==fetchPrecontractShoppingRushUrlMap || 0==fetchPrecontractShoppingRushUrlMap.size()) {
			System.out.println("precontractShoppingRush 参数非法");
			return flag;
		}
		String precontractUrl = "https:"+String.valueOf(fetchPrecontractShoppingRushUrlMap.get("url"));
		String skuId = String.valueOf(fetchPrecontractShoppingRushUrlMap.get("sku"));
		// 设置请求方法 GET https://yushou.jd.com/toYuyue.action?sku=6138263&key=c422b1c98126552e8b94dfd53c846bce
		RequestBuilder requestBuilder = RequestBuilder.get()// Get 方法
				                                      .setUri(precontractUrl);
		// 设置访问的Header
		HttpRequestHeaderGenerator.setFetchPrecontractShoppingRushUrlHeaders(requestBuilder,skuId);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);		
		//发起请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "precontractShoppingRush");
		String responseString = null;
		try {
			responseString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(responseString.contains("compatible: true")) {
			flag = true;
		}
		return flag;		
	}


}
