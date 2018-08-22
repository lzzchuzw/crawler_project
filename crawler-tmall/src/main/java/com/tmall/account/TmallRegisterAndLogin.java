package com.tmall.account;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.RequestBuilder;

import com.tmall.constant.TmallConstant;
import com.utils.request.HttpClientRequestHandler;
/**
 * 
* @ClassName: TmallRegisterAndLogin
* @Description: 天猫注册与登录类
* @author: leisure
* @date: 2018年8月20日 上午10:38:55
 */
public class TmallRegisterAndLogin {
	
	private Log log = LogFactory.getLog(this.getClass()); 
	
	/**
	 * 
	* @Title: visitTmallLoginHomePage
	* @Description: 访问Tmall登录主页
	* @param userAccountWrapper void
	* @author leisure
	* @date 2018年8月20日上午10:45:43
	 */
	public void visitTmallLoginHomePage(final TmallAccountWrapper userAccountWrapper) {
		if(null==userAccountWrapper ) {
			log.error("Tmall userAccountWrapper is null");
			return;
		}
		HttpClientRequestHandler requestHandler = userAccountWrapper.getRequestHandler();
		TmallAccount userAccount = userAccountWrapper.getUserAccount();
		if(null==requestHandler || null==userAccount) {
			log.error("requestHandler is null or TmallAccount is null");
			return ;
		}
		//设置访问方法 GET https://account.xiaomi.com/pass/serviceLogin
		RequestBuilder requestBuilder = RequestBuilder.get()
						                              .setUri(TmallConstant.TMALL_LOGIN_URL);
		//设置访问的Header
		//HttpRequest
		
	}

}
