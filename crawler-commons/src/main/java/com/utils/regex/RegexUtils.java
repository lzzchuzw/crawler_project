package com.utils.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	public static String findString(String begin,String end,String src) {
		String des = null;
		if(null==begin||null==end||null==src) {
			return des;
		}
		Pattern bPattern = Pattern.compile(begin);
		Matcher bm = bPattern.matcher(src);
		
		for(int count=1;bm.find();count++) {
			System.out.println("count = "+count);
		}
		int bLocation = bm.start()+begin.length();
		
		Pattern ePattern = Pattern.compile(end);
		Matcher em = ePattern.matcher(src.substring(bLocation));
		int eLocation = em.start();
		System.out.println("bLocation = "+bLocation+"---eLocation = "+eLocation);
		des = src.substring(bLocation, eLocation);
		return des;
	}
	/**
	 * 
	* @Title: findString
	* @Description: 找到唯一匹配的通用正则方法
	* @param src
	* @param regexString
	* @return String
	* @author leisure
	* @date 2018年5月14日下午2:18:35
	 */
	public static String findString(String src,String regexString) {
		String des = null;
		if(null==regexString||null==src) {
			return des;
		}
		System.out.println("regexString = "+regexString);
		Pattern p = Pattern.compile(regexString);
		Matcher m = p.matcher(src);
		while(m.find()) {
		   des = m.group();
		}
		//System.out.println("des = "+des);
		return des;
	}
	/**
	 * 匹配regexString之间的值  比如 (?<=").*?(?=")匹配两个双引号之间的数值      (?<=addressId=").*?(?=")配置以 addressId="开头  以"结尾的数值
	 * @param src
	 * @param regexString
	 * @return
	 */
	public static List<String> findValueGroup(String src,String regexString){
		if(null==src || null==regexString) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		Pattern p = Pattern.compile(regexString);
		Matcher m = p.matcher(src);
		while(m.find()) {
			list.add(m.group());
		}
		//输出
		/*for(String s:list) {
			System.out.println(s);
		}*/
		return list;
	}
	/**
	 * 获取head和tail两个字符串之间的字符串  
	 * 正则表达式regexString:  (?<=head).*?(?=tail) 当null==tail时  regexString: (?<=head).*?
	 * @param src
	 * @param head
	 * @param tail
	 * @return
	 */
	public static String getValueBetweenHeadAndTail(String src,String head,String tail) {
		String ret = null;
		if(null==src || null==head ) {
			return null;
		}
		StringBuffer sb = new StringBuffer("(?<=");
		sb.append(head)
		  .append(").*");
		if(null!=tail) {
			sb.append("?(?=")
			  .append(tail)
			  .append(")");			 
		}
		String regexString = new String(sb);
		//System.out.println("src = "+src);
		System.out.println("regexString = "+regexString);
		Pattern p = Pattern.compile(regexString);
		Matcher m = p.matcher(src);
		while(m.find()) {
		ret = m.group();
		}
		return ret;
	}
	/**
	 * 获取head和tail两个字符串之间的字符串  
	 * 正则表达式regexString:  (?<=head).*?(?=tail) 当null==tail时  regexString: (?<=head).*?
	 * @param src
	 * @param head
	 * @param tail
	 * @return 
	 */
	public static List<String> getValueListBetweenHeadAndTail(String src,String head,String tail) {
		List<String> stringList = new ArrayList<String>();
		if(null==src || null==head ) {
			return null;
		}
		StringBuffer sb = new StringBuffer("(?<=");
		sb.append(head)
		  .append(").*");
		if(null!=tail) {
			sb.append("?(?=")
			  .append(tail)
			  .append(")");			 
		}
		String regexString = new String(sb);
		//System.out.println("src = "+src);
		//System.out.println("regexString = "+regexString);
		Pattern p = Pattern.compile(regexString);
		Matcher m = p.matcher(src);
		while(m.find()) {
		
		  stringList.add(m.group());
		}
		return stringList;
	}
	
	public static String replaceString(String src,String patternRegex,String replaceString) {
		if(null==src || null == patternRegex || null==replaceString) {
			return src;
		}
		Pattern p = Pattern.compile(patternRegex);
		Matcher m = p.matcher(src);
		String dst = m.replaceAll(replaceString);
		System.out.println("src = "+src);
		System.out.println("patternRegex = "+patternRegex+"----replaceString = "+replaceString);
		System.out.println("dst = "+dst);
		return dst;
	}
	
	public static void main(String[] args) {
		replaceString("bcteth","usdt","usd");
	}

}
