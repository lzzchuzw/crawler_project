package com.math;
/**
 * 
* @ClassName: ExpectedProfitCalculator
* @Description: 计算期望的利润
* @author: leisure
* @date: 2018年6月1日 下午4:58:58
 */
public class ExpectedProfitCalculator {
	/**
	 * 挂单Coin
	 */
	private String orderCoin;
	/**
	 * 支付Coin
	 */
	private String paymentCoin;
	/**
	 * 交易平台A
	 */
	private String tradingPlatA;
	
	/**
	 * 交易平台A价格
	 */
	private Double priceA;
	
	/**
	 * 交易平台A的OrderCoin数量
	 */
	private Double orderCoinCountA;
	/**
	 * 交易平台A的paymentCoin数量
	 */
	private Double paymentCoinCountA;
	/**
	 * 交易平台A的交易费率
	 */
	private Double exchangeRateA;
	/**
	 * 交易平台B
	 */
	private String tradingPlatB;
	/**
	 * 交易平台B价格
	 */
	private Double priceB;
	/**
	 * 交易平台B的OrderCoin数量
	 */
	private Double orderCoinCountB;
	/**
	 * 交易平台B的paymentCoin数量
	 */
	private Double paymentCoinCountB;
	/**
	 * 交易平台B的交易费率
	 */
	private Double exchangeRateB;
	public String getOrderCoin() {
		return orderCoin;
	}
	public void setOrderCoin(String orderCoin) {
		this.orderCoin = orderCoin;
	}
	public String getPaymentCoin() {
		return paymentCoin;
	}
	public void setPaymentCoin(String paymentCoin) {
		this.paymentCoin = paymentCoin;
	}
	public String getTradingPlatA() {
		return tradingPlatA;
	}
	public void setTradingPlatA(String tradingPlatA) {
		this.tradingPlatA = tradingPlatA;
	}
	public Double getPriceA() {
		return priceA;
	}
	public void setPriceA(Double priceA) {
		this.priceA = priceA;
	}
	public Double getOrderCoinCountA() {
		return orderCoinCountA;
	}
	public void setOrderCoinCountA(Double orderCoinCountA) {
		this.orderCoinCountA = orderCoinCountA;
	}
	public Double getPaymentCoinCountA() {
		return paymentCoinCountA;
	}
	public void setPaymentCoinCountA(Double paymentCoinCountA) {
		this.paymentCoinCountA = paymentCoinCountA;
	}
	public Double getExchangeRateA() {
		return exchangeRateA;
	}
	public void setExchangeRateA(Double exchangeRateA) {
		this.exchangeRateA = exchangeRateA;
	}
	public String getTradingPlatB() {
		return tradingPlatB;
	}
	public void setTradingPlatB(String tradingPlatB) {
		this.tradingPlatB = tradingPlatB;
	}
	public Double getPriceB() {
		return priceB;
	}
	public void setPriceB(Double priceB) {
		this.priceB = priceB;
	}
	public Double getOrderCoinCountB() {
		return orderCoinCountB;
	}
	public void setOrderCoinCountB(Double orderCoinCountB) {
		this.orderCoinCountB = orderCoinCountB;
	}
	public Double getPaymentCoinCountB() {
		return paymentCoinCountB;
	}
	public void setPaymentCoinCountB(Double paymentCoinCountB) {
		this.paymentCoinCountB = paymentCoinCountB;
	}
	public Double getExchangeRateB() {
		return exchangeRateB;
	}
	public void setExchangeRateB(Double exchangeRateB) {
		this.exchangeRateB = exchangeRateB;
	}
	
	
	

}
