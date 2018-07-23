package com.market.model;
/**
 * 
* @ClassName: KLineInfo
* @Description: K线信息类
* @author: leisure
* @date: 2018年6月13日 下午5:30:12
 */
public class KLine {
	/**
	 * 时间
	 */
	private String formatDate;
	
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
	 * 收盘价
	 */
	private Double closePrice;
	/**
	 * 涨跌幅(本次的收盘价-上次的收盘价)/上次的收盘价
	 */
	private String advanceDecline;
	//private String amplitude;
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
	
	
    /**********************************************************************************/
	public String getFormatDate() {
		return formatDate;
	}

	public void setFormatDate(String formatDate) {
		this.formatDate = formatDate;
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

	public Double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public String getAdvanceDecline() {
		return advanceDecline;
	}

	public void setAdvanceDecline(String advanceDecline) {
		this.advanceDecline = advanceDecline;
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

}
