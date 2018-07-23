package com.utils.string;

public class StringUtils {
	
	public static void main(String[] args) {
		dropSpecificSubString("btc_usdt", "_");
	}
	
	public static String dropSpecificSubString(String src,String specificSubString) {
		if(null==src || null==specificSubString) {
			return null;
		}
		String dst = src.replace(specificSubString, "");
		
		System.out.println("dst = "+dst);
		
		return dst;
		
	}
	
	public static boolean isEmpty(String str) {
		if(str == null) 
			return true; 
		String tempStr = str.trim(); 
		if(tempStr.length() == 0)
			return true; 
		if(tempStr.equals("null"))
			return true;
		return false; 
	}
	

}
