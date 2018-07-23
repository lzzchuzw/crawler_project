package com.market.model;

import java.util.Date;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;

public class MarketTickerInfo {
	
	/**
	 * 交易平台Id
	 */
	private Integer tradingPlatformId;
	/**
	 * 交易平台   币安网、火币网... and so on
	 */
	private String tradingPlatformName;
	/**
	 * 交易对Id
	 */
	private Integer tradingAssetPairId;
	
	/**
	 * 交易对   比如火币网 :btcusdt, bchbtc, rcneth ...
	 */
	private String tradingAssetPairName;
	/**
	 * 获取数据的时访问Url
	 */
	private String fetchDataUrl;
	/**
	 * 获取数据时使用的代理服务器
	 */
	private RequestConfig requestConfig;
	/**
	 * 从服务器返回数据的时间
	 */
	private Date serverDate;
	/**
	 * 从服务器返回的json数据
	 */
	private String responseDataString;
	/**
	 * 从服务器返回数据,用fastjson转换的map
	 */
	private Map<String,Object> responseDataMap;
	/**
	 * 开盘价
	 */
	private Double openPrice;
	/**
	 * 最高价
	 */
	private Double highPrice;
	/**
	 * 最低价
	 */
	private Double lowPrice;
	
	
	/**
	 * 最近成交价
	 */
	private Double lastPrice;
	/**
	 * 成交笔数
	 */
	private Double transactionCount;
	/**
	 * 成交量
	 */
	private Double transactionAmount;
	/**
	 * 成交额
	 */
	private Double transactionVolume;
	/**
	 * 买一价
	 */
	private Double bidPrice;
	/**
	 * 买一量
	 */
	private Double bidCount;
	/**
	 * 卖一价
	 */
	private Double askPrice;
	/**
	 * 卖一量
	 */
	private Double askCount;
	/**
	 * 解析ServerDate的正则
	 */
    private String parseServerDateRegex;
    /**
     * 解析开盘价的正则
     */
    private String parseOpenPriceRegex;
    /**
     * 解析最高价的正则
     */
    private String parseHighPriceRegex;
    /**
     * 解析最低价的正则
     */
    private String parseLowPriceRegex;
    /**
     * 解析最近成交价的正则
     */
    private String parseLastPriceRegex;
    /**
     * 解析成交笔数的正则
     */
    private String parseTransactionCountRegex;
    /**
     * 解析成交量的正则
     */
    private String parseTransactionAmountRegex;
    /**
     * 解析成交额的正则
     */
    private String parseTransactionVolumeRegex;
    /**
     * 解析买一价的正则
     */
    private String parseBidPriceRegex;
    /**
     * 解析买一量的正则
     */
    private String parseBidCountRegex;
    /**
     * 解析卖一价的正则
     */
    private String parseAskPriceRegex;
    /**
     * 解析卖一量的正则
     */
    private String parseAskCountRegex;
    /**
	 * 盈亏状态 -1 亏 0 持平 1 盈
	 */
	private Integer state;
	/**
	 * 收益差
	 */
	private Double priceSpread;
	/**
	 * (买一最高价 - 卖一最低价) / 卖一最低价    的百分比
	 */
	private Double margin;
  

	/**********************************************************************/
	public MarketTickerInfo() {
		this.bidPrice = Double.MIN_VALUE;
		this.askPrice = Double.MAX_VALUE;
	}
    public MarketTickerInfo(final String tradingPlatform,final String tradingAsset,final String fetchDataUrlPrefix) {
		this.tradingPlatformName = tradingPlatform;
		this.tradingAssetPairName = tradingAsset;
		this.fetchDataUrl = fetchDataUrlPrefix+tradingAsset;
		this.bidPrice = Double.MIN_VALUE;
		this.askPrice = Double.MAX_VALUE;
	}
	/**********************************************************************/
	
