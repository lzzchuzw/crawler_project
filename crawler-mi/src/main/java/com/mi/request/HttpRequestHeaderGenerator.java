package com.mi.request;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicHeader;

import com.utils.string.UrlParseUtils;

/**
 * 用于设置请求的Headers
 * @author Leisure
 * @version 创建时间：2018年1月26日 下午12:46:39 
 */
public class HttpRequestHeaderGenerator {

	/**
	 * 设置访问小米官网登录主页面的Headers
	 * @param builder
	 */
	public static void setVisitXiaomiLoginHomePageHeaders(final RequestBuilder builder) {
		if(null!=builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "account.xiaomi.com");
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 小米登录前验证方法
	 * @param builder
	 */
	public static void setServiceLoginAuth2Headers(final RequestBuilder builder) {
		if(null!=builder) {
			builder.setHeader("Accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Content-type", "application/x-www-form-urlencoded");
			builder.setHeader("Host", "account.xiaomi.com");
			builder.setHeader("Origin", "https://account.xiaomi.com");
			builder.setHeader("Referer", "https://account.xiaomi.com/pass/serviceLogin");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 小米登录成功后跳转 5个请求方法都会用到 其中4个是302跳转的请求
	 * @param builder
	 */
	public static void setloginSuccessRedirectLocationHeaders(final RequestBuilder builder) {
		if(null!=builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "account.xiaomi.com");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("Referer", "https://account.xiaomi.com/pass/serviceLogin");	
			builder.setHeader("Upgrade-Insecure-Requests", "1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**获取所有收货地址
	 * 设置获取用户收货地址Headers
	 * @param builder
	 */
	public static void setFetchAddressIdListHeaders(final RequestBuilder builder,String url) {
		if(null!=builder && null!=url) {
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 设置访问小米商城个人中心的Headers
	 * @param builder
	 */
	public static void setGotoPersonalCenterHomePageHeaders(final RequestBuilder builder,String host) {
		if(null!=builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Connection", "keep-alive");
			//builder.setHeader("Host", host);
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取预约地址
	 * 设置小米商城获取待抢购商品的预约Url的Headers
	 * @param builder
	 */
	public static void setFetchPrecontractShoppingRushUrlHeaders(final RequestBuilder builder,String host) {
		if(null!=builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Connection", "keep-alive");
			//builder.setHeader("Host", host);
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 预约
	 * 设置小米商城预约抢购商品的Header
	 * @param builder
	 * @param 待抢购商品Id
	 */
	public static void setPrecontractShoppingRushHeaders(final RequestBuilder builder,String url,String id) {
		if(null!=builder && null!=url && null!=id) {
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("referer", "https://item.mi.com/product/"+id+".html");
			builder.setHeader("pragma", "no-cache");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 预约
	 * 设置小米商城预约抢购商品的Header
	 * @param builder
	 * @param 待抢购商品Id
	 */
	public static void setGetGoodIdOfProductHeaders(final RequestBuilder builder,String url,String id) {
		if(null!=builder && null!=url && null!=id) {
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("referer", "https://item.mi.com/product/"+id+".html");
			builder.setHeader("pragma", "no-cache");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 小米商城进入购物车主页面
	 * 设置小米商城获取待抢购商品的预约Url的Headers
	 * @param builder
	 */
	public static void setGotoShoppingCartHomePageHeaders(final RequestBuilder builder) {
		if(null!=builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "static.mi.com");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("referer", "https://www.mi.com/index.html");
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取已加入到购物车中的商品
	 * 设置小米商城获取待小米商城购物车页面获取加入到购物车中的商品的Headers
	 * @param builder
	 */
	public static void setGetProductListInCartHeaders(final RequestBuilder builder,final String url) {
		if(null!=builder) {
			Map<String,Object> map = UrlParseUtils.parseUrl(url);	
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("Host", "static.mi.com");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("Referer", "https://static.mi.com/cart/");		
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 预约
	 * 设置小米商城预约抢购商品的Header
	 * @param builder
	 * @param 待抢购商品Id
	 */
	public static void setPrepareForAddProductToCartHeaders(final RequestBuilder builder,String url,String id) {
		if(null!=builder && null!=url && null!=id) {
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("referer", "https://item.mi.com/product/"+id+".html");
			builder.setHeader("pragma", "no-cache");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 
	 * 设置小米商城获取抢购前参数的Header
	 * @param builder
	 * @param 
	 */
	public static void setFetchShoppingRushParamtersHeaders(final RequestBuilder builder,String url,String id) {
		if(null!=builder && null!=url && null!=id) {
			String host = UrlParseUtils.parseUrlHost(url);		
			builder.setHeader("accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", host);
			builder.setHeader("referer", "https://item.mi.com/product/"+id+".html");
			builder.setHeader("pragma", "no-cache");	
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 
	 * 设置小米商城获取抢购跳转的url的Header
	 * @param builder
	 * @param 
	 */
	public static void setFetchShoppingRushSkipUrlHeaders(final RequestBuilder builder,String url,String id) {
		if(null!=builder && null!=url && null!=id) {
			String host = UrlParseUtils.parseUrlHost(url);		
			builder.setHeader("accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", host);
			builder.setHeader("referer", "https://item.mi.com/product/"+id+".html");
			builder.setHeader("pragma", "no-cache");	
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 添加商品到购物车
	 * 设置小米商城将商品添加到购物车的Header
	 * @param builder
	 * @param 待抢购商品Id
	 */
	public static void setAddProductToCartHeaders(final RequestBuilder builder,String url,String id) {
		if(null!=builder && null!=url && null!=id) {
			String host = UrlParseUtils.parseUrlHost(url);		
			builder.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("cache-control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", host);
			builder.setHeader("referer", "https://item.mi.com/product/"+id+".html");
			builder.setHeader("pragma", "no-cache");	
			builder.setHeader("Upgrade-Insecure-Requests", "1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取购物中的itemId
	 * 设置小米商城将商品添加到购物车的Header
	 * @param builder
	 * @param 待抢购商品Id
	 */
	public static void setFetchItemIdListHeaders(final RequestBuilder builder,String url) {
		if(null!=builder || null!=url ) {	
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-control", "no-cache");
			builder.setHeader("Referer", "https://static.mi.com/cart/");
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 订单结算前预处理
	 * 设置进入订单结算页前预处理的Header
	 * @param builder
	 * @param 待抢购商品Id
	 */
	public static void setCheckoutPreCheckHeaders(final RequestBuilder builder,String url) {
		if(null!=builder || null!=url ) {	
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-control", "no-cache");
			builder.setHeader("Referer", "https://static.mi.com/cart/");
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 进入订单提交页
	 * 设置进入订单提交页的Header
	 * @param builder
	 * @param 待抢购商品Id
	 */
	public static void setCheckoutOrderHeaders(final RequestBuilder builder,String url) {
		if(null!=builder || null!=url ) {	
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-control", "no-cache");
			builder.setHeader("Referer", "https://static.mi.com/cart/");
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 进入订单提交页
	 * 设置进入订单提交页的Header
	 * @param builder
	 * @param 待抢购商品Id
	 */
	public static void setSetRegionPaymentHeaders(final RequestBuilder builder,String url,final String referer) {
		if(null!=builder || null!=url ) {	
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "POST");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			builder.setHeader("Cache-control", "no-cache");
			builder.setHeader("Origin", "https://order.mi.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 设置提交订单的Headers
	 * @param builder
	 */
	public static void setSubmitOrderPageHeaders(final RequestBuilder builder, final String referer) {
		if(null!=builder && null!=referer) {
			builder.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Cache-Control", "no-cache");	
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			builder.setHeader("Host", "order.mi.com");
			builder.setHeader("Origin", "https://order.mi.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 将map转换为Header[]
	 * 
	 * @param map
	 * @return
	 */
	public static Header[] translateMapToHeaderArray(Map<String, Object> map) {
		if (null == map || 0 == map.size()) {
			return null;
		}
		Header[] requestHeader = new Header[map.size()];
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		int index = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			Header header = new BasicHeader(entry.getKey(), String.valueOf(entry.getValue()));
			requestHeader[index] = header;
			index++;
		}		
		return requestHeader;
	}

}
