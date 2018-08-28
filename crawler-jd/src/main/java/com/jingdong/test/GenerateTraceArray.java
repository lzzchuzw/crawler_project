package com.jingdong.test;

public class GenerateTraceArray {
	
	public static void main(String[] args) {
		generateArray(99,35);
	}
	
	public static void generateArray(int distance,int count) {
		//long x = 776;
		//long y = 299;
		//long t = System.currentTimeMillis();
		long[][] c = new long[count][3];
		long[] t = fun_t(count);
		long[] x = fun_x(t, distance);
		for(int index=0;index<count;index++) {
			System.out.println("t["+index+"] = "+t[index]+"---x["+index+"] = "+x[index]);
		}
	}
	
	
	public static long[] fun_t(int lenth) {
		int index = 0;
		long[] t = new long[lenth];
		t[index] = 0;
		index++;
		t[index] = 0;
		index++;
        while(index<lenth) {
	       double value = 0.05921*Math.pow(index, 3)-1.546*Math.pow(index, 2) + 31.6*index - 27.69;
           t[index] = (long) value;
	       index++;
        }
	    return t;
	}
	/**
	 * 
	* @Title: fun_x
	* @Description: 计算 滑动中 水平坐标
	* @param t
	* @param lenth 水平移动的总距离
	* @return long[]
	* @author leisure
	* @date 2018年8月28日下午5:54:52
	 */
	public static long[] fun_x(long[] t,int distance){
		long[] x = new long[t.length];
		int index = 0;
		//默认设置初始位置
		x[index] = 776;
		index++;
		x[index] = 776+distance/3;
		index++;
		for(;index<t.length;index++) {
			x[index] = (long)(893.9*Math.sin(0.0003758*t[index]+1.073) + 18.2*Math.sin(0.003239*t[index]+0.2217));
		}
		index--;
		long last = 776+distance;
		x[index] = x[index]==last?x[index]:last;
		return x;
	}
	
	public static long fun_y(long t) {
		long y = 0;
		return y;
	}

}
