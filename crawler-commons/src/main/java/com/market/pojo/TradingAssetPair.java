package com.market.pojo;
/**
 * 
* @ClassName: TradingAssetPair
* @Description: 虚拟货币交易对 反应每个交易所所支持交易的交易对
* @author: leisure
* @date: 2018年5月25日 上午8:46:38
 */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.utils.hibernate.BaseEntity;

@Entity
@Table(name="trading_asset_pair")
public class TradingAssetPair extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 139377129980815241L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 交易对名 eg: btc_eth
	 */
	private String name;
	
	/**
	 * 交易对的左边对应的货币,用于生成订单的交易货币 比如btc_eth orderCurrency就是btc
	 */
	private String orderCurrencyName;
	/**
	 * 交易对的右边对应的货币该交易对所属的Market 比如 btc_eth 属于eth交易市场的 目前主流的通用交易市场 BTC ETH USDT
	 */
	private String paymentCurrencyName;	
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
	
	/******************************************************************************/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	@Column(name="order_currency_name")
	public String getOrderCurrencyName() {
		return orderCurrencyName;
	}
	public void setOrderCurrencyName(String orderCurrencyName) {
		this.orderCurrencyName = orderCurrencyName;
	}
	@Column(name="payment_currency_name")
	public String getPaymentCurrencyName() {
		return paymentCurrencyName;
	}
	public void setPaymentCurrencyName(String paymentCurrencyName) {
		this.paymentCurrencyName = paymentCurrencyName;
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
