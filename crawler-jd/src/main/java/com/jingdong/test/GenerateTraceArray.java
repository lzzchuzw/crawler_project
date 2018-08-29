package com.jingdong.test;

public class GenerateTraceArray {
	
	public static void main(String[] args) {
		generateArray(133,26,35);
	}
	
	public static long[][] generateArray(int distanceX,int distanceY,int count) {
		//long x = 776;
		//long y = 299;
		//long t = System.currentTimeMillis();
		long nowTimes = System.currentTimeMillis();
		long[][] c = new long[count][3];
		long[] t = fun_t(count);
		long[] x = fun_x(t, distanceX);
		long[] y = fun_y(t,distanceY);
		for(int index=0;index<count;index++) {
			System.out.println("t["+index+"] = "+t[index]+"---x["+index+"] = "+x[index]+"---y["+index+"] = "+y[index]);
			c[index][0] = x[index];
			c[index][1] = y[index];
			c[index][2] = t[index]+nowTimes;
		}
		return c;
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
           //double value = 0.07921*Math.pow(index, 3)-1.246*Math.pow(index, 2) + 34.6*index - 27.69;
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
		x[index] = x[index-1]+distance/3;
		index++;
		for(;index<t.length;index++) {
			x[index] = (long)(893.9*Math.sin(0.0003758*t[index]+1.073) + 18.2*Math.sin(0.003239*t[index]+0.2217));
		}
		index--;
		long last = 776+distance;
		x[index] = x[index]==last?x[index]:last;
		return x;
	}
	
	public static long[] fun_y(long[] t,int distanceY) {
		long[] y = new long[t.length];
		int index = 0;
		y[index] = 299;
		index++;
		/*y[index] = 299+distanceY-5;
		index++;*/
		for(;index<t.length;index++) {
			y[index] = (long)(0.0002608*t[index]+317.3);
		}
		/*index--;
		long last = 299+distanceY;
		y[index] = last;*/
		return y;
	}

}
