package com.utils.date;

import java.text.SimpleDateFormat;
/**
 * 
* @ClassName: DateUtils
* @Description: 时间处理的工具类
* @author: leisure
* @date: 2018年6月13日 下午5:35:48
 */
public class DateUtils {
	
	public static final String dateformateString = "yyyy-MM-dd HH:mm:ss.SS";
	
	/**
	 * 
	* @Title: dateFormate
	* @Description: 将毫秒的long数据转换为"yyyy-MM-dd HH:mm:ss.SS"形式的字符串形式
	* @param times
	* @return String
	* @author leisure
	* @date 2018年6月13日下午5:36:09
	 */
	public static String dateFormate(long times) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateformateString);
		return sdf.format(times);
	}

}