    public Integer getTradingPlatformId() {
		return tradingPlatformId;
	}
	public void setTradingPlatformId(Integer tradingPlatformId) {
		this.tradingPlatformId = tradingPlatformId;
	}
	public String getTradingPlatformName() {
		return tradingPlatformName;
	}
	public void setTradingPlatformName(String tradingPlatformName) {
		this.tradingPlatformName = tradingPlatformName;
	}
	public Integer getTradingAssetPairId() {
		return tradingAssetPairId;
	}
	public void setTradingAssetPairId(Integer tradingAssetPairId) {
		this.tradingAssetPairId = tradingAssetPairId;
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
	public RequestConfig getRequestConfig() {
		return requestConfig;
	}
	public void setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
	}
	public Date getServerDate() {
		return serverDate;
	}
	public void setServerDate(Date serverDate) {
		this.serverDate = serverDate;
	}
	public String getResponseDataString() {
		return responseDataString;
	}
	public void setResponseDataString(String responseDataString) {
		this.responseDataString = responseDataString;
	}
	public Map<String, Object> getResponseDataMap() {
		return responseDataMap;
	}
	public void setResponseDataMap(Map<String, Object> responseDataMap) {
		this.responseDataMap = responseDataMap;
	}
	public Double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}
	public Double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}
	public Double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}
	public Double getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}
	public Double getTransactionCount() {
		return transactionCount;
	}
	public void setTransactionCount(Double transactionCount) {
		this.transactionCount = transactionCount;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public Double getTransactionVolume() {
		return transactionVolume;
	}
	public void setTransactionVolume(Double transactionVolume) {
		this.transactionVolume = transactionVolume;
	}
	public Double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}
	public Double getBidCount() {
		return bidCount;
	}
	public void setBidCount(Double bidCount) {
		this.bidCount = bidCount;
	}
	public Double getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}
	public Double getAskCount() {
		return askCount;
	}
	public void setAskCount(Double askCount) {
		this.askCount = askCount;
	}
	public String getParseServerDateRegex() {
		return parseServerDateRegex;
	}
	public void setParseServerDateRegex(String parseServerDateRegex) {
		this.parseServerDateRegex = parseServerDateRegex;
	}
	public String getParseOpenPriceRegex() {
		return parseOpenPriceRegex;
	}
	public void setParseOpenPriceRegex(String parseOpenPriceRegex) {
		this.parseOpenPriceRegex = parseOpenPriceRegex;
	}
	public String getParseHighPriceRegex() {
		return parseHighPriceRegex;
	}
	public void setParseHighPriceRegex(String parseHighPriceRegex) {
		this.parseHighPriceRegex = parseHighPriceRegex;
	}
	public String getParseLowPriceRegex() {
		return parseLowPriceRegex;
	}
	public void setParseLowPriceRegex(String parseLowPriceRegex) {
		this.parseLowPriceRegex = parseLowPriceRegex;
	}
	public String getParseLastPriceRegex() {
		return parseLastPriceRegex;
	}
	public void setParseLastPriceRegex(String parseLastPriceRegex) {
		this.parseLastPriceRegex = parseLastPriceRegex;
	}
	public String getParseTransactionCountRegex() {
		return parseTransactionCountRegex;
	}
	public void setParseTransactionCountRegex(String parseTransactionCountRegex) {
		this.parseTransactionCountRegex = parseTransactionCountRegex;
	}
	public String getParseTransactionAmountRegex() {
		return parseTransactionAmountRegex;
	}
	public void setParseTransactionAmountRegex(String parseTransactionAmountRegex) {
		this.parseTransactionAmountRegex = parseTransactionAmountRegex;
	}
	public String getParseTransactionVolumeRegex() {
		return parseTransactionVolumeRegex;
	}
	public void setParseTransactionVolumeRegex(String parseTransactionVolumeRegex) {
		this.parseTransactionVolumeRegex = parseTransactionVolumeRegex;
	}
	public String getParseBidPriceRegex() {
		return parseBidPriceRegex;
	}
	public void setParseBidPriceRegex(String parseBidPriceRegex) {
		this.parseBidPriceRegex = parseBidPriceRegex;
	}
	public String getParseBidCountRegex() {
		return parseBidCountRegex;
	}
	public void setParseBidCountRegex(String parseBidCountRegex) {
		this.parseBidCountRegex = parseBidCountRegex;
	}
	public String getParseAskPriceRegex() {
		return parseAskPriceRegex;
	}
	public void setParseAskPriceRegex(String parseAskPriceRegex) {
		this.parseAskPriceRegex = parseAskPriceRegex;
	}
	public String getParseAskCountRegex() {
		return parseAskCountRegex;
	}
	public void setParseAskCountRegex(String parseAskCountRegex) {
		this.parseAskCountRegex = parseAskCountRegex;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Double getPriceSpread() {
		return priceSpread;
	}
	public void setPriceSpread(Double priceSpread) {
		this.priceSpread = priceSpread;
	}
	public Double getMargin() {
		return margin;
	}
	public void setMargin(Double margin) {
		this.margin = margin;
	}

}
