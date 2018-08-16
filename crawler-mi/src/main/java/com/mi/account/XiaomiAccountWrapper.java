package com.mi.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.utils.request.HttpClientRequestHandler;


/**
 * 
 * @Description 小米官网账号Wrapper类
 * @author lzz
 * @time 2018年3月1日 下午3:19:36
 */
public class XiaomiAccountWrapper {
	private Log log = LogFactory.getLog(this.getClass());
	private XiaomiAccount xiaomiAccount;
	private AccountState accountState;
	private HttpClientRequestHandler requestHandler;
	
	
	public XiaomiAccountWrapper() {
		
	}
	
	public XiaomiAccountWrapper(XiaomiAccount xiaomiAccount,HttpClientRequestHandler requestHandler) {
		this.xiaomiAccount = xiaomiAccount;
		this.requestHandler = requestHandler;
	}
	/**************************************************************/
	public XiaomiAccount getXiaomiAccount() {
		return xiaomiAccount;
	}
	public void setXiaomiAccount(XiaomiAccount xiaomiAccount) {
		this.xiaomiAccount = xiaomiAccount;
	}
	public AccountState getAccountState() {
		return accountState;
	}
	public void setAccountState(AccountState accountState) {
		this.accountState = accountState;
	}
	public HttpClientRequestHandler getRequestHandler() {
		return requestHandler;
	}
	public void setRequestHandler(HttpClientRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	

}
