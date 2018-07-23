package com.utils.tradingIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.market.model.KLine;

/**
 * 
* @ClassName: MovingAverageIndicator
* @Description: 移动平均线指标   参照同花顺软件 MA5 MA10 MA20 MA30 MA60 代表5日 10日 20日 30日 60日均线
* 按照计算方法一般分为
* @author: leisure
* @date: 2018年7月6日 下午4:13:20
 */
public class MovingAverageIndicator {
	
	public static List<Double> calculateMAList(List<KLine> klineList,int period) {
		if(null==klineList || klineList.size()<period) {
			return null;
		}
		List<Double> periodMAList = new ArrayList<Double>(klineList.size()-period+1);
		int index = 0;
		Double sum = 0.0;
		//计算index=0到index=peroid-2的数据之和
		do {
			sum += klineList.get(index).getClosePrice();
			index ++;
		}while(index<period-1);
		for(;index<klineList.size();index++) {
			sum += klineList.get(index).getClosePrice();
			Double periodMA = sum/period;
			periodMAList.add(periodMA);
			sum -= klineList.get(index-period+1).getClosePrice();
		}
		return periodMAList;
	}
	/**
	 * 
	* @Title: calculateMAListMap
	* @Description: 计算N日均线  从第N日开始给出数值
	* @param klineList
	* @param period
	* @param decimalWidth
	* @return List<Map<String,String>>
	* @author leisure
	* @date 2018年7月16日上午11:06:27
	 */
	public static List<Map<String, String>> calculateMAListMap(List<KLine> klineList,final int period,final int decimalWidth) {
		if(null==klineList || klineList.size()<period) {
			return null;
		}
		List<Map<String,String>> periodMAListMap = new ArrayList<Map<String,String>>(klineList.size()-period+1);
		
		//"%.2f"
		String format = "%."+decimalWidth+"f";
		int index = 0;
		Double sum = 0.0;
		//计算index=0到index=peroid-2的数据之和
		do {
			sum += klineList.get(index).getClosePrice();
			System.out.println("index = "+index+"---sum = "+sum);
			index ++;			
		}while(index<period-1);
		
		System.out.println("初始化的index = "+index+"----sum = "+sum);
		
		for(;index<klineList.size();index++) {
			sum += klineList.get(index).getClosePrice();
			Double periodMA = sum/period;
			Map<String,String> periodMaMap = new HashMap<String,String>();
			
			periodMaMap.put("TIME", klineList.get(index).getFormatDate());
			periodMaMap.put("CLOSEPRICE", String.format(format, klineList.get(index).getClosePrice()));
			periodMaMap.put("MA"+period,String.format(format,periodMA));
			periodMAListMap.add(periodMaMap);
			sum -= klineList.get(index-period+1).getClosePrice();
		}
		return periodMAListMap;
	}
	
	/**
	   * 
	  * @Title: calculateSMA
	  * @Description: 
	  * SMA(X,N,M)，求X的N日移动平均，M为权重。算法：若Y=SMA(X,N,M) 则 Y=(M*X+(N-M)*Y')/N，其中Y'表示上一周期Y值，N必须大于M。
	  * @param dataList
	  * @param period  周期
	  * @param weight  当日权重
	  * @param absFlag 1 取当前值的绝对值
	  * @author leisure
	  * @date 2018年7月12日下午3:57:14
	   */
	  public static List<Double> calculateSMA(List<Double> dataList, int period, Double weight, boolean absFlag) {
		  if ((dataList == null) || (dataList.size() < period)) {
		      return null;
		   }
		  int size = dataList.size();
		 
		  List<Double> smaList = new ArrayList<Double>(dataList.size());
		 
		  Double sma = 0.0;
		  //step1  sma = dataList.get(0);
		  //step2  sma = (period-weight)*sma+dataList.get(index);
		  for(int index=0;index<size;index++) {
			  Double data = dataList.get(index);
			  if(absFlag) {
				  data = Math.abs(data);
			  }else {
				  data = Math.max(data, 0);
			  }
			  sma = ((period-weight)*sma+data)/period;
			  smaList.add(sma);
		  }
		  return smaList;
		  
	  }

}
