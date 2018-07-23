package com.utils.tradingIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.market.model.KLine;
/**
 * 
* @ClassName: BollIndicator
* @Description: 布林线指标
* BollIndicator(N,P) //param N:N日     P:倍数
* MID: MA(CLOSE,N)   //N日收盘价的算术平均数
* UPPER:MID+P*STD(CLOSE,N);  //STD 标准差(Standard Deviation)
* LOWER:MID-P*STD(CLOSE,N);
* @author: leisure
* @date: 2018年7月16日 上午10:28:43
 */
public class BollIndicator {
	
	/**
	 * 
	* @Title: calculateBoll
	* @Description: 
	* 日BOLL指标的计算过程
　　      1）计算MA
       MA=N日内的收盘价之和÷N
　　     2）计算标准差MD
       MD=平方根N日的（C－MA）的两次方之和除以N
　　    3）计算MB、UP、DN线
       MB=（N－1）日的MA
       UP=MB＋2×MD
       DN=MB－2×MD
	* @param klineList
	* @param period
	* @param multiple
	* @param decimalWidth
	* @return List<Map<String,String>>
	* @author leisure
	* @date 2018年7月16日上午10:50:06
	 */
	public static List<Map<String, String>> calculateBoll(List<KLine> klineList,final int period,
		 final Double multiple ,final int decimalWidth ){
		if(null==klineList || 0==klineList.size() ) {
			System.out.println("klineList is null or size is 0");
			return null;
		}
		List<Map<String, String>> bollList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		int lenth = klineList.size();
		
		Double maSum = 0.0;
		
		int index = 0;
		//"%.2f"
		String format = "%."+decimalWidth+"f";
		//计算index=0到index=peroid-2的收盘价数据之和
		for(;index<period-1;index++) {
			
			maSum += klineList.get(index).getClosePrice();
			map = new HashMap<String,String>();
			map.put("TIME", klineList.get(index).getFormatDate());
			map.put("CLOSE", String.format(format, klineList.get(index).getClosePrice()));
			map.put("MID", "0");
			map.put("UPPER","0");
			map.put("LOWER","0");
			bollList.add(map);
		}
		//Kline的数据量要多于period 
		if(lenth<period) {
			return bollList;
		}
		//当inde>=period时开始计算  并给出相应指标值
		Double MA = 0.0;
		Double UPPER = 0.0;
		Double LOWER = 0.0;
		Double MD = 0.0;
		
		System.out.println("初始化的index = "+index+"----maSum = "+maSum);
		while(index<lenth) {
			//加上当前项
			maSum +=  klineList.get(index).getClosePrice();
			MA = maSum/period;
			Double mdSum = 0.0;
			//由于每次循环得到的MA值都不同，这里不能靠累加,必须重新循环计算
			//自变量i:  index-period+1 <= i <= index
			for(int i=index;i>=(index-period+1);i--) {
				//CLOSE-MA的平方和 
				//这里注意 自变量是i
				mdSum += Math.pow(klineList.get(i).getClosePrice()-MA, 2);
			}
		
			//CLOSE-MA的标准差
			MD = Math.sqrt(mdSum/period);
			UPPER = MA + multiple*MD;
			LOWER = MA - multiple*MD;
			//System.out.println("index = "+index+"---"+"---mdSum = "+mdSum+"---maSum = "+maSum+"---multiple*MD = "+multiple*MD+"---MA = "+MA+"---MD = "+MD+"---UPPER = "+UPPER+"---LOWER = "+LOWER);
			map = new HashMap<String,String>();
		
			map.put("TIME", klineList.get(index).getFormatDate());
			map.put("CLOSE", String.format(format, klineList.get(index).getClosePrice()));
			map.put("MID", String.format(format,MA));
			map.put("UPPER",String.format(format,UPPER));
			map.put("LOWER",String.format(format,LOWER));
			bollList.add(map);
			//减去首项
			maSum -= klineList.get(index-period+1).getClosePrice();	
			index++;
		}
		
		return bollList;
	}

}
