package com.utils.math;

public class MarginCalculate {
	
	public static void main(String args[]) {
		calculateMargin(0.00032,0.00040090);
	}
	
	public static Double calculateMargin(Double ask,Double bid) {
		Double ret = 0.0;
		if(ask <= 0 || bid <= 0) {
			return ret;
		}
		ret = (bid - ask)/ask*100;
		System.out.println("askPrice = "+ask+"---bidPrice = "+bid+"----margin = "+ret);
		return ret;		
	}

}
