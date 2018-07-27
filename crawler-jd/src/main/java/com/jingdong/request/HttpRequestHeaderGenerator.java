package com.jingdong.request;

import java.util.Iterator;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.message.BasicHeader;

/**
 * @author Leisure
 * @version 创建时间：2018年1月26日 下午12:46:39 类说明
 */
public class HttpRequestHeaderGenerator {

	/**
	 * 设置访问京东登录主页的Headers
	 * 
	 * @param builder
	 */
	public static void setVisitJDLoginHomePageHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "passport.jd.com");
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 设置检测是否需要验证码的Headers
	 * 
	 * @param builder
	 */
	public static void setCheckAuthCodeHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "passport.jd.com");
			builder.setHeader("Referer", "https://passport.jd.com/new/login.aspx");
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取验证码需要的Headers
	 * 
	 * @param builder
	 */
	public static void setFetchAuthCodeHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "image/webp,image/apng,image/*,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "authcode.jd.com");
			builder.setHeader("Referer", "https://passport.jd.com/new/login.aspx");// 必须。因为没弄这个浪费了很长时间
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取验证码需要的Headers
	 * 
	 * @param builder
	 */
	public static void setLoginJDHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "passport.jd.com");
			//builder.setHeader("Referer", "https://passport.jd.com/new/login.aspx");
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取验证码需要的Headers
	 * 
	 * @param builder
	 */
	public static void setloginSuccessRedirectLocationHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Upgrade-Insecure-Requests", "1");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 配置获取预抢购商品的预约Url的Header
	 * 
	 * @param builder
	 */
	public static void setFetchPrecontractShoppingRushUrlHeaders(final RequestBuilder builder,String id) {
		if (null != builder) {
			builder.setHeader("Accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "yushou.jd.com");
			builder.setHeader("Referer", "https://item.jd.com/"+id+".html");
			builder.setHeader("Pragma", "no-cache");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
     * 配置预约抢购商品的Header
     * @param builder 
     * @param id 待预约商品Id
     */
	public static void setPrecontractShoppingRushHeaders(final RequestBuilder builder,String id) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "yushou.jd.com");
			builder.setHeader("Referer", "https://item.jd.com/"+id+".html");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("Upgrade-Insecure-Requests", "1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
     * 配置获取用户收货地址的Header
     * @param builder 
     */
	public static void setFetchAddressListHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "easybuy.jd.com");
			builder.setHeader("Referer", "http://home.jd.com/");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("Upgrade-Insecure-Requests", "1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
     * 配置获取用户收货地址的Header
     * @param builder 
     */
	public static void setFetchAddAddressCookieHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/plain, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "easybuy.jd.com");			
			builder.setHeader("Origin", "http://easybuy.jd.com");
			builder.setHeader("Referer", "http://easybuy.jd.com/address/getEasyBuyList.action");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
     * 配置添加用户收货地址的Header
     * @param builder 
     */
	public static void setAddNewAddressHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/plain, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "easybuy.jd.com");			
			builder.setHeader("Origin", "http://easybuy.jd.com");
			builder.setHeader("Referer", "http://easybuy.jd.com/address/getEasyBuyList.action");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
     * 配置抢购跳转的订单结算页获取收货地址的Header
     * @param builder 
     */
	public static void setFetchUsualAddressListHeaders(final RequestBuilder builder,String referer) {
		if (null != builder) {
			builder.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "marathon.jd.com");			
			builder.setHeader("Origin", "https://marathon.jd.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
     * 配置设置默认收货地址的Header
     * @param builder 
     */
	public static void setDefaultAddressByIdHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/plain, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "easybuy.jd.com");			
			builder.setHeader("Origin", "http://easybuy.jd.com");
			builder.setHeader("Referer", "http://easybuy.jd.com/address/getEasyBuyList.action");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	
	/**
     * 配置进入购物车主页面的Header
     * @param builder 
     */
	public static void setGotoShoppingCartHomePageHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "max-age=0");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "cart.jd.com");
			builder.setHeader("Upgrade-Insecure-Requests", "1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
     * 配置获取用户收货地址的Header
     * @param builder 
     */
	public static void setAddProductToCartHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "cart.jd.com");
			builder.setHeader("Origin", "https://cart.jd.com");
			builder.setHeader("Referer", "https://cart.jd.com/cart.action");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
     * 京东商城购物车页面"全选"或者取消"全选"请求的Header
     * @param builder 
     */
	public static void setSelectOrCancelAllItemHeaders(final RequestBuilder builder) {
		if (null != builder) {
			builder.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "cart.jd.com");
			builder.setHeader("Origin", "https://cart.jd.com");
			builder.setHeader("Referer", "https://cart.jd.com/cart.action");
			builder.setHeader("Pragma", "no-cache");
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 配置获取待抢购商品的Url的Header
	 * 
	 * @param builder
	 */
	public static void setFetchRushToPurchaseUrlHeaders(final RequestBuilder builder,String id) {
		if (null != builder) {
			builder.setHeader("Accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "itemko.jd.com");
			builder.setHeader("Referer", "https://item.jd.com/"+id+".html");
			builder.setHeader("Pragma", "no-cache");			
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取到抢购链接后,发起的第一个GET请求(302跳转)的Header
	 * 设置进入到订单结算页Step1的Header
	 * 
	 * @param builder
	 */
	public static void setgotoCheckOrderAndSettleAccountPageStep1Headers(final RequestBuilder builder,String id) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "divide.jd.com");
			builder.setHeader("Referer", "https://item.jd.com/"+id+".html");
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("Upgrade-Insecure-Requests", "1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取到抢购链接后,发起的第二个GET请求(302跳转)的Header
	 * 设置进入到订单结算页Step2的Header
	 * 
	 * @param builder
	 */
	public static void setgotoCheckOrderAndSettleAccountPageStep2Headers(final RequestBuilder builder,String id) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "marathon.jd.com");
			builder.setHeader("Referer", "https://item.jd.com/"+id+".html");
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("Upgrade-Insecure-Requests", "1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * 获取到抢购链接后,发起的第三个GET请求(302跳转)的Header
	 * 设置进入到订单结算页Step3的Header
	 * 
	 * @param builder
	 */
	public static void setgotoCheckOrderAndSettleAccountPageStep3Headers(final RequestBuilder builder,String id) {
		if (null != builder) {
			builder.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "marathon.jd.com");
			builder.setHeader("Referer", "https://item.jd.com/"+id+".html");
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("Upgrade-Insecure-Requests", "1");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * Header for:
	 * 抢购进入的订单结算页面,保存收货人信息时发起的第一个请求 https://marathon.jd.com/async/isSupportCodPayment.action?skuId=1552946
	 * 
	 * @param builder
	 * @param referer 
	 */
	public static void setIsSupportPaymentHeaders(final RequestBuilder builder,String referer) {
		if (null != builder) {
			builder.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "marathon.jd.com");
			builder.setHeader("Origin", "https://marathon.jd.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * Header for:
	 * 抢购进入的订单结算页面,保存收货人信息时发起的第二个请求 https://marathon.jd.com/async/isSupportCodPayment.action?skuId=1552946
	 * 
	 * @param builder
	 * @param referer 
	 */
	public static void setSaveConsigneeHeaders(final RequestBuilder builder,String referer) {
		if (null != builder) {
			builder.setHeader("Accept", "*/*");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Host", "ss.jd.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * Header for:
	 * 抢购进入的订单结算页面,保存支付及配送方式时发送的请求的Header https://marathon.jd.com/async/calcuOrderPrice.action?skuId=1552946&num=1
	 * 
	 * @param builder
	 * @param referer 
	 */
	public static void setSavePaymentAndDeliveryModeHeaders(final RequestBuilder builder,String referer) {
		if (null != builder) {
			builder.setHeader("Accept", "application/json, text/javascript, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			builder.setHeader("Host", "marathon.jd.com");
			builder.setHeader("Origin", "https://marathon.jd.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	/**
	 * Header for:
	 * 抢购提交订单请求的Header https://marathon.jd.com/seckill/submitOrder.action?skuId=1552946&vid=
	 * 
	 * @param builder
	 * @param referer 
	 */
	public static void setSubmitPurchaseOrderHeaders(final RequestBuilder builder,String referer) {
		if (null != builder) {
			builder.setHeader("Accept", "text/plain, */*; q=0.01");
			builder.setHeader("Accept-Encoding", "gzip, deflate, br");
			builder.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
			builder.setHeader("Cache-Control", "no-cache");
			builder.setHeader("Connection", "keep-alive");
			builder.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			builder.setHeader("Host", "marathon.jd.com");
			builder.setHeader("Origin", "https://marathon.jd.com");
			builder.setHeader("Referer", referer);
			builder.setHeader("Pragma", "no-cache");	
			builder.setHeader("X-Requested-With", "XMLHttpRequest");
			builder.setHeader("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.119 Safari/537.36");
		}
	}
	
	/**
	 * 将map转换为Header[]
	 * 
	 * @param map
	 * @return
	 */
	public static Header[] translateMapToHeaderArray(Map<String, Object> map) {
		if (null == map || 0 == map.size()) {
			return null;
		}
		Header[] requestHeader = new Header[map.size()];
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		int index = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			Header header = new BasicHeader(entry.getKey(), String.valueOf(entry.getValue()));
			requestHeader[index] = header;
			index++;
		}		
		return requestHeader;
	}

}
