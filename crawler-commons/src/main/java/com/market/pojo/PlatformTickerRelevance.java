package com.market.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.utils.hibernate.BaseEntity;
@Entity
@Table(name="platform_ticker_relevance")
public class PlatformTickerRelevance extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9003035722997989300L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 所属平台Id
	 */
	private Integer tradingPlatformId;
	/**
	 * 对应的统一交易对的Id
	 */
	private Integer TradingAssetPairId;
	/**
	 * 返回数据的服务器时间
	 */
	private Date serverDate;
	/**
	 * 交易对
	 */
	private String symbol;
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
	 * 最近价
	 */
	private Double lastPrice;
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
     * 备注
     */
	private String remark;
	/**
	 * 生成时间
	 */
	private Date gmtCreate;
	/**
	 * 最后修改时间
	 */
	private Date gmtModified;
	/*****************************************************************/
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="trading_platform_id")
	public Integer getTradingPlatformId() {
		return tradingPlatformId;
	}
	public void setTradingPlatformId(Integer tradingPlatformId) {
		this.tradingPlatformId = tradingPlatformId;
	}
	@Column(name="trading_asset_pair_id")
	public Integer getTradingAssetPairId() {
		return TradingAssetPairId;
	}
	public void setTradingAssetPairId(Integer tradingAssetPairId) {
		TradingAssetPairId = tradingAssetPairId;
	}
	@Column(name="server_date")
	public Date getServerDate() {
		return serverDate;
	}
	public void setServerDate(Date serverDate) {
		this.serverDate = serverDate;
	}
	@Column(name="symbol")
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	@Column(name="open_price",length=16,precision=8)
	public Double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(Double openPrice) {
		this.openPrice = openPrice;
	}
	@Column(name="high_price",length=16,precision=8)
	public Double getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}
	@Column(name="low_price",length=16,precision=8)
	public Double getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}
	@Column(name="last_price",length=16,precision=8)
	public Double getLastPrice() {
		return lastPrice;
	}
	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}
	@Column(name="bid_price",length=16,precision=8)
	public Double getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}
	@Column(name="bid_count",length=16,precision=8)
	public Double getBidCount() {
		return bidCount;
	}
	public void setBidCount(Double bidCount) {
		this.bidCount = bidCount;
	}
	@Column(name="ask_price",length=16,precision=8)
	public Double getAskPrice() {
		return askPrice;
	}
	public void setAskPrice(Double askPrice) {
		this.askPrice = askPrice;
	}
	@Column(name="ask_count",length=16,precision=8)
	public Double getAskCount() {
		return askCount;
	}
	public void setAskCount(Double askCount) {
		this.askCount = askCount;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="gmt_create")
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	@Column(name="gmt_modified")
	public Date getGmtModified() {
		return gmtModified;
	}
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	
}
