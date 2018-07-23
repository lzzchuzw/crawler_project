package com.market.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.utils.hibernate.BaseEntity;
/**
 * 
* @ClassName: TradingPlatform
* @Description: 交易平台
* @author: leisure
* @date: 2018年5月25日 上午9:02:20
 */
@Entity
@Table(name="trading_platform")
public class TradingPlatform extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4624055330211560769L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 排名
	 */
	private Integer ranking;
	/**
	 * 交易所名 Binance
	 */
	private String name;
	/**
	 * 别名
	 */
	private String alias;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 交易对数目
	 */
	private Integer symbolCount;
	/**
	 * 支持的交易类型 1:支持现货
	 *           2:支持期货
	 *          3:支持法币交易或OTC交易
	 *  保存时
	 *  同时支持三种交易类型:{1,2,3}  
	 *  同时支持后面两种交易类{2,3}
	 */
	private String supportTradingString;
	
	/**
	 * 官网
	 */
	private String officialWebsite;
	 /**
     * 交易所的简介
     */
	private String description;
	/**
	 * 交易所发行的货币Id  不是所有交易所都发行的 这一项可能为空
	 */
	private String virtualCoinId;
	/**
	 * 交易对是否大写  0:小写  1:大写
	 */
	private Integer isUpperCase;
	/**
	 * 交易对之间的连接线字符串  btc_usdt  BTC-USD
	 */
	private String connectingLineString;
	/**
	 * 交易对使用的usd还是usdt 0:usdt 1:usd 
	 */
	private Integer isUsd;
	/**
	 * 获取Ticker数据的类型
	 * 0:默认值 既可以一次性获取全部的Ticker值,也可以单独获取某指定交易对的值
	 * 1:不能一次性获取全部Ticker值,只能每次单独获取某指定交易对的Ticker值
	 * 2:不能一次性获取全部Ticker值,只能每次单独获取某指定交易对的Ticker值,请求的url分prefix和postfix两部分组成
	 */
	private Integer fetchTickerType;
	/**
	 * 交易对对照表  比如Binance  eth_btc 对应为ETHBTC
	 */
	private String tradingAssetComparison;
	/**
	 * 交易所获取全部Currency
	 */
	private String fetchAllCurrencyUrl;
	/**
	 * 交易所获取所有交易对的url
	 */
	private String fetchAllSymbolUrl;
	/**
	 * 一次获取交易所全部交易对Ticker信息的url;
	 * 需要平台该API支持,默认值null
	 */
	private String fetchAllTickerUrl;
	/**
	 * 一次获取一个交易对Ticker信息的url前缀
	 */
	private String fetchTickerUrlPrefix;
	/**
	 * 一次获取一个交易对Ticker信息的url后缀
	 */
	private String fetchTickerUrlPostfix;
	
	/**
	 * 解析ticker数组的正则
	 */
	private String parseAllTickerRegex;
	/**
	 * 解析一个单独交易对的Ticker的正则,默认为null
	 * 需要平台提供一次获取交易所全部交易对Ticker信息的url
	 * 二者配合使用
	 */
	private String parseSingleTickerRegex;
	/**
	 * 解析ServerDate的正则
	 */
    private String parseServerDateRegex;
    /**
     * 解析交易对的正则
     */
    private String parseSymbolRegex;
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
     * 获取KLine的url
     */
    private String fetchKLineUrl;
    /**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 最后一次修改时间
	 */
	private Date gmtModified;
	
	
	/*********************************************************/
	public TradingPlatform() {
		
	}
	/*********************************************************/
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="ranking")
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="alias")
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@Column(name="country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name="symbol_count")
	public Integer getSymbolCount() {
		return symbolCount;
	}
	public void setSymbolCount(Integer symbolCount) {
		this.symbolCount = symbolCount;
	}
	@Column(name="support_trading_string")
	public String getSupportTradingString() {
		return supportTradingString;
	}
	public void setSupportTradingString(String supportTradingString) {
		this.supportTradingString = supportTradingString;
	}
	@Column(name="virtual_coin_id")
	public String getVirtualCoinId() {
		return virtualCoinId;
	}
	public void setVirtualCoinId(String virtualCoinId) {
		this.virtualCoinId = virtualCoinId;
	}
	@Column(name="is_upper_case")
	public Integer getIsUpperCase() {
		return isUpperCase;
	}
	public void setIsUpperCase(Integer isUpperCase) {
		this.isUpperCase = isUpperCase;
	}
	@Column(name="connecting_line_string")
	public String getConnectingLineString() {
		return connectingLineString;
	}
	public void setConnectingLineString(String connectingLineString) {
		this.connectingLineString = connectingLineString;
	}
	@Column(name="is_usd")
	public Integer getIsUsd() {
		return isUsd;
	}
	public void setIsUsd(Integer isUsd) {
		this.isUsd = isUsd;
	}
	@Column(name="fetch_ticker_type")
	public Integer getFetchTickerType() {
		return fetchTickerType;
	}
	public void setFetchTickerType(Integer fetchTickerType) {
		this.fetchTickerType = fetchTickerType;
	}
	
	@Column(name="official_web_site")
	public String getOfficialWebsite() {
		return officialWebsite;
	}
	public void setOfficialWebsite(String officialWebsite) {
		this.officialWebsite = officialWebsite;
	}
	@Column(name="trading_asset_comparison")
	public String getTradingAssetComparison() {
		return tradingAssetComparison;
	}
	public void setTradingAssetComparison(String tradingAssetComparison) {
		this.tradingAssetComparison = tradingAssetComparison;
	}
	@Column(name="fetch_all_currency_url")
	public String getFetchAllCurrencyUrl() {
		return fetchAllCurrencyUrl;
	}
	public void setFetchAllCurrencyUrl(String fetchAllCurrencyUrl) {
		this.fetchAllCurrencyUrl = fetchAllCurrencyUrl;
	}
	@Column(name="fetch_all_symbol_url")
	public String getFetchAllSymbolUrl() {
		return fetchAllSymbolUrl;
	}
	public void setFetchAllSymbolUrl(String fetchAllSymbolUrl) {
		this.fetchAllSymbolUrl = fetchAllSymbolUrl;
	}
	
	@Column(name="fetch_all_ticker_url")
	public String getFetchAllTickerUrl() {
		return fetchAllTickerUrl;
	}
	public void setFetchAllTickerUrl(String fetchAllTickerUrl) {
		this.fetchAllTickerUrl = fetchAllTickerUrl;
	}
	@Column(name="fetch_ticker_url_prefix")
	public String getFetchTickerUrlPrefix() {
		return fetchTickerUrlPrefix;
	}
	public void setFetchTickerUrlPrefix(String fetchTickerUrlPrefix) {
		this.fetchTickerUrlPrefix = fetchTickerUrlPrefix;
	}
	@Column(name="fetch_ticker_url_postfix")
	public String getFetchTickerUrlPostfix() {
		return fetchTickerUrlPostfix;
	}
	public void setFetchTickerUrlPostfix(String fetchTickerUrlPostfix) {
		this.fetchTickerUrlPostfix = fetchTickerUrlPostfix;
	}
	@Column(name="parse_all_ticker_regex")
	public String getParseAllTickerRegex() {
		return parseAllTickerRegex;
	}
	public void setParseAllTickerRegex(String parseAllTickerRegex) {
		this.parseAllTickerRegex = parseAllTickerRegex;
	}
	@Column(name="parse_single_ticker_regex")
	public String getParseSingleTickerRegex() {
		return parseSingleTickerRegex;
	}
	public void setParseSingleTickerRegex(String parseSingleTickerRegex) {
		this.parseSingleTickerRegex = parseSingleTickerRegex;
	}
	@Column(name="parse_server_date_regex")
	public String getParseServerDateRegex() {
		return parseServerDateRegex;
	}
	public void setParseServerDateRegex(String parseServerDateRegex) {
		this.parseServerDateRegex = parseServerDateRegex;
	}
	@Column(name="paser_symbol_regex")
	public String getParseSymbolRegex() {
		return parseSymbolRegex;
	}
	public void setParseSymbolRegex(String parseSymbolRegex) {
		this.parseSymbolRegex = parseSymbolRegex;
	}
	@Column(name="parse_open_price_regex")
	public String getParseOpenPriceRegex() {
		return parseOpenPriceRegex;
	}
	public void setParseOpenPriceRegex(String parseOpenPriceRegex) {
		this.parseOpenPriceRegex = parseOpenPriceRegex;
	}
	@Column(name="parese_high_price_regex")
	public String getParseHighPriceRegex() {
		return parseHighPriceRegex;
	}
	public void setParseHighPriceRegex(String parseHighPriceRegex) {
		this.parseHighPriceRegex = parseHighPriceRegex;
	}
	@Column(name="parse_low_price_regex")
	public String getParseLowPriceRegex() {
		return parseLowPriceRegex;
	}
	public void setParseLowPriceRegex(String parseLowPriceRegex) {
		this.parseLowPriceRegex = parseLowPriceRegex;
	}
	@Column(name="parse_last_price_regex")
	public String getParseLastPriceRegex() {
		return parseLastPriceRegex;
	}
	public void setParseLastPriceRegex(String parseLastPriceRegex) {
		this.parseLastPriceRegex = parseLastPriceRegex;
	}
	@Column(name="parse_transaction_count_regex")
	public String getParseTransactionCountRegex() {
		return parseTransactionCountRegex;
	}
	public void setParseTransactionCountRegex(String parseTransactionCountRegex) {
		this.parseTransactionCountRegex = parseTransactionCountRegex;
	}
	@Column(name="parse_transaction_amount_regex")
	public String getParseTransactionAmountRegex() {
		return parseTransactionAmountRegex;
	}
	public void setParseTransactionAmountRegex(String parseTransactionAmountRegex) {
		this.parseTransactionAmountRegex = parseTransactionAmountRegex;
	}
	@Column(name="parse_transaction_volume_regex")
	public String getParseTransactionVolumeRegex() {
		return parseTransactionVolumeRegex;
	}
	public void setParseTransactionVolumeRegex(String parseTransactionVolumeRegex) {
		this.parseTransactionVolumeRegex = parseTransactionVolumeRegex;
	}
	@Column(name="parse_bid_price_regex")
	public String getParseBidPriceRegex() {
		return parseBidPriceRegex;
	}
	public void setParseBidPriceRegex(String parseBidPriceRegex) {
		this.parseBidPriceRegex = parseBidPriceRegex;
	}
	@Column(name="parse_bid_count_regex")
	public String getParseBidCountRegex() {
		return parseBidCountRegex;
	}
	public void setParseBidCountRegex(String parseBidCountRegex) {
		this.parseBidCountRegex = parseBidCountRegex;
	}
	@Column(name="parse_ask_price_regex")
	public String getParseAskPriceRegex() {
		return parseAskPriceRegex;
	}
	public void setParseAskPriceRegex(String parseAskPriceRegex) {
		this.parseAskPriceRegex = parseAskPriceRegex;
	}
	@Column(name="parse_ask_count_regex")
	public String getParseAskCountRegex() {
		return parseAskCountRegex;
	}
	public void setParseAskCountRegex(String parseAskCountRegex) {
		this.parseAskCountRegex = parseAskCountRegex;
	}
	@Column(name="fetch_kline_url")
	public String getFetchKLineUrl() {
		return fetchKLineUrl;
	}
	public void setFetchKLineUrl(String fetchKLineUrl) {
		this.fetchKLineUrl = fetchKLineUrl;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
