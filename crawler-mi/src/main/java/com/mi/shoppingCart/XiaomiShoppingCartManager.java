package com.mi.shoppingCart;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.mi.request.HttpRequestHeaderGenerator;
import com.utils.json.JsonUtils;
import com.utils.map.MapUtils;
import com.utils.random.RandomNumberGenerator;
import com.utils.regex.RegexUtils;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.ResponseRet;


/**
 * 
 * @Description 小米商城管理购物车的类
 * @author lzz
 * @time 2018年3月6日 下午2:08:25
 */
public class XiaomiShoppingCartManager {
	
	Log log = LogFactory.getLog(this.getClass());
	
	
	/**
	 * 小米商城进入购物车主页面
	 * @param requestHandler
	 */
	public void gotoShoppingCartHomePage(final HttpClientRequestHandler requestHandler) {
		if(null==requestHandler) {
			return;
		}
		//设置访问方法GET https://static.mi.com/cart/
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri("https://static.mi.com/cart/");
    	//设置访问的Header
		HttpRequestHeaderGenerator.setGotoShoppingCartHomePageHeaders(requestBuilder);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "gotoShoppingCartHomePage");
	}
	/**
	 * 获取购物车中全部商品
	 * @param requestHandler
	 * @return
	 */
	public Map<String,Object> getProductListInCart(final HttpClientRequestHandler requestHandler){
		if(null==requestHandler) {
			return null;
		}
		//构造访问的url
		long nowTimes = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer("https://cart.mi.com/cart/getList.php?api=")
				              .append("1")
				              .append("&jsonpcallback=")
				              //.append("jQuery007_")
				              .append("jQuery111307675018715718371_")
				              .append(String.valueOf(nowTimes))
				              .append("&_=")
				              .append(String.valueOf(nowTimes+1));
		String url = new String(sb);
		System.out.println("getProductListInCart----------url = "+url);
		//设置访问方法GET https://cart.mi.com/cart/getList.php?api=1&jsonpcallback=jQuery111307675018715718371_1520578426779&_=1520578426780
		
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setGetProductListInCartHeaders(requestBuilder,url);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "getProductListInCart");
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
		String jsonString = RegexUtils.getValueBetweenHeadAndTail(responseString, "(", ")");
		Map<String,Object> retMap = JsonUtils.json2Map(jsonString);
		MapUtils.traversalMap(retMap);
		return retMap;
	}
	/**
	 * 将商品添加入购物车前的准备工作,获取一个cookie
	 * @param requestHandler
	 * @param productId
	 */
	public int prepareForAddProductToCart(final HttpClientRequestHandler requestHandler,final String productId,final String goodsId) {
		if(null==requestHandler || null==productId) {
			return -1;
		}
		//生成url
		long nowTimes = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer("https://cart.mi.com/cart/add/")
        		               .append(goodsId)
        		               .append("?jsonpcallback=")
        		               .append("jQuery007_")
        		               .append(String.valueOf(nowTimes))
        		               .append("&_=")
        		               .append(String.valueOf(nowTimes+1));        		               
        String url = new String(sb);
        System.out.println("prepareForAddProductToCart-----url = "+url);
        //设置访问方法GET https://cart.mi.com/cart/add/2173500019?jsonpcallback=jQuery111304483071387428923_1520391665938&_=1520391665949
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setGetGoodIdOfProductHeaders(requestBuilder,url,productId);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "prepareForAddProductToCart");
		int statusCode = responseRet.getStautsLine().getStatusCode();
		System.out.println("statusCode = "+statusCode);
		return statusCode;
	}
	/**
	 * 将商品添加到购物车  
	 * @param requestHandler
	 * @param productId  商品Id
	 * @param gid  goods_id
	 */
	public void addProductToCart(final HttpClientRequestHandler requestHandler,final String productId,final String gid) {
		if(null==requestHandler || null==productId || null==gid) {
			return;
		}
		//生成url
		StringBuffer sb = new StringBuffer("https://static.mi.com/buySuccess/?gid=")
				              .append(gid)
				              .append("&protype=product")
				              .append("&pid=")
				              .append(productId);
		String url = new String(sb);
		System.out.println("url = "+url);
		//设置访问方法GET https://static.mi.com/buySuccess/?gid=2171800013&protype=product&pid=10000057
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setAddProductToCartHeaders(requestBuilder,url,productId);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "addProductToCart");
		//获取responseString
		String responseString = null;
		try {
			responseString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			log.error("parse responseString error");
			e.printStackTrace();
		}
		if(null==responseString) {
			return ;
		}
		String msg = "已成功加入购物车！";
		boolean flag = responseString.contains(msg);
		System.out.println(flag?"加入成功":"添加购物车失败");
	}
	public List<String> fetchItemIdList(final HttpClientRequestHandler requestHandler){
		if(null==requestHandler) {
			return null;
		}
		List<String> itemIdList = new ArrayList<String>();
		//生成url
		long nowTimes = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer("https://cart.mi.com/cart/getList.php?api=1")
				              .append("&jsonpcallback=")
				              .append("jQuery007_"+String.valueOf(nowTimes))
				              //.append("jQuery111309565571203352241_"+nowTimes)
				              .append("&_=")
				              .append(String.valueOf(nowTimes+1));
		String url = new String(sb);
		System.out.println("fetchItemIdList------------------url = "+url);
		//设置访问方法GET https://static.mi.com/buySuccess/?gid=2171800013&protype=product&pid=10000057
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setFetchItemIdListHeaders(requestBuilder,url);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "fetchItemIdList");
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
		//解析返回值获取itemId
		itemIdList = RegexUtils.findValueGroup(responseString, "(?<=\"itemId\":\").*?_0_buy(?=\")");
		for(int index=0;index<itemIdList.size();index++) {
			String itemId = itemIdList.get(index);
			System.out.println("index = "+index+"----itemId = "+itemId);
		}
		return itemIdList;
	}
	/**
	 * 结算订单前的请求,某些时候为了获取一个cookie  Set-Cookie: xm_order_sid=13b46a01954a09bbe6eae98645ac5cd6; path=/; HttpOnly
	 * @param requestHandler
	 * @return
	 */
	public int checkoutPreCheck(final HttpClientRequestHandler requestHandler) {
		if(null==requestHandler) {
			return -1;
		}
		//生成url
		long nowTimes = System.currentTimeMillis();
		String r = RandomNumberGenerator.generateRandomDoubleString(15, 5);
		StringBuffer sb = new StringBuffer("https://order.mi.com/buy/checkoutPreCheck.php?r=")
				              .append(r)
				              .append("&jsonpcallback=")
				              .append("jQuery007_"+String.valueOf(nowTimes))
				              .append("&_=")
				              .append(String.valueOf(nowTimes+1));
		String url = new String(sb);
		System.out.println("checkoutPreCheck------------------url = "+url);
		//设置访问方法GET https://order.mi.com/buy/checkoutPreCheck.php?r=18470.1520393111&jsonpcallback=jQuery1113046581255250874265_1520393104747&_=1520393104749
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setCheckoutPreCheckHeaders(requestBuilder,url);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "checkoutPreCheck");
		int statusCode = responseRet.getStautsLine().getStatusCode();
		System.out.println("statusCode = "+statusCode);
		return statusCode;
	}
	/**
	 * 进入订单结算页
	 * @param requestHandler
	 */
	public Map<String,Object> checkoutOrder(final HttpClientRequestHandler requestHandler) {
		if(null==requestHandler) {
			return null;
		}
		//生成url
		String r = RandomNumberGenerator.generateRandomDoubleString(15, 5);
		StringBuffer sb = new StringBuffer("https://order.mi.com/buy/checkout?r=")
				              .append(r);
		String url = new String(sb);
		System.out.println("checkoutOrder------------------url = "+url);
		//设置访问方法GET https://static.mi.com/buySuccess/?gid=2171800013&protype=product&pid=10000057
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setCheckoutOrderHeaders(requestBuilder,url);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "checkoutOrder");
		int statusCode = responseRet.getStautsLine().getStatusCode();
		System.out.println("statusCode = "+statusCode);
		Map<String,Object> retMap = new HashMap<String,Object>();
		retMap.put("referer", url);
		return retMap;
	}
	/**
	 * 设置收货地址
	 * @param requestHandler
	 * @param map
	 */
	public void setRegionPayment(final HttpClientRequestHandler requestHandler,final Map<String,Object> map) {
		if(null==requestHandler || null==map || 0==map.size()) {
			return;
		}
		//获取map中的参数
		String referer = String.valueOf(map.get("referer"));
		String addressId = String.valueOf("addressId");
		String cityId = String.valueOf("cityId");
		//生成url
		String nowTimes = String.valueOf(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer("https://order.mi.com/buy/getRegionPayment.php?v")
				              .append(nowTimes);
		String url = new String(sb);
		System.out.println("setRegionPayment------------------url = "+url);
		//设置访问方法POST https://order.mi.com/buy/getRegionPayment.php?v1520400457928 
    	RequestBuilder requestBuilder = RequestBuilder.post()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setSetRegionPaymentHeaders(requestBuilder,url,referer);
		//设置访问携带Form
		Map<String,Object> formMap = new HashMap<String,Object>();
		formMap.put("region_id", 36);
		formMap.put("pay_id", 1);
		formMap.put("invoice_type", 4);
		formMap.put("invoice_title", "个人");
		formMap.put("quick_order", 0);
		try {
			requestBuilder.setEntity(new UrlEncodedFormEntity(
					HttpClientRequestHandler.generateNameValuePairs(formMap), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "setRegionPayment");
		//获取responseString
		String responseString = null;
		try {
			responseString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			log.error("parse responseString error");
			e.printStackTrace();
		}
		if(null==responseString) {
			return ;
		}
		System.out.println(responseString);
	}
	/**
	 * 提交订单
	 * @param requestHandler
	 * @param map
	 */
	public void submitOrder(final HttpClientRequestHandler requestHandler,final Map<String,Object> map) {
		if(null==requestHandler || null==map || 0==map.size()) {
			return;
		}
		//获取map中的参数
		String referer = String.valueOf(map.get("referer"));
		String addressId = String.valueOf(map.get("addressId"));
		System.out.println("referer = "+referer+"----addressId = "+addressId);
		//生成url
		String r = String.valueOf(Math.random());
		StringBuffer sb = new StringBuffer("https://order.mi.com/buy/submit?")
				              .append(r);
		String url = new String(sb);
		System.out.println("submitOrder------------------url = "+url);
		//设置访问方法POST https://order.mi.com/buy/submit?0.5125192184057987 
    	RequestBuilder requestBuilder = RequestBuilder.post()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setSubmitOrderPageHeaders(requestBuilder,referer);
		//设置访问携带Form
		Map<String,Object> formMap = new HashMap<String,Object>();
		formMap.put("Checkout[address_id]", addressId);
		formMap.put("Checkout[pay_id]", 1);
		formMap.put("Checkout[best_time]", 1);
		formMap.put("Checkout[invoice_type]", 4);
		formMap.put("Checkout[shipment_id]", 10);
		formMap.put("Checkout[pickup_id]", null);
		formMap.put("Checkout[coupons_value]", null);
		formMap.put("Checkout[coupons_type]", null);
		formMap.put("Checkout[invoice_title]", "个人");
		formMap.put("Checkout[invoice_tel]", null);
		formMap.put("Checkout[invoice_email]", null);
		formMap.put("risk_token", null);
		formMap.put("quick_order", 0);
		try {
			requestBuilder.setEntity(new UrlEncodedFormEntity(
					HttpClientRequestHandler.generateNameValuePairs(formMap), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "submitOrder");
		//获取responseString
		String responseString = null;
		try {
			responseString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			log.error("parse responseString error");
			e.printStackTrace();
		}
		if(null==responseString) {
			return ;
		}
		System.out.println(responseString);
		Map<String,Object> retMap = JsonUtils.json2Map(responseString);
		MapUtils.traversalMap(retMap);
		
	}

}
