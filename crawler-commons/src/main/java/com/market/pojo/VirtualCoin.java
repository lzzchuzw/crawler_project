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
@Table(name = "virtual_coin")
public class VirtualCoin extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4821086232689078443L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 非小号成交额排名
	 */
	private Integer ranking;
	/**
	 * token 名
	 */
	private String name;	
	/**
	 * 类型 0:虚拟币
	 *    1:代币
	 */
	private Integer coinType;
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
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="coin_type")
	public Integer getCoinType() {
		return coinType;
	}
	public void setCoinType(Integer coinType) {
		this.coinType = coinType;
	}
	@Column(name="ranking")
	public Integer getRanking() {
		return ranking;
	}
	public void setRanking(Integer ranking) {
		this.ranking = ranking;
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
