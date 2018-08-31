package com.jingdong.test;

import java.util.LinkedList;
import java.util.List;

import com.utils.regex.RegexUtils;

public class CalculateTraceArray {
	public static void main(String[] args) {
		
		String s = "\r\n" + 
				"[\"678\", \"299\", 1535613506107]\r\n" + 
				"[\"706\", \"325\", 1535613506107]\r\n" + 
				"[\"706\", \"325\", 1535613506185]\r\n" + 
				"[\"708\", \"325\", 1535613506194]\r\n" + 
				"[\"720\", \"325\", 1535613506210]\r\n" + 
				"[\"735\", \"325\", 1535613506227]\r\n" + 
				"[\"747\", \"325\", 1535613506243]\r\n" + 
				"[\"758\", \"325\", 1535613506260]\r\n" + 
				"[\"761\", \"325\", 1535613506276]\r\n" + 
				"[\"763\", \"324\", 1535613506294]\r\n" + 
				"[\"765\", \"323\", 1535613506313]\r\n" + 
				"[\"767\", \"323\", 1535613506326]\r\n" + 
				"[\"774\", \"323\", 1535613506345]\r\n" + 
				"[\"784\", \"323\", 1535613506362]\r\n" + 
				"[\"791\", \"323\", 1535613506377]\r\n" + 
				"[\"794\", \"323\", 1535613506393]\r\n" + 
				"[\"794\", \"323\", 1535613506410]\r\n" + 
				"[\"796\", \"323\", 1535613506547]\r\n" + 
				"[\"801\", \"323\", 1535613506562]\r\n" + 
				"[\"804\", \"323\", 1535613506577]\r\n" + 
				"[\"807\", \"321\", 1535613506594]\r\n" + 
				"[\"810\", \"321\", 1535613506611]\r\n" + 
				"[\"811\", \"321\", 1535613506628]\r\n" + 
				"[\"812\", \"321\", 1535613506906]\r\n" + 
				"[\"814\", \"321\", 1535613506927]\r\n" + 
				"[\"817\", \"321\", 1535613506944]\r\n" + 
				"[\"819\", \"321\", 1535613507281]\r\n" + 
				"[\"821\", \"321\", 1535613507294]\r\n" + 
				"[\"822\", \"321\", 1535613507311]\r\n" + 
				"[\"824\", \"321\", 1535613507328]\r\n" + 
				"[\"825\", \"321\", 1535613507793]\r\n" + 
				"[\"828\", \"320\", 1535613507811]\r\n" + 
				"[\"829\", \"319\", 1535613507826]\r\n" + 
				"[\"828\", \"319\", 1535613508418]\r\n" + 
				"[\"827\", \"320\", 1535613508427]\r\n" + 
				"[\"827\", \"320\", 1535613509313]";
		/*String s1 = s.replace("[", "{").replace("]", "}");
		String[] s2 = s1.split("}");
		System.out.println(s1);
		for(int index=0;index<s2.length;index++) {
			System.out.println("s2["+index+"] = "+s2[index]);
		}*/
		String regexString = "(?<=\\[).*?(?=\\])";
		List<String> g = RegexUtils.findValueGroup(s, regexString);
		String[][] data = new String[g.size()][];
		for(int index=0;index<g.size();index++) {
			data[index] = g.get(index).split(",");
		}
		long[][] c = new long[data.length][3];
		System.out.println("data.lenth = "+data.length);
		for(int i=0;i<data.length;i++) {
			for(int j=0;j<3;j++) {
				c[i][j] = Long.valueOf(data[i][j].replace("\"", "").trim());
				//System.out.println("c["+i+"]["+j+"] = "+c[i][j]);
			}
		}
		//pi("mlBUZix",4);
		String d = gc(c);
		System.out.println("d = "+d);
	}
	
	public static String st(long d) {
		String[] s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-~".split("");
		int lenth = s.length;
		long e = d;
		//System.out.println("lenth = "+lenth);
		LinkedList<String> a = new LinkedList<String>();
        do {
        	int mod = (int) (e % lenth);
        	e = (e-mod) / lenth;
        	a.addFirst(s[mod]);
        }while(0!=e);
        //System.out.println(a.toString());
        StringBuilder sb = new StringBuilder();
        for(String t:a) {
        	sb.append(t);
        }
       // System.out.println("d = "+d+"---result: "+sb.toString());
        //System.out.println("st----ret = "+sb.toString());
        return new String(sb);
	}
	
	public static String pi(String a,int b) {
		StringBuilder sb = new StringBuilder(b-1);
		for(int index=1;index<b;index++) {
			sb.append(0);
		}
		String s = new String(sb)+a;
		//System.out.println(sb.toString());
		s = s.substring(s.length()-b);
		//System.out.println("pi----ret = "+s);
		return s;
	}
	
	public static String pm(long d,int c,boolean b) {
		String e = st(Math.abs(d));
		String a = "";
		if(!b) {
			a += d>0?"1":"0";
		}
		a += pi(e,c);
		//System.out.println("pm----ret = "+a);
		return a;
	}
	
	public static String gc(long [][]c) {
		LinkedList<String> b = new LinkedList<String>();
		for(int e=0;e<c.length;e++) {
			if(0==e) {
				b.add(pm(c[e][0] < 262143 ? c[e][0] : 262143, 3, true));
				b.add(pm(c[e][1] < 16777215 ? c[e][1] : 16777215, 4, true));
		        b.add(pm(c[e][2] < 4398046511103L ? c[e][2] : 4398046511103L, 7, true));
			}else {
				long a = c[e][0] - c[e - 1][0];
                long f = c[e][1] - c[e - 1][1];
                long d = c[e][2] - c[e - 1][2];
                b.add(pm(a < 4095 ? a : 4095, 2, false));
                b.add(pm(f < 4095 ? f : 4095, 2, false));
                b.add(pm(d < 16777215 ? d : 16777215, 4, true));
			}
		}
		StringBuilder sb = new StringBuilder();
        for(String t:b) {
        	sb.append(t);
        }
        //System.out.println("gc----ret = "+sb.toString());
        return new String(sb);
	}
	
	public static void ei() {
		
	}
	
	public static void si() {
		
	}
	

}
