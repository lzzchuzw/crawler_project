package com.mi.request;

import java.util.HashMap;
import java.util.Map;

import com.utils.encryption.MD5Util;
import com.utils.map.MapUtils;
import com.utils.string.UrlParseUtils;

public class StringTest {
	
	public static void main(String[] args) {
		String url = "https://my.mi.com/portal";
		String url2 = "https://order.mi.com/portal?r=71505.1520233655";
		String url3 = "https://order.mi.com/buyservice/appointmentCreate?jsonpcallback=jQuery111306325275283766669_1520264005189&goods_id=2175200008&appointment_batch=subscribe_batch_20180301152018&activity_code=&_=1520264005198";
		/*parseHostFromUrl(url);
		parseHostFromUrl(url2);
		parseUrl(url3);*/
		/*UrlParseUtils.parseUrlScheme(url3);
		UrlParseUtils.parseUrlHost(url3);
		UrlParseUtils.parseUrlPath(url3);
		UrlParseUtils.parseUrl(url3);*/
		md5();
	}
	
	public static void parseHostFromUrl(String url) {
		if(null==url) {
			return;
		}
		String scheme = parseSchemeFromUrl(url);
		url = url.substring((scheme+"://").length());
		System.out.println("url = "+url);
		String host = url.substring(0,url.indexOf('/'));
		System.out.println("host = "+host);
	}
	
	public static String parseSchemeFromUrl(String url) {
		if(null==url) {
			return null;
		}
		String scheme = url.substring(0,url.indexOf("://"));
		System.out.println("scheme = "+scheme);
		return scheme;
	}
	
	public static String parsePathFromUrl(String url,String scheme,String host) {
		if(null==url || null==scheme || null==host) {
			return null;
		}
		
		String path = url.substring((scheme+"://"+host).length());
		System.out.println("path = "+path);
		return path;
	}
	
	public static  Map<String,Object> parseUrl(String url) {
		if(null==url) {
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		String scheme = url.substring(0,url.indexOf("://"));
		url = url.substring((scheme+"://").length());
		String host = url.substring(0,url.indexOf('/'));
		String path = url.substring(host.length());
		System.out.println("url = "+url);
		System.out.println("scheme = "+scheme);
		System.out.println("host = "+host);
		System.out.println("path = "+path);
		map.put("scheme", scheme);
		map.put("host", host);
		map.put("path", path);
		MapUtils.traversalMap(map);
		return map;
		
	}
	
	public static void md5() {
		String s = MD5Util.EncodeMd5("lzz851128",true);
		System.out.println("s = "+s);
	}

}
