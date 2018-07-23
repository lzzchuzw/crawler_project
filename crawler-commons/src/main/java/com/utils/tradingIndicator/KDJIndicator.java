package com.utils.tradingIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.market.model.KLine;

public class KDJIndicator {
	
	public static int PERIOD = 9;
	
	public static int PARAM_A = 3;
	
	public static int PARAM_B = 3;
	
	public static List<Map<String, String>> calculateKDJ(List<KLine> klineList,final int period,
			final int paramA, final int paramB,final int decimalWidth ){
		if(null==klineList || 0==klineList.size()) {
			return null;
		}
		
		List<Map<String, String>> kdjList = new ArrayList<Map<String, String>>();
		Map<String, String> kdjMap = new HashMap<String, String>();
		int index = 0;
		//"%.2f"
		String format = "%."+decimalWidth+"f";
		KLine kline = klineList.get(index);
		String time = kline.getFormatDate();
		//初始化
		Double RSV = 0.0;
		Double K = 90.91;
		Double D = 90.91;
		Double J = 90.91;
		
		kdjMap.put("TIME", time);
		kdjMap.put("RSV", String.format(format, RSV));
		kdjMap.put("K", String.format(format, K));
		kdjMap.put("D", String.format(format, D));
		kdjMap.put("J", String.format(format, J));
		kdjList.add(kdjMap);
		while(++index<klineList.size()) {
			kdjMap = new HashMap<String, String>();
			kline = klineList.get(index);
			//获取数据,计算指标
			time = kline.getFormatDate();
			//
			List<KLine> sub_klineList = klineList.subList(0, index+1);
			if(index>=period) {
				sub_klineList = klineList.subList(index+1-period, index+1);
			}
			RSV = calculateRSV(sub_klineList,period);
			K = (2.0*K+1.0*RSV)/3.0;
			D = (2.0*D+1.0*K)/3.0;
			J = 3.0*K-2.0*D;
			kdjMap.put("TIME", time);
			kdjMap.put("RSV", String.format(format, RSV));
			kdjMap.put("K", String.format(format, K));
			kdjMap.put("D", String.format(format, D));
			kdjMap.put("J", String.format(format, J));
			kdjList.add(kdjMap);
		}
		
		return kdjList;
	}
	
	public static Double calculateRSV(List<KLine> klineList, int period) {
		if(null==klineList || 0==klineList.size()) {
			System.out.println("calculateRSV -- param is error");
			return null;
		}
		System.out.println("calculateRSV---klineList.size = "+klineList.size()+"--period = "+period);
		//period周期内的最低价
		Double low = klineList.get(0).getLowPrice();
		//period周期内的最高价
		Double high = klineList.get(0).getHighPrice();
		
		Double RSV = 0.0;
		
		int count = klineList.size();
		if(count<period) {
			period = count ;
		}
		System.out.println("count = "+count+"---period = "+period);
		for(int i=1;i<period;i++) {
			KLine kline  = klineList.get(i);
			if(null==kline) {
				System.out.println("get kline is null");
			}
			if(kline.getLowPrice()<low) {
				low = kline.getLowPrice();
			}
			if(kline.getHighPrice()>high) {
				high = kline.getHighPrice();
			}
		}
		
		KLine kline  = klineList.get(count-1);
		System.out.println("time = "+kline.getFormatDate()+"---closePrice = "+kline.getClosePrice());
		
		RSV = (klineList.get(count-1).getClosePrice()-low)/(high-low)*100;
		
		System.out.println("low = "+low+"---high = "+high+"---RSV = "+RSV);
		return RSV;
	}
	
	
	
	
	

}
