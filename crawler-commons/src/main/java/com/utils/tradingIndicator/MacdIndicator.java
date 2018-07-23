package com.utils.tradingIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.market.model.KLine;
import com.market.model.MarketKLineInfo;

/**
 * 
 * @ClassName: MacdIndicator
 * @Description: 计算MACD DIFF DEA BAR(MACD) 初始值: List<double> closedPrice 令: k =
 *               2.0/(period+1.0); 第N日收盘价 为 value(N) = list.get(N)
 *               EMA(N,value(N),period) =
 *               EMA(N-1,value(N-1),period)*11/13+value(N)*2/13 =
 *               value(N)*k+EMA(N-1,value(N-1)*(1-k); DIFF = EMA(N,value(N),12)
 *               - EMA(N,value(N),26) DEA(N) = EMA(N,DIFF(N),9)
 * @author: leisure
 * @date: 2018年6月14日 下午2:19:15
 */
public class MacdIndicator {
	// 短周期天数
	public static final int SHORT_PERIOD = 12;
	// 长周期天数
	public static final int LONG_PERIOD = 26;
	// m天数 -- 计算 DEA的时候用的
	public static final int M_DAYS = 9;
    
	
	
	/**
	 * 
	* @Title: calculateMACD
	* @Description: 采用的(12,26,9)计算MACD值
	* @param closedPriceList
	* @return List<HashMap<String,Double>>
	* @author leisure
	* @date 2018年6月14日下午4:10:55
	 */
	public static List<HashMap<String, Double>> calculateMACD(final List<Double> closedPriceList){
		return calculateMACD(closedPriceList,SHORT_PERIOD,LONG_PERIOD,M_DAYS);
	}
	/**
	 * 
	* @Title: calculateMACD
	* @Description: 根据收盘价list 计算出相应的MACD值  参照"同花顺"的MACDFS指标展示,计算返回的是MACD DIFF 和DEA的值
	* @param closedPriceList
	* @param shortPeriod
	* @param longPeriod
	* @param mDays
	* @return List<HashMap<String,Double>>
	* @author leisure
	* @date 2018年6月14日下午4:07:55
	 */
	public static List<HashMap<String, Double>> calculateMACD(final List<Double> closedPriceList, final int shortPeriod,
			final int longPeriod, final int mDays) {
		if (null == closedPriceList || 0 == closedPriceList.size()) {
			return null;
		}
		List<HashMap<String, Double>> macdList = new ArrayList<HashMap<String, Double>>();
		HashMap<String, Double> macdMap = new HashMap<String, Double>();
		// 新股上市,开盘第一天 DIFF DEA MACD均为0, EMA(12)和EMA(26)均为第一天的收盘价
		Double ema_12 = closedPriceList.get(0);
		Double ema_26 = closedPriceList.get(0);
		Double diff = 0.0;
		Double dea = 0.0;
		Double macd = 0.0;
		
		macdMap.put("MACD", macd);
		macdMap.put("DIFF", diff);
		macdMap.put("DEA", dea);
		macdList.add(macdMap);
		
		for(int index = 1;index<closedPriceList.size();index++) {
			ema_12 = calculateEMA(ema_12, closedPriceList.get(index), shortPeriod);
			ema_26 = calculateEMA(ema_26, closedPriceList.get(index), longPeriod);
			diff = ema_12 - ema_26;
			dea = calculateEMA(dea, diff, mDays);
			macd = 2 * (diff - dea);
			macdMap = new HashMap<String, Double>();
			macdMap.put("MACD", macd);
			macdMap.put("DIFF", diff);
			macdMap.put("DEA", dea);
			macdList.add(macdMap);
			//System.out.println("ema_12 = "+ema_12+"---ema_26 = "+ema_26+"---diff");
		}
		
		return macdList;

	}
	
	
    /**
     * 
    * @Title: calculateMACD
    * @Description: 由List<KLine>生成相应的MACD指标
    * @param klineList
    * @param shortPeriod
    * @param longPeriod
    * @param mDays
    * @param decimalWidth  保留小数位宽度
    * @return List<Map<String,String>>
    * @author leisure
    * @date 2018年6月15日上午10:29:31
     */
	public static List<Map<String, String>> calculateMACD(List<KLine> klineList,final int shortPeriod,
			final int longPeriod, final int mDays,final int decimalWidth ){
		
		if(null==klineList || 0==klineList.size()) {
			System.out.println("klineList is null or size is 0");
			return null;
		}
		//"%.2f"
		String format = "%."+decimalWidth+"f";
		List<Map<String, String>> macdList = new ArrayList<Map<String, String>>();
		Map<String, String> macdMap = new HashMap<String,String>();
		int index = 0;
		KLine kline = klineList.get(index);
		String time = kline.getFormatDate();
		Double ema_12 = kline.getClosePrice();
		Double ema_26 = ema_12;
		Double diff = 0.0;
		Double dea = 0.0;
		Double macd = 0.0;
				
		macdMap.put("TIME", time);
		macdMap.put("MACD", String.format(format,macd));
		macdMap.put("DIFF", String.format(format,diff));
		macdMap.put("DEA", String.format(format,dea));
		macdList.add(macdMap);
		while(++index<klineList.size()) {
			macdMap = new HashMap<String,String>();
			kline = klineList.get(index);
			//获取数据,计算指标
			time = kline.getFormatDate();
			ema_12 = calculateEMA(ema_12, kline.getClosePrice(), shortPeriod);
			ema_26 = calculateEMA(ema_26, kline.getClosePrice(), longPeriod);
			diff = ema_12 - ema_26;
			dea = calculateEMA(dea, diff, mDays);
			macd = 2 * (diff - dea);
			//格式化保留两位小数 并四舍五入
			macdMap.put("TIME", time);
			macdMap.put("MACD", String.format(format,macd));
			macdMap.put("DIFF", String.format(format,diff));
			macdMap.put("DEA", String.format(format,dea));
			macdList.add(macdMap);
		}
		return macdList;	
	}

	/**
	 * 
	 * @Title: calculateEMA
	 * @Description: 计算当天的EMA
	 * @param ema
	 *            前一天的EMA
	 * @param closedPrice
	 *            今天的收盘价
	 * @param period
	 *            周期 常见12/26/9
	 * @return Double
	 * @author leisure
	 * @date 2018年6月14日下午4:05:59
	 */
	public static Double calculateEMA(Double ema, Double closedPrice, int period) {
       // System.out.println("o_ema = "+ema);
		Double k = 2.0 / (period + 1.0);

		ema = closedPrice * k + ema * (1 - k);
		//System.out.println("n_ema = "+ema);
		return ema;

	}
	
	

	
}
