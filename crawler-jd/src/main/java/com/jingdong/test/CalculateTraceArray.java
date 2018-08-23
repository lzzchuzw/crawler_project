package com.jingdong.test;

import java.util.LinkedList;
import java.util.List;

import com.utils.regex.RegexUtils;

public class CalculateTraceArray {
	public static void main(String[] args) {
		
		String s = "[\"776\", \"299\", 1535018380746]\r\n" + 
				"[\"807\", \"321\", 1535018380746]\r\n" + 
				"[\"806\", \"321\", 1535018380813]\r\n" + 
				"[\"807\", \"319\", 1535018380830]\r\n" + 
				"[\"809\", \"319\", 1535018380845]\r\n" + 
				"[\"812\", \"318\", 1535018380861]\r\n" + 
				"[\"817\", \"318\", 1535018380877]\r\n" + 
				"[\"820\", \"318\", 1535018380893]\r\n" + 
				"[\"822\", \"318\", 1535018380901]\r\n" + 
				"[\"825\", \"318\", 1535018380917]\r\n" + 
				"[\"828\", \"318\", 1535018380945]\r\n" + 
				"[\"832\", \"318\", 1535018380976]\r\n" + 
				"[\"837\", \"318\", 1535018381006]\r\n" + 
				"[\"844\", \"318\", 1535018381036]\r\n" + 
				"[\"853\", \"318\", 1535018381066]\r\n" + 
				"[\"855\", \"318\", 1535018381097]\r\n" + 
				"[\"858\", \"318\", 1535018381126]\r\n" + 
				"[\"859\", \"318\", 1535018381166]\r\n" + 
				"[\"860\", \"318\", 1535018381188]\r\n" + 
				"[\"864\", \"318\", 1535018381219]\r\n" + 
				"[\"871\", \"318\", 1535018381248]\r\n" + 
				"[\"872\", \"318\", 1535018381278]\r\n" + 
				"[\"876\", \"318\", 1535018381309]\r\n" + 
				"[\"877\", \"318\", 1535018381339]\r\n" + 
				"[\"878\", \"318\", 1535018381370]\r\n" + 
				"[\"879\", \"318\", 1535018381477]\r\n" + 
				"[\"881\", \"317\", 1535018381495]\r\n" + 
				"[\"880\", \"317\", 1535018381869]\r\n" + 
				"[\"879\", \"317\", 1535018381901]\r\n" + 
				"[\"878\", \"317\", 1535018381949]\r\n" + 
				"[\"877\", \"317\", 1535018382046]\r\n" + 
				"[\"876\", \"317\", 1535018382087]\r\n" + 
				"[\"875\", \"317\", 1535018382103]\r\n" + 
				"[\"875\", \"318\", 1535018382126]\r\n" + 
				"[\"875\", \"318\", 1535018382621]";
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
		for(int i=0;i<data.length;i++) {
			for(int j=0;j<3;j++) {
				c[i][j] = Long.valueOf(data[i][j].replace("\"", "").trim());
				System.out.println("c["+i+"]["+j+"] = "+c[i][j]);
			}
		}
		//pi("mlBUZix",4);
		gc(c);
	}
	
	public static String st(long d) {
		String[] s = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-~".split("");
		int lenth = s.length;
		long e = d;
		System.out.println("lenth = "+lenth);
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
		System.out.println(s);
		return s;
	}
	
	public static String pm(long d,int c,boolean b) {
		String e = st(Math.abs(d));
		String a = "";
		if(!b) {
			a += d>0?"1":"0";
		}
		a += pi(e,c);
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
        System.out.println(sb);
        return new String(sb);
	}
	

}
