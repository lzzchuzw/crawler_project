package com.tmall.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.utils.request.HttpClientRequestHandler;
/**
 * 
* @ClassName: TmallAccountWrapper
* @Description: 天猫 AccountWrapper,包含了HttpClientRequestHandler
* @author: leisure
* @date: 2018年8月20日 上午10:36:10
 */
public class TmallAccountWrapper {
	private Log log = LogFactory.getLog(this.getClass());
	private TmallAccount userAccount;
	private HttpClientRequestHandler requestHandler;
	
	/*******************************************************************************************/
	public TmallAccountWrapper() {
		
	}
	
	public TmallAccountWrapper(TmallAccount userAccount,HttpClientRequestHandler requestHandler) {
		this.userAccount = userAccount;
		this.requestHandler = requestHandler;
	}
	
	/*******************************************************************************************/
	public TmallAccount getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(TmallAccount userAccount) {
		this.userAccount = userAccount;
	}
	public HttpClientRequestHandler getRequestHandler() {
		return requestHandler;
	}
	public void setRequestHandler(HttpClientRequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	
	
	

}
