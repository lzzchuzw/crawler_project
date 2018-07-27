package com.jingdong.account;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.jingdong.request.HttpRequestHeaderGenerator;
import com.utils.json.JsonUtils;
import com.utils.jsoup.JSoupHandler;
import com.utils.map.MapUtils;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.ResponseRet;

/**
 * 
 * @Description 京东注册与登录
 * @author lzz
 * @time 2018年2月8日 上午11:15:57
 */
public class JDRegisterAndLogin {

	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * 访问京东登录主页--账号登录部分 获取cookie和登录提交的Form表单的input
	 */
	public Map<String, Object> visitJDLoginHomePage(final JDAccountWrapper jdAccountWrapper) {
		if (null == jdAccountWrapper || null == jdAccountWrapper.getJdAccount()
				|| null == jdAccountWrapper.getRequestHandler()) {
			return null;
		}
		HttpClientRequestHandler requestHandler = jdAccountWrapper.getRequestHandler();
		// 设置访问方法GET "https://passport.jd.com/new/login.aspx"
		RequestBuilder requestBuilder = RequestBuilder.get()// Get 方法
				.setUri("https://passport.jd.com/new/login.aspx");// 设置访问的Uri
		// 设置访问的Header
		HttpRequestHeaderGenerator.setVisitJDLoginHomePageHeaders(requestBuilder);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		// 发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "visitJDLoginHomePage");
		// 解析获取form表达里的input标签
		Map<String, Object> visitJDLoginHomePageMap = JSoupHandler.parserResponseRet_visitJDLoginHomePage(responseRet);
		MapUtils.traversalMap(visitJDLoginHomePageMap);
		// 保存到requestHandler中
		requestHandler.getParseMap().put("visitJDLoginHomePageMap", visitJDLoginHomePageMap);
		return visitJDLoginHomePageMap;
	}

	/**
	 * 检测登录京东账号是否需要验证码
	 * 
	 * @return true 需要验证码 false 不需要验证码
	 */
	public boolean checkAuthCode(final JDAccountWrapper jdAccountWrapper) {

		boolean isNeedAuthCode = false;
		if (null == jdAccountWrapper || null == jdAccountWrapper.getJdAccount()
				|| null == jdAccountWrapper.getRequestHandler()) {
			return isNeedAuthCode;
		}
		HttpClientRequestHandler requestHandler = jdAccountWrapper.getRequestHandler();
		// 设置访问方法 GET https://passport.jd.com/uc/showAuthCode?r=123456&version=2015
		String requestUrl = "https://passport.jd.com/uc/showAuthCode?r=" + Math.random() + "&version=2015";
		RequestBuilder requestBuilder = RequestBuilder.get()// Get 方法
				.setUri(requestUrl);// 设置访问的Uri
		// 设置访问的Header
		HttpRequestHeaderGenerator.setCheckAuthCodeHeaders(requestBuilder);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		// 发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "checkAuthCode");
		// 解析返回结果
		if (null != responseRet && null != responseRet.getRetContent()) {
			String s = null;
			try {
				s = new String(responseRet.getRetContent(), responseRet.getContentEncoding());
				s = s.substring(1, s.length() - 1);
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			}
			if (null != s) {
				Map<String, Object> checkAuthCodeMap = JsonUtils.json2Map(s);
				if (checkAuthCodeMap.get("verifycode").toString().equals("true")) {
					isNeedAuthCode = true;
				}
			}
		}
		System.out.println(isNeedAuthCode ? "true" : "false");
		return isNeedAuthCode;
	}

	/**
	 * 获取验证码图片
	 */
	public void fetchAuthCode(final JDAccountWrapper jdAccountWrapper) {
		if (null == jdAccountWrapper || null == jdAccountWrapper.getJdAccount()
				|| null == jdAccountWrapper.getRequestHandler()) {
			return;
		}
		HttpClientRequestHandler requestHandler = jdAccountWrapper.getRequestHandler();
		// 设置访问方法 从requstHandler中获取Url+当前时间做参数
		String requestUrl = String
				.valueOf(requestHandler.getParseMap().get("visitJDLoginHomePageMap").get("authCodeUrl"))// 访问京东登录主页时已解析该Url
				+ System.currentTimeMillis();
		RequestBuilder requestBuilder = RequestBuilder.get()// Get 方法
				.setUri(requestUrl);// 设置访问的Uri
		// 设置访问的Header
		HttpRequestHeaderGenerator.setFetchAuthCodeHeaders(requestBuilder);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		// 发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "fetchAuthCode");
		// 获取到验证码图片,处理验证码图片
		requestHandler.setAuthCodeImgPath(responseRet.getFilePath());
	}

