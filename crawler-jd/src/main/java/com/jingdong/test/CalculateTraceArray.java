package com.jingdong.test;

import java.util.LinkedList;
import java.util.List;

import com.utils.regex.RegexUtils;

public class CalculateTraceArray {
	public static void main(String[] args) {
		
		String s = "[\"678\", \"299\", 1535357937826]\r\n" + 
				   "[\"714\", \"326\", 1535357937826]\r\n" + 
				   "[\"720\", \"326\", 1535357937980]\r\n" + 
				"[\"733\", \"327\", 1535357937998]\r\n" + 
				"[\"743\", \"328\", 1535357938013]\r\n" + 
				"[\"746\", \"329\", 1535357938030]\r\n" + 
				"[\"747\", \"329\", 1535357938047]\r\n" + 
				"[\"748\", \"329\", 1535357938107]\r\n" + 
				"[\"750\", \"329\", 1535357938115]\r\n" + 
				"[\"752\", \"329\", 1535357938130]\r\n" + 
				"[\"763\", \"331\", 1535357938147]\r\n" + 
				"[\"770\", \"333\", 1535357938163]\r\n" + 
				"[\"772\", \"333\", 1535357938180]\r\n" + 
				"[\"774\", \"333\", 1535357938284]\r\n" + 
				"[\"777\", \"333\", 1535357938297]\r\n" + 
				"[\"783\", \"333\", 1535357938313]\r\n" + 
				"[\"792\", \"333\", 1535357938331]\r\n" + 
				"[\"794\", \"333\", 1535357938347]\r\n" + 
				"[\"796\", \"333\", 1535357938475]\r\n" + 
				"[\"801\", \"333\", 1535357938497]\r\n" + 
				"[\"807\", \"333\", 1535357938513]\r\n" + 
				"[\"811\", \"333\", 1535357938531]\r\n" + 
				"[\"814\", \"333\", 1535357938547]\r\n" + 
				"[\"815\", \"333\", 1535357938710]\r\n" + 
				"[\"818\", \"333\", 1535357938731]\r\n" + 
				"[\"825\", \"333\", 1535357938747]\r\n" + 
				"[\"826\", \"333\", 1535357939779]\r\n" + 
				"[\"829\", \"332\", 1535357939797]\r\n" + 
				"[\"829\", \"332\", 1535357940500]";
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
		gc(c);
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
