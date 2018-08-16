package com.mi.address;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.mi.request.HttpRequestHeaderGenerator;
import com.utils.random.RandomNumberGenerator;
import com.utils.regex.RegexUtils;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.ResponseRet;


public class XiaomiAddressManager {
	
	public List<String> fetchAddressIdList(final HttpClientRequestHandler requestHandler){
		List<String> addressIdList = new ArrayList<String>();
		//设置访问方法 GET https://order.mi.com/user/address?r=35304.1520219562
		//参数r是总长度为15的数据,其中整数部分长度为5
		String r = RandomNumberGenerator.generateRandomDoubleString(15, 5);
		String url = "https://order.mi.com/user/address?r="+r;
		RequestBuilder requestBuilder = RequestBuilder.get()
				                                      .setUri(url);
		//设置访问的Header
		HttpRequestHeaderGenerator.setFetchAddressIdListHeaders(requestBuilder,url);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "fetchAddressIdList");
		//获取responseString
		String responseString = null;
		try {
			responseString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			//log.error("parse responseString error");
			e.printStackTrace();
		}
		if(null==responseString) {
			return null;
		}
		addressIdList = RegexUtils.findValueGroup(responseString, "(?<=data-address_id=\").*?(?=\")");
		
		for(int index=0;index<addressIdList.size();index++) {
			System.out.println("addressIdList["+index+"] = "+addressIdList.get(index));
		}
		return addressIdList;
	}

}
