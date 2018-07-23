package com.utils.tradingIndicator;

/**
 *  @ClassName: RSIIndicator
* @Description: RSI指标
*   //LC是Last Close Price 上一个收盘价   REF方法即为获取上一个收盘价
*   LC = REF(CLOSE, 1)
*   //SMA是
    RSI1 = SMA(MAX(CLOSE - LC, 0), N1, 1) / SMA(ABS(CLOSE - LC), N1, 1) * 100
    RSI2 = SMA(MAX(CLOSE - LC, 0), N2, 1) / SMA(ABS(CLOSE - LC), N2, 1) * 100
    RSI3 = SMA(MAX(CLOSE - LC, 0), N3, 1) / SMA(ABS(CLOSE - LC), N3, 1) * 100
* @author: leisure
* @date: 2018年7月12日 下午3:34:14
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.market.model.KLine;

public class RSIIndicator {

	// 短周期天数
	public static final int SHORT_PERIOD = 6;
	// 中周期天数
	public static final int MIDDLE_PERIOD = 12;
	// 长周期天数
	public static final int LONG_PERIOD = 24;

	public static List<Map<String, String>> calculateRSI(List<KLine> klineList, final int shortPeriod,
			final int middlePeriod, final int longPeriod, final int decimalWidth) {
		if ((klineList == null) || (klineList.size() == 0)) {
			System.out.println("klineList is null or size is 0");
			return null;
		}
		String format = "%." + decimalWidth + "f";
		List<Map<String, String>> rsiList = new ArrayList<Map<String, String>>();

		Double rsiShort = 0.0;
		Double rsiMiddle = 0.0;
		Double rsiLong = 0.0;
		// 分子
		Double smaShortUp = 0.0;
		Double smaMiddleUp = 0.0;
		Double smaLongUp = 0.0;
		// 分母
		Double smaShortDown = 0.0;
		Double smaMiddleDown = 0.0;
		Double smaLongDown = 0.0;
		for (int index = 1; index < klineList.size(); index++) {
			KLine currentPoint = (KLine) klineList.get(index);

			KLine lastPoint = (KLine) klineList.get(index - 1);

			Double minus = Double.valueOf(((KLine) klineList.get(index)).getClosePrice().doubleValue()
					- lastPoint.getClosePrice().doubleValue());

			currentPoint.setAdvanceDecline(String.format("%.2f",
					Double.valueOf(minus.doubleValue() / lastPoint.getClosePrice().doubleValue())));

			Double minusUp = Math.max(minus, 0);
			Double minusDown = Math.abs(minus);
			smaShortUp = ((shortPeriod - 1) * smaShortUp + minusUp) / shortPeriod;
			smaMiddleUp = ((middlePeriod - 1) * smaMiddleUp + minusUp) / middlePeriod;
			smaLongUp = ((longPeriod - 1) * smaLongUp + minusUp) / longPeriod;

			smaShortDown = ((shortPeriod - 1) * smaShortDown + minusDown) / shortPeriod;
			smaMiddleDown = ((middlePeriod - 1) * smaMiddleDown + minusDown) / middlePeriod;
			smaLongDown = ((longPeriod - 1) * smaLongDown + minusDown) / longPeriod;

			rsiShort = smaShortUp / smaShortDown * 100;
			rsiMiddle = smaMiddleUp / smaMiddleDown * 100;
			rsiLong = smaLongUp / smaLongDown * 100;

			/*
			 * System.out.println("inde = "+index+"---minusUp = "+minusUp+"---smaShortUp = "
			 * +smaShortUp+"---smaShortDown = "+smaShortDown
			 * +"---smaMiddleUp = "+smaMiddleUp+"---smaMiddleDown = "
			 * +smaMiddleDown+"---smaLongUp = "+smaLongUp);
			 */

			Map<String, String> map = new HashMap<String, String>();
			map.put("RSI" + shortPeriod, String.format(format, rsiShort));
			map.put("RSI" + middlePeriod, String.format(format, rsiMiddle));
			map.put("RSI" + longPeriod, String.format(format, rsiLong));
			map.put("TIME", currentPoint.getFormatDate());

			rsiList.add(map);
		}
		return rsiList;
	}

}
