package com.jingdong.account;

import java.util.Date;

/**
 * 
 * @Description 
 * @author lzz
 * @time 2018年2月8日 上午10:04:54
 */
public class JDAccount {
	
	private int id;
	private String userName;
	private String loginPassWd;
	private String mobile;
	private String payPassWd;
	private String mail;
	private int accountState;
	private Date gmtCreate;
	private Date gmtModify;
	private String others;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginPassWd() {
		return loginPassWd;
	}
	public void setLoginPassWd(String loginPassWd) {
		this.loginPassWd = loginPassWd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPayPassWd() {
		return payPassWd;
	}
	public void setPayPassWd(String payPassWd) {
		this.payPassWd = payPassWd;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getAccountState() {
		return accountState;
	}
	public void setAccountState(int accountState) {
		this.accountState = accountState;
	}
	public Date getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public Date getGmtModify() {
		return gmtModify;
	}
	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
