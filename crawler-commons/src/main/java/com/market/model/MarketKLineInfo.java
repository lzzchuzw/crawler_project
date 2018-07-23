package com.market.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
* @ClassName: MarketKLineInfo
* @Description: 用于存储每次从交易平台上获取的KLine list数据
* @author: leisure
* @date: 2018年6月14日 上午9:14:27
 */
public class MarketKLineInfo {
	
	/**
	 * 交易平台   币安网、火币网... and so on
	 */
	private String tradingPlatformName;
	/**
	 * 交易对   比如火币网 :btcusdt, bchbtc, rcneth ...
	 */
	private String tradingAssetPairName;
	/**
	 * 获取数据的时访问Url
	 */
	private String fetchDataUrl;
	/**
	 * 从服务器返回的json数据
	 */
	private String responseDataString;
	
	private List<KLine> klineList;
	
	/****************************************************************************/
	public MarketKLineInfo(String tradingPlatformName,String tradingAssetPairName) {
		this.tradingPlatformName = tradingPlatformName;
		this.tradingAssetPairName = tradingAssetPairName;
		this.klineList = new ArrayList<KLine>();
	}
    /********************************************************************************/
	public String getTradingPlatformName() {
		return tradingPlatformName;
	}

	public void setTradingPlatformName(String tradingPlatformName) {
		this.tradingPlatformName = tradingPlatformName;
	}

	public String getTradingAssetPairName() {
		return tradingAssetPairName;
	}

	public void setTradingAssetPairName(String tradingAssetPairName) {
		this.tradingAssetPairName = tradingAssetPairName;
	}

	public String getFetchDataUrl() {
		return fetchDataUrl;
	}

	public void setFetchDataUrl(String fetchDataUrl) {
		this.fetchDataUrl = fetchDataUrl;
	}

	public String getResponseDataString() {
		return responseDataString;
	}

	public void setResponseDataString(String responseDataString) {
		this.responseDataString = responseDataString;
	}

	public List<KLine> getKlineList() {
		return klineList;
	}

	public void setKlineList(List<KLine> klineList) {
		this.klineList = klineList;
	}

}
