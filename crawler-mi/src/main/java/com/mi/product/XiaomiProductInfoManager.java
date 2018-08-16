package com.mi.product;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import com.mi.request.HttpRequestHeaderGenerator;
import com.utils.regex.RegexUtils;
import com.utils.request.HttpClientRequestHandler;
import com.utils.request.ResponseRet;

/**
 * 
 * @Description 商品信息管理类
 * @author lzz
 * @time 2018年3月6日 下午2:42:57
 */
public class XiaomiProductInfoManager {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	/**
	 * 商品详情页获取商品的goods_id参数,为将商品添加进购物车做准备
	 * 
	 * productId相同的情况下 ,goods_id不同 
	 * productId是在进入商品详情页需要用到的id,goods_id指同一个productId下不同配置的商品
	 * 以手机为例：
	 * productId=10000057 商品详情页  https://item.mi.com/product/10000057.html
	 * goods_id有5个
	 * goods_id = 2171800013 对应的商品名: goods_name=小米Max 2 4GB+64GB 金色
	 * goods_id = 2171800015 对应的商品名      goods_name=小米Max 2 4GB+128GB 黑色
	 * ...
	 * 
	 * goods_id与默认的收货地址有关,该方法是在默认收货地址下获取的所有的goods_id,第一个goods_id是默认选中的
	 * @param requestHandler
	 * @param productId
	 * @return goods_id   
	 */
    public List<String> getGoodIdOfProduct(final HttpClientRequestHandler requestHandler,final String productId){
    	
    	if(null==requestHandler || null==productId) {
    		return null;
    	}
    	//设置访问方法GET https://order.mi.com/product/get?jsonpcallback=proget2callback&product_id=10000057&_=1520317599454
        StringBuffer sb = new StringBuffer("https://order.mi.com/product/get?jsonpcallback=proget2callback")
        		               .append("&product_id=")
        		               .append(productId)
        		               .append("&_")
        		               .append(String.valueOf(System.currentTimeMillis()));
        String url = new String(sb);
        System.out.println("url = "+url);
    	RequestBuilder requestBuilder = RequestBuilder.get()
        		                                      .setUri(url);
    	//设置访问的Header
		HttpRequestHeaderGenerator.setGetGoodIdOfProductHeaders(requestBuilder,url,productId);
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		//发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_generalMethod(requestHandler, "getGoodIdOfProduct");
		//获取responseString
		String responseString = null;
		try {
			responseString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			log.error("parse responseString error");
			e.printStackTrace();
		}
		if(null==responseString) {
			return null;
		}
		String jsonString = RegexUtils.getValueBetweenHeadAndTail(responseString, "proget2callback\\(", "\\)");
		
		List<String> goodsIdList = RegexUtils.getValueListBetweenHeadAndTail(jsonString, "\"goods_id\":\"", "\"");
		for(int index=0;index<goodsIdList.size();index++) {
			String goodsId = goodsIdList.get(index);
			System.out.println("index = "+index+"---goodsId = "+goodsId);
		}
    	return goodsIdList;
    }
}
