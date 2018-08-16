package com.mi.initialize;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mi.account.XiaomiAccount;



/**
 * 
 * @Description 小米账号初始化类
 * @author lzz
 * @time 2018年3月1日 上午11:16:13
 */
public class XiaomiAccountInitialize {
	public static final String XIAOMI_ACCOUNT_FILE_PATH = "F:\\testFolder\\account\\xiaomi_account.txt";
	
	public static List<XiaomiAccount> parseXiaomiAccount(String src,AccountInitType initType){
		List<XiaomiAccount> xiaomiAccountList = new ArrayList<XiaomiAccount>();
		return xiaomiAccountList;
	}
	/**
	 * 从文件中解析小米商城的账号
	 * @param filePath
	 * @return
	 */
	public  List<XiaomiAccount> parseXiaomiAccountFromText(String filePath){
		List<XiaomiAccount> xiaomiAccountList = new ArrayList<XiaomiAccount>();
		if(null==filePath) {
			return null;
		}
		BufferedReader br = null;		
		try {
			br = new BufferedReader(new FileReader(filePath)) ;
			String s = null;
			while(null!=(s=br.readLine())) {
				
				xiaomiAccountList.add(generateXiaomiAccount(parseAccount(s)));
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return xiaomiAccountList;
	}
	public  List<String> parseAccount(String s) {
		if(null==s) {
			return null;
		}
		Pattern p =  Pattern.compile("-{4}");
		Matcher m = p.matcher(s);
		int count = 0;
		int start = 0;
		int end = 0;
		String ss = null;
		List<String> list = new ArrayList<String>();
	    while(m.find()) {
	    	count++;
	    	start = m.start();
	    	ss = s.substring(end,start);	    	
	    	end = m.end();
	    	System.out.println("count = "+count+"---start = "+start+"---end = "+end+"---ss = "+ss);
	    	list.add(ss);
	    }
	    ss = s.substring(end);
	    System.out.println("最后一个ss = "+ss);
	    list.add(ss);
	    return list;
	}
	/**
	 * 使用正则解析得到的字符串生成XiaomiAccount
	 * @param list
	 * @return
	 */
	public XiaomiAccount generateXiaomiAccount(List<String> list) {
		if(null==list ) {
			return null;
		}
		XiaomiAccount account = new XiaomiAccount();
		int index = 0;
		
		account.setUserName(list.get(index++));
		
		account.setLoginPassWd(list.get(index++));
		
		//account.setMobile(list.get(index++));
		return account;
	}

}
