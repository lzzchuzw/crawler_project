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
@Table(name="paltform_symbol_relevance")
public class PlatformSymbolRelevance extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 405282843600217977L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 交易对名  ETH/USDT
	 */
	private String name;
	/**
	 * 对应的交易对名 
	 */
	private String correspondingName;
	/**
	 * 交易对所属交易平台Id
	 */
	private Integer tradingPlatformId;
	/**
	 * 对应于标准交易对的Id
	 */
	private Integer TradingAssetPairId;
	/**
	 * 在本交易所的排名
	 */
	private Integer ranking;
	/**
	 * 订单货币简单描述
	 */
	private String orderCoinDes;
	/**
	 * 当前价格
	 */
	private String price;
	/**
	 * 占比
	 */
	private String proportion;	
	/**
	 * 描述信息
	 */
	private String description;
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
	/*******************************************************************************************/
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="corresponding_name")
	public String getCorrespondingName() {
		return correspondingName;
	}

	public void setCorrespondingName(String correspondingName) {
		this.correspondingName = correspondingName;
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
	@Column(name="ranking")
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
	@Column(name="order_coin_des")
	public String getOrderCoinDes() {
		return orderCoinDes;
	}
	public void setOrderCoinDes(String orderCoinDes) {
		this.orderCoinDes = orderCoinDes;
	}
    @Column(name="price")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name="proportion")
	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
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
