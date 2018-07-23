package com.utils.request;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.cookie.Cookie;


/**
 * 
 * @Description http请求的返回数据
 * @author lzz
 * @time 2018年2月8日 上午11:46:30
 */
public class ResponseRet {
	private StatusLine stautsLine;
	private ResponseType retType;
	private String contentEncoding;
	private byte[] retContent;
	private Header[] responseHeaders;
	private String locationUrl;
	private List<Cookie> cookieList;
	
	
	public ResponseRet() {
		
	}
	

	


	public StatusLine getStautsLine() {
		return stautsLine;
	}

	public void setStautsLine(StatusLine stautsLine) {
		this.stautsLine = stautsLine;
	}





	public ResponseType getRetType() {
		return retType;
	}

	public void setRetType(ResponseType retType) {
		this.retType = retType;
	}

	public String getContentEncoding() {
		return contentEncoding;
	}

	public void setContentEncoding(String contentEncoding) {
		this.contentEncoding = contentEncoding;
	}

	public byte[] getRetContent() {
		return retContent;
	}

	public void setRetContent(byte[] retContent) {
		this.retContent = retContent;
	}





	public Header[] getResponseHeaders() {
		return responseHeaders;
	}





	public void setResponseHeaders(Header[] responseHeaders) {
		this.responseHeaders = responseHeaders;
	}





	public String getLocationUrl() {
		return locationUrl;
	}





	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}





	public List<Cookie> getCookieList() {
		return cookieList;
	}





	public void setCookieList(List<Cookie> cookieList) {
		this.cookieList = cookieList;
	}

	
}