	/**
	 * 登录京东
	 * 
	 * @param jdAccountWrapper
	 */
	public void loginJDWithoutAuthCode(final JDAccountWrapper jdAccountWrapper) {
		if (null == jdAccountWrapper || null == jdAccountWrapper.getJdAccount()
				|| null == jdAccountWrapper.getRequestHandler()) {
			return;
		}
		JDAccount jdAccount = jdAccountWrapper.getJdAccount();
		boolean isNeedAuthCode = false;
		// 访问京东登录首页
		visitJDLoginHomePage(jdAccountWrapper);
		// 获取验证码
		if ((isNeedAuthCode = checkAuthCode(jdAccountWrapper))) {
			fetchAuthCode(jdAccountWrapper);// 获取验证码
		}
		/*
		 * Map<String,Object> visitJDLoginHomePageMap =
		 * requestHandler.getParseMap().get("visitJDLoginHomePageMap");
		 * MapUtils.traversalMap(visitJDLoginHomePageMap);
		 */
		/*
		 * try { Thread.sleep(1000); } catch (InterruptedException e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */
		// 设置访问方法
		String requestUrl = "https://passport.jd.com/uc/loginService";
		RequestBuilder requestBuilder = RequestBuilder.post()// Post 方法
				.setUri(requestUrl);// 设置访问的Uri
		// 设置访问的Header
		HttpRequestHeaderGenerator.setLoginJDHeaders(requestBuilder);

		HttpClientRequestHandler requestHandler = jdAccountWrapper.getRequestHandler();
		// 需要携带的表达数据
		Map<String, Object> visitJDLoginHomePageMap = requestHandler.getParseMap().get("visitJDLoginHomePageMap");
		visitJDLoginHomePageMap.remove("loginname");
		visitJDLoginHomePageMap.remove("loginpwd");
		visitJDLoginHomePageMap.remove("nloginpwd");
		visitJDLoginHomePageMap.remove("authCodeUrl");
		if (!isNeedAuthCode) {
			visitJDLoginHomePageMap.remove("authcode");
		}
		visitJDLoginHomePageMap.put("loginname", jdAccount.getUserName());
		visitJDLoginHomePageMap.put("loginpwd", jdAccount.getLoginPassWd());
		visitJDLoginHomePageMap.put("nloginpwd", jdAccount.getLoginPassWd());
		visitJDLoginHomePageMap.put("checkRememberMe", "");
		visitJDLoginHomePageMap.put("machineNet", "");
		visitJDLoginHomePageMap.put("machineCpu", "");
		visitJDLoginHomePageMap.put("machineDisk", "");
		System.out.println("遍历获取到的.....");
		MapUtils.traversalMap(visitJDLoginHomePageMap);
		try {
			requestBuilder.setEntity(new UrlEncodedFormEntity(
					HttpClientRequestHandler.generateNameValuePairs(visitJDLoginHomePageMap), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		// 发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "loginJD");
		// 解析请求
		if (null != responseRet && null != responseRet.getRetContent()) {
			String s = null;
			try {
				s = new String(responseRet.getRetContent(), responseRet.getContentEncoding());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String jsonString = "";
			// 用户名不存在
			if (s.contains("username")) {// "({\"username\":\"\\u8d26\\u6237\\u540d\\u4e0d\\u5b58\\u5728\\uff0c\\u8bf7\\u91cd\\u65b0\\u8f93\\u5165<br/>\\u5982\\u9700\\u6d77\\u5916\\u624b\\u673a\\u767b\\u5f55\\u8bf7\\u6dfb\\u52a0
											// <a href=\\\"javascript:void(0);\\\" id=\\\"country_code_selector\\\"
											// class=\\\"flk13\\\"
											// style=\\\"color:blue;\\\">\\u56fd\\u9645\\u533a\\u53f7</a>\",\"_t\":\"_t\",\"countryTips\":true})
											// 账户名不存在，请重新输入
				jsonString = s.substring(1, s.length() - 1);
				// 请输入验证码
			} else {

				jsonString = s.substring(1, s.length() - 1);
			}
			// 需要输入验证码
			if (s.contains("emptyAuthcode")) {
				System.out.println("需要输入验证码");
				fetchAuthCode(jdAccountWrapper);
			}
			Map<String, Object> loginJDMap = JsonUtils.json2Map(jsonString);
			handlerLoginJDResult(requestHandler, loginJDMap);

		}
	}

	public Map<String, Object> loginJD(final JDAccountWrapper jdAccountWrapper,Map<String, Object> visitJDLoginHomePageMap) {
		if (null == jdAccountWrapper || null == jdAccountWrapper.getJdAccount()
				|| null == jdAccountWrapper.getRequestHandler()) {
			return null;
		}
		JDAccount jdAccount = jdAccountWrapper.getJdAccount();
		// 设置访问方法
		String requestUrl = "https://passport.jd.com/uc/loginService";
		RequestBuilder requestBuilder = RequestBuilder.post()// Post 方法
				.setUri(requestUrl);// 设置访问的Uri
		// 设置访问的Header
		HttpRequestHeaderGenerator.setLoginJDHeaders(requestBuilder);

		HttpClientRequestHandler requestHandler = jdAccountWrapper.getRequestHandler();
		// 需要携带的表达数据
		//Map<String, Object> visitJDLoginHomePageMap = requestHandler.getParseMap().get("visitJDLoginHomePageMap");
		visitJDLoginHomePageMap.remove("loginname");
		visitJDLoginHomePageMap.remove("loginpwd");
		visitJDLoginHomePageMap.remove("nloginpwd");
		visitJDLoginHomePageMap.remove("authCodeUrl");
		/*
		 * if(!isNeedAuthCode) { visitJDLoginHomePageMap.remove("authcode"); }
		 */
		visitJDLoginHomePageMap.put("loginname", jdAccount.getUserName());
		visitJDLoginHomePageMap.put("loginpwd", jdAccount.getLoginPassWd());
		visitJDLoginHomePageMap.put("nloginpwd", jdAccount.getLoginPassWd());
		visitJDLoginHomePageMap.put("checkRememberMe", "");
		visitJDLoginHomePageMap.put("machineNet", "");
		visitJDLoginHomePageMap.put("machineCpu", "");
		visitJDLoginHomePageMap.put("machineDisk", "");
		System.out.println("遍历获取到的.....");
		MapUtils.traversalMap(visitJDLoginHomePageMap);
		try {
			requestBuilder.setEntity(new UrlEncodedFormEntity(
					HttpClientRequestHandler.generateNameValuePairs(visitJDLoginHomePageMap), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 添加JD特有的Cookie
		requestHandler.initJDSpecialCookies(requestHandler);
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		// 发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "loginJD");
		String jsonString = "";
		// 解析请求
		if (null != responseRet && null != responseRet.getRetContent()) {
			String s = null;
			try {
				s = new String(responseRet.getRetContent(), responseRet.getContentEncoding());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			// 用户名不存在
			if (s.contains("username")) {// "({\"username\":\"\\u8d26\\u6237\\u540d\\u4e0d\\u5b58\\u5728\\uff0c\\u8bf7\\u91cd\\u65b0\\u8f93\\u5165<br/>\\u5982\\u9700\\u6d77\\u5916\\u624b\\u673a\\u767b\\u5f55\\u8bf7\\u6dfb\\u52a0
											// <a href=\\\"javascript:void(0);\\\" id=\\\"country_code_selector\\\"
											// class=\\\"flk13\\\"
											// style=\\\"color:blue;\\\">\\u56fd\\u9645\\u533a\\u53f7</a>\",\"_t\":\"_t\",\"countryTips\":true})
											// 账户名不存在，请重新输入
				jsonString = s.substring(1, s.length() - 1);
				// 请输入验证码
			} else {

				jsonString = s.substring(1, s.length() - 1);
			}
			// 需要输入验证码
			if (s.contains("emptyAuthcode")) {
				System.out.println("需要输入验证码");
				fetchAuthCode(jdAccountWrapper);
			}

		}
		Map<String, Object> loginJDMap = JsonUtils.json2Map(jsonString);
		handlerLoginJDResult(requestHandler, loginJDMap);
		return loginJDMap;

	}

	/**
	 * 处理登录后的结果 根据
	 * 
	 * @param loginJDMap
	 */
	public void handlerLoginJDResult(final HttpClientRequestHandler requestHandler,
			final Map<String, Object> loginJDMap) {
		if (null == requestHandler || null == loginJDMap || 0 == loginJDMap.size()) {
			return;
		}
		if (loginJDMap.containsKey("success")) {// 登录成功处理
			log.info("登录成功...");
			loginSuccessRedirectLocation(requestHandler, loginJDMap);
		} else if (loginJDMap.containsKey("username")) {// 用户名不存在
			log.info("用户名出错！");
		} else if (loginJDMap.containsKey("pwd")) {// 密码错误
			log.info("密码错误！");
		} else if (loginJDMap.containsKey("emptyAuthcode")) {// 需要输入验证码
			log.info("需要输入验证码");
		} else {// 账号被锁定等信息

		}

	}

	/**
	 * 京东登录成功后的跳转
	 * 
	 * @param loginJDMap
	 */
	public void loginSuccessRedirectLocation(final HttpClientRequestHandler requestHandler,
			final Map<String, Object> loginJDMap) {
		if (null == requestHandler || null == loginJDMap || 0 == loginJDMap.size()) {
			return;
		}
		String requestUrl = String.valueOf(loginJDMap.get("success"));
		RequestBuilder requestBuilder = RequestBuilder.get()// Get 方法
				.setUri(requestUrl);// 设置访问的Uri
		// 设置访问的Header
		HttpRequestHeaderGenerator.setloginSuccessRedirectLocationHeaders(requestBuilder);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		// 发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler,
				"loginSuccessRedirectLocation");
		requestHandler.GetHttpResponse_generalMethod(requestHandler, "loginSuccessRedirectLocation");
		// 解析请求
	}

}
