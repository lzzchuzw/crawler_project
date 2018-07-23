package com.utils.request;

import java.util.Map;

import org.apache.http.client.methods.RequestBuilder;

import com.utils.string.UrlParseUtils;

/**
 * 用于设置请求Header
 * @author leisure
 *
 */
public class HttpRequestHeaderGenerator {
	
	/**
	 * 设置访问百度主页的Header  
	 */
	public static void setVisitBaiduHeaders(final RequestBuilder builder,String url) {
		if(null!=builder || null!=url ) {	
			/*Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));*/
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			//builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			builder.setHeader("Cache-control", "max-age=0");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "www.baidu.com");
			//builder.setHeader("X-Requested-With", "XMLHttpRequest");
			//builder.setHeader("Pragma", "no-cache");	
			
			builder.setHeader("Upgrade-Insecure-Requests","1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
		}
	}
	
    /**
     * 设置获取火币网MarketDepth的Header
     */
	public static void setGetMarketDepthHeaders(final RequestBuilder builder,String url) {
		if(null!=builder || null!=url ) {	
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			/*builder.setHeader("Cache-control", "no-cache");
			builder.setHeader("Origin", "https://order.mi.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("Pragma", "no-cache");*/	
			builder.setHeader("upgrade-insecure-requests","1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
		}
	}
	
	/**
     * 设置获取交易所MarketInfo的通用Header
     */
	public static void setGetMarketInfoeaders(final RequestBuilder builder,String url) {
		if(null!=builder || null!=url ) {	
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			/*builder.setHeader("Cache-control", "no-cache");
			builder.setHeader("Origin", "https://order.mi.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("Pragma", "no-cache");*/	
			builder.setHeader("upgrade-insecure-requests","1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
		}
	}
	
	/**
     * 设置Post MarketInfo的Header
     */
	public static void setPostMarketInfoHeaders(final RequestBuilder builder,String url) {
		if(null!=builder || null!=url ) {	
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "POST");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			/*builder.setHeader("Cache-control", "no-cache");
			builder.setHeader("Origin", "https://order.mi.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("Pragma", "no-cache");*/	
			builder.setHeader("upgrade-insecure-requests","1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
		}
	}
	
	/**
	 * 调用API的统一请求头
	 */
	public static void setRequestMarketInfoHeaders(final RequestBuilder builder,String url) {
		if(null!=builder || null!=url ) {	
			Map<String,Object> map = UrlParseUtils.parseUrl(url);			
			builder.setHeader(":authority", String.valueOf(map.get("host")));
			builder.setHeader(":method", "GET");
			builder.setHeader(":path", String.valueOf(map.get("path")));
			builder.setHeader(":scheme", String.valueOf(map.get("scheme")));
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			/*builder.setHeader("Cache-control", "no-cache");
			builder.setHeader("Origin", "https://order.mi.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("Pragma", "no-cache");*/	
			builder.setHeader("upgrade-insecure-requests","1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.71 Safari/537.36");
		}
	}
}
