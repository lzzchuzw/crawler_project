package com.jingdong.test;

import com.utils.random.RandomNumberGenerator;

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
		System.out.println("distanceX = "+distanceX+"--distanceY = "+distanceY);
		for(int index=0;index<count;index++) {
			//System.out.println("t["+index+"] = "+t[index]+"---x["+index+"] = "+x[index]+"---y["+index+"] = "+y[index]);
			c[index][0] = x[index];
			c[index][1] = y[index];
			c[index][2] = t[index]+nowTimes;
			System.out.println("c["+index+"][0] = "+c[index][0]+"---c["+index+"][1] = "+c[index][1]+"---c["+index+"][2] = "+c[index][2]);
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
		//初始化值
		int initValue = 776;
		//第一个值
		//x[index] = RandomNumberGenerator.generateRandomNumBetweenBounday(initValue, initValue+120);
		x[index] = initValue;
		index = index+2;
		//从第三个值开始到倒数第8个
		for(;index<t.length-8;index++) {
			x[index] = (long)(893.9*Math.sin(0.0003758*t[index]+1.073) + 18.2*Math.sin(0.003239*t[index]+0.2217));
		}
		//第二个值
		x[1] = RandomNumberGenerator.generateRandomNumBetweenBounday((int)x[2]-5, (int)x[2]+3);
		//最后的稳定值
		long lastValue = x[0]+distance;
		for(;index<t.length-2;index++) {
			x[index] = (int)RandomNumberGenerator.generateRandomNumBetweenBounday((int)lastValue-2, (int)lastValue+1);
		}
		x[index] = lastValue;
		index++;
		x[index] = lastValue;
		return x;
	}
	
	public static long[] fun_y(long[] t,int distanceY) {
		long[] y = new long[t.length];
		int index = 0;
		//初始化值
		int initValue = 299;
		//最后的稳定值  比初始值大20-30左右
		long lastValue = initValue+RandomNumberGenerator.generateRandomNumBetweenBounday(20, 30);
		//第一个值
		//y[index] = RandomNumberGenerator.generateRandomNumBetweenBounday(initValue, initValue+12);
		y[index] = 299;
		index = index+2;
		//从第三个值开始到倒数第8个
		
		for(;index<t.length-5;index++) {
			int param = RandomNumberGenerator.generateRandomNumBetweenBounday((int)lastValue-1, (int)lastValue+2);
			//y[index] = (long)(0.0002608*t[index]+param);
			y[index] = (long)(0.0002608*t[index]+param);
		}
		//第二个值
		y[1] = RandomNumberGenerator.generateRandomNumBetweenBounday((int)y[2]-5, (int)y[2]+3);
		//倒数8个值
		
		for(;index<t.length;index++) {
			y[index] = lastValue;
		}
		
		return y;
	}

}
