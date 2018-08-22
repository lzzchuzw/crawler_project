package com.tmall.request;

import org.apache.http.client.methods.RequestBuilder;

/**
 * 
* @ClassName: HttpRequestHeaderGenerator
* @Description: 设置天猫HTTP请求的Header类
* @author: leisure
* @date: 2018年8月20日 下午12:52:10
 */
public class HttpRequestHeaderGenerator {
	
	/**
	 * 设置访问天猫官网登录主页面的Headers
	 * @param builder
	 */
	public static void setVisitTmallLoginHomePageHeader(final RequestBuilder builder) {
		if(null!=builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("Host", "login.tmall.com");
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}

}
