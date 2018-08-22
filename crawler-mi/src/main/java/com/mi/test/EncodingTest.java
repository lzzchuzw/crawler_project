package com.mi.test;

import com.utils.encryption.MD5Util;

public class EncodingTest {
	
	public static void main(String args[]) {
		md5EncodingTest("lzz851128");
	}
	
	public static void md5EncodingTest(String s) {
		if(null!=s) {
			String encodingString = MD5Util.EncodeMd5(s, false);
			System.out.println(s+"---md5Encoidng lowCase: "+encodingString);
		}
	}

}
