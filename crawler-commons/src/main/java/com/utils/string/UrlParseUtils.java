package com.utils.string;

import java.util.HashMap;
import java.util.Map;

import com.utils.map.MapUtils;

/**
 * 
 * @Description 解析Url的类
 * @author lzz
 * @time 2018年3月6日 上午10:43:12
 */
public class UrlParseUtils {
	
	/**
	 * 解析Url,得到Url包含的scheme host path等参数
	 * @param url https://order.mi.com/buyservice/appointmentCreate?jsonpcallback=jQuery111306325275283766669_1520264005189&goods_id=2175200008&appointment_batch=subscribe_batch_20180301152018&activity_code=&_=1520264005198
	 * @return scheme = https
	 *         host = order.mi.com
	 *         path = /buyservice/appointmentCreate?jsonpcallback=jQuery111306325275283766669_1520264005189&goods_id=2175200008&appointment_batch=subscribe_batch_20180301152018&activity_code=&_=1520264005198
	 */
	public static  Map<String,Object> parseUrl(String url) {
		if(null==url) {
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		String scheme = url.substring(0,url.indexOf("://"));
		url = url.substring((scheme+"://").length());
		String host = url.substring(0,url.indexOf('/'));
		String path = url.substring(host.length());
		/*System.out.println("url = "+url);
		System.out.println("scheme = "+scheme);
		System.out.println("host = "+host);
		System.out.println("path = "+path);*/
		map.put("scheme", scheme);
		map.put("host", host);
		map.put("path", path);
		MapUtils.traversalMap(map);
		return map;		
	}
	/**
	 * 解析Url 得到scheme
	 * @param url
	 * @return scheme
	 */
	public static String parseUrlScheme(final String url) {
		if(null==url) {
			return null;
		}
		String scheme = url.substring(0, url.indexOf("://"));
		System.out.println("scheme = "+scheme);
		return scheme;
	}
	/**
	 * 解析Url 得到host
	 * @param url
	 * @return
	 */
	public static String parseUrlHost(String url) {
		if(null==url) {
			return null;
		}
		String scheme = parseUrlScheme(url);
		url = url.substring((scheme+"://").length());
		//System.out.println("url = "+url);
		String host = url.substring(0,url.indexOf('/'));
		System.out.println("host = "+host);
		return host;
	}
	/**
	 * 解析Url 得到path
	 * @param url
	 * @return
	 */
	public static String parseUrlPath(String url) {
		if(null==url) {
			return null;
		}
		String scheme = parseUrlScheme(url);
		String host = parseUrlHost(url);
		url = url.substring((scheme+"://").length());
		String path = url.substring(host.length());
		System.out.println("path = "+path);
		return path;
	}

}
