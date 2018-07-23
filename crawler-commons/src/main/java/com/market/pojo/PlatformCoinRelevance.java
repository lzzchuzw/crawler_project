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
* @ClassName: PlatformCoinRelevance
* @Description: 平台交易对关联表
* @author: leisure
* @date: 2018年5月25日 下午5:56:49
 */
@Entity
@Table(name="platform_coin_relevance")
public class PlatformCoinRelevance extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7937075171629069860L;
	/**
	 * 主键
	 */
	private Integer id;	
	/**
	 * 属于本平台的交易对名
	 */
	private String ownTradingAssetPairName;
	/**
	 * 所属平台Id
	 */
	private Integer tradingPlatformId;
	
	/**
	 * 对应的统一交易对的Id
	 */
	private Integer TradingAssetPairId;
	/**
	 * 描述信息
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Date gmtCreate;
	/**
	 * 最后一次修改时间
	 */
	private Date gmtModified;
	
	/***********************************************************************************************/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="own_trading_asset_pair_name")
	public String getOwnTradingAssetPairName() {
		return ownTradingAssetPairName;
	}
	public void setOwnTradingAssetPairName(String ownTradingAssetPairName) {
		this.ownTradingAssetPairName = ownTradingAssetPairName;
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
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
