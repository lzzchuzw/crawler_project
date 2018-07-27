package com.utils.request;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





/**
 * 用于处理HttpClient请求的类
 * 
 * @author Leisure
 * @date 2018年1月19日
 */
public class HttpClientRequestHandler {
	public static final String FILE_PATH = "f:/testFolder/simulateLogin/xiaomi/";
	public static final boolean DEBUG = true;
	
	private HttpClient httpClient;
	private HttpClientContext context;
	private HttpUriRequest requestMethod;
	private String authCodeImgPath;
	private Map<String, ResponseRet> resonseRetMap;
	private Map<String, Map<String, Object>> parseMap;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	

	

	/**********************************************/
    public HttpClientRequestHandler(boolean isProxy,HttpHost proxyHost) {
		
		// httpclient
		HttpClientBuilder hcb = HttpClients.custom();
		CloseableHttpClient httpClient = null;
		if (isProxy) {
			httpClient = this.setProxyStrategy(hcb)
			                 .setProxy(proxyHost)
			                 .build();
			System.out.println("初始化HttpClientRequestHandler-----代理");
		} else {
			httpClient = hcb.build();
		}
		System.out.println("初始化HttpClientRequestHandler");
		// context
		HttpClientContext context = generateHttpClientContext();
		this.httpClient = httpClient;
		this.context = context;
		this.requestMethod = null;
		this.resonseRetMap = new HashMap<String, ResponseRet>();
		this.parseMap = new HashMap<String,Map<String,Object>>();
	}
	public HttpClientRequestHandler() {
		
		// httpclient
		HttpClientBuilder hcb = HttpClients.custom();
		CloseableHttpClient httpClient = null;
		if (RequestConstant.IS_PROXY) {
			httpClient = this.setProxyStrategy(hcb).build();
			System.out.println("初始化HttpClientRequestHandler-----代理");
		} else {
			httpClient = hcb.build();
		}
		System.out.println("初始化HttpClientRequestHandler");
		// context
		HttpClientContext context = generateHttpClientContext();
		this.httpClient = httpClient;
		this.context = context;
		this.requestMethod = null;
		this.resonseRetMap = new HashMap<String, ResponseRet>();
		this.parseMap = new HashMap<String,Map<String,Object>>();
	}
	
     public HttpClientRequestHandler(PoolingHttpClientConnectionManager connManager,InetSocketAddress socketProxy) {
		
		// httpclient
		HttpClientBuilder hcb = HttpClients.custom();
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				
                .setBufferSize(4128)
                .build();
		CloseableHttpClient httpClient = hcb.setConnectionManager(connManager)
				                            .setDefaultConnectionConfig(connectionConfig)
				                            .build();
		
		                              
		// context
		HttpClientContext context = generateHttpClientContext();
		context.setAttribute("socks.address", socketProxy); 
		this.httpClient = httpClient;
		this.context = context;
		this.requestMethod = null;
		this.resonseRetMap = new HashMap<String, ResponseRet>();
		this.parseMap = new HashMap<String,Map<String,Object>>();
	}
	

	/**********************************************************************************/
	/**
	 * 设置代理,以JAVA虚拟机做为代理;
	 * 
	 * @param httpClientBuilder
	 * @return
	 */
	public HttpClientBuilder setProxyStrategy(HttpClientBuilder httpClientBuilder) {
		SystemDefaultRoutePlanner routePlanner = new SystemDefaultRoutePlanner(ProxySelector.getDefault());
		httpClientBuilder.setRoutePlanner(routePlanner);
		return httpClientBuilder;
	}

	/**
	 * 生成最基本的HttpClientContext,需要配置CookieStore
	 * 
	 * @return
	 */
	public HttpClientContext generateHttpClientContext() {
		HttpClientContext context = HttpClientContext.create();
		addRequestConfig(context);
		addCookieStore(context);

		return context;
	}

	/**
	 * 为HttpClientContext添加CookieStore
	 * 
	 * @param context
	 * @return
	 */
	public HttpClientContext addCookieStore(final HttpClientContext context) {
		CookieStore cookieStore = new BasicCookieStore();
		context.setCookieStore(cookieStore);
		return context;
	}

	public HttpClientContext addRequestConfig(final HttpClientContext context) {
		RequestConfig requestConfig = RequestConfig.custom()
				                      .setCookieSpec(CookieSpecs.STANDARD)
				                      /*.setConnectTimeout(5000)
				                      .setConnectionRequestTimeout(5000)
				                      .setSocketTimeout(7000)*/
				                      .build();
		
		context.setRequestConfig(requestConfig);
		return context;
	}

	

	/**
	 * 将map转换为Header[]
	 * 
	 * @param map
	 * @return
	 */
	public static Header[] translateMapToHeaderArray(Map<String, String> map) {
		if (null == map || 0 == map.size()) {
			return null;
		}
		Header[] requestHeader = new Header[map.size()];
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		int index = 0;
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			Header header = new BasicHeader(entry.getKey(), entry.getValue());
			requestHeader[index] = header;
			index++;
		}
		/*
		 * for(int i=0;i<map.size();i++) { Map.Entry<String, String> entry = map. }
		 */
		// requestHeader
		/*
		 * for(index=0;index<requestHeader.length;index++) {
		 * System.out.println("requestHeader["+index+"].name = "+requestHeader[index].
		 * getName()+"---requestHeader["+index+"].value = "+requestHeader[index].
		 * getValue()); }
		 */
		return requestHeader;
	}

	public static List<BasicNameValuePair> generateNameValuePairs(Map<String, Object> map) {
		// List<Cookie> cookieList = cookieStore.getCookies();
		List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			String value = "";
			if (null != entry.getValue()) {
				value = entry.getValue().toString();
			}
			pairList.add(new BasicNameValuePair(key, value));
		}
		/* 遍历pairList */
		System.out.println("----------------------------遍历pairList-----------------------------------");
		for (int i = 0; i < pairList.size(); i++) {
			BasicNameValuePair bnvp = pairList.get(i);
			System.out.println("i = " + i + "----bnvp.name = " + bnvp.getName() + "---bnvp.value = " + bnvp.getValue());
		}
		return pairList;
	}
	/**
	 * 
	* @Title: generateListNameValuePairs
	* @Description: 由map生成 表单数据
	* @param map
	* @return List<BasicNameValuePair>
	* @author leisure
	* @date 2018年7月4日下午3:41:59
	 */
	public static List<BasicNameValuePair> generateListNameValuePairs(Map<String, String> map) {
		// List<Cookie> cookieList = cookieStore.getCookies();
		List<BasicNameValuePair> pairList = new ArrayList<BasicNameValuePair>();
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			String key = entry.getKey();
			String value = "";
			if (null != entry.getValue()) {
				value = entry.getValue().toString();
			}
			pairList.add(new BasicNameValuePair(key, value));
		}
		/* 遍历pairList */
		System.out.println("----------------------------遍历pairList-----------------------------------");
		for (int i = 0; i < pairList.size(); i++) {
			BasicNameValuePair bnvp = pairList.get(i);
			System.out.println("i = " + i + "----bnvp.name = " + bnvp.getName() + "---bnvp.value = " + bnvp.getValue());
		}
		return pairList;
	}
	
	/**
	 * 初始化一些京东商城特有的Cookie
	 * @param requestHandler
	 */
	public void initJDSpecialCookies(HttpClientRequestHandler requestHandler) {
		/*requestHeaderParam.put("Cookie", 
		 *      "__jda=122270672.15154973380332050715474.1515497338.1515497338.1515497338.1;" + 
				"__jdb=122270672.1.15154973380332050715474|1.1515497338; " + 
				"__jdc=122270672; " + 
				"__jdv=122270672|direct|-|none|-|1515497338036;" + 
				"_jrda=1; " + 
				"_jrdb=1515497338152; " + 
				"__jdu=15154973380332050715474; " + 
				"wlfstk_smdl=d49a24c8oeaav8kkrxbdh3o5ftl1r5n5; " + 
				"3AB9D23F7A4B3C9B=IRNZB6RPCFBN64NU657CNVAW4BGI3R3UZCEFZ7P2TW233YCKGDITAR7O7LUUT5S46CRDX5T737EDK5RO7F34F5ECD4");*/
		HttpClientContext context = requestHandler.getContext();
		CookieStore cookieStore = context.getCookieStore();
		if(null==cookieStore) {
			cookieStore = new BasicCookieStore();
		}
		cookieStore.addCookie(generateBasicClientCookie("__jda", "122270672.15154973380332050715474.1515497338.1515497338.1515497338.1"));
		cookieStore.addCookie(generateBasicClientCookie("__jdb", "122270672.1.15154973380332050715474|1.1515497338"));
		cookieStore.addCookie(generateBasicClientCookie("__jdc", "122270672"));
		cookieStore.addCookie(generateBasicClientCookie("__jdv", "122270672|direct|-|none|-|1515497338036"));
		cookieStore.addCookie(generateBasicClientCookie("__jdu", "15154973380332050715474"));
		cookieStore.addCookie(generateBasicClientCookie("_jrda", "1"));
		cookieStore.addCookie(generateBasicClientCookie("_jrdb", "1515497338152"));
		cookieStore.addCookie(generateBasicClientCookie("wlfstk_smdl", "d49a24c8oeaav8kkrxbdh3o5ftl1r5n5"));
		cookieStore.addCookie(generateBasicClientCookie("PCSYCityID", "1"));
		cookieStore.addCookie(generateBasicClientCookie("3AB9D23F7A4B3C9B", "IRNZB6RPCFBN64NU657CNVAW4BGI3R3UZCEFZ7P2TW233YCKGDITAR7O7LUUT5S46CRDX5T737EDK5RO7F34F5ECD4"));
		context.setCookieStore(cookieStore);
	}
    
	/**
	 * 处理Response的通用方法,
	 * 
	 * @param requestHandler
	 * @param fileNamePrefix
	 *            用于保存文件时的文件名前缀
	 * @return
	 */
	public ResponseRet GetHttpResponse_parseMarketInfo(HttpClientRequestHandler requestHandler, String fileNamePrefix) {
		HttpClient httpClient = requestHandler.getHttpClient();
		HttpClientContext context = requestHandler.getContext();
		HttpUriRequest requestMethod = requestHandler.getRequestMethod();
		ResponseRet responseRet = new ResponseRet();
		outputRequestHeaderAndCookies(requestHandler);
		CloseableHttpResponse response = null;
		/*StackTraceElement[] stacks = Thread.currentThread().getStackTrace(); 
		String locationInfo = "类名："+stacks[2].getClassName() + "\n函数名：" + stacks[2].getMethodName()
    			+ "\n文件名：" + stacks[2].getFileName() + "\n行号："
    			+ stacks[2].getLineNumber() + "";
		System.out.println(locationInfo);*/
		try {
			response = (CloseableHttpResponse) httpClient.execute(requestMethod, context);
			int responseCode = outputResponseHeaderAndCookies(requestHandler, response, responseRet);
			if(200!=responseCode) {
				System.out.println("get responseCode = "+responseCode);
				return responseRet;
			}
			// 请求返回消息体
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				
				// 存储到硬盘
				// 将entity保持到内存中rushToPurchasePage
				byte[] responseBody = EntityUtils.toByteArray(entity);
				String filePath = "F:\\testFolder\\virtual_coin\\binance\\market_depth\\";
				String fileName = fileNamePrefix + "_" + System.currentTimeMillis();
				// 默认设置
				responseRet.setRetType(ResponseType.JSON);
				responseRet.setContentEncoding("gzip");
				
				// 返回文本类型
				fileName += ".txt";
				responseRet.setRetContent(responseBody);
				System.out.println("fileName = "+filePath + fileName);
				File file = new File(filePath + fileName);
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				EntityUtils.consume(entity);
				bos.write(responseBody);
				bos.flush();
				bos.close();
				requestHandler.getResonseRetMap().put(fileNamePrefix, responseRet);
			} else {
				System.out.println("entity is null");
			}

		} catch (ClientProtocolException e) {
            System.out.println("requst method = "+requestHandler.getRequestMethod().getMethod());
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			// 关闭response
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseRet;
	}
	/**
	 * 处理Response的通用方法,
	 * 
	 * @param requestHandler
	 * @param fileDirPath
	 * @param fileNamePrefix
	 *            用于保存文件时的文件名前缀
	 * @return
	 */
	public ResponseRet GetHttpResponse_parseMarketInfo(HttpClientRequestHandler requestHandler, String fileDirPath,String fileNamePrefix) {
		HttpClient httpClient = requestHandler.getHttpClient();
		HttpClientContext context = requestHandler.getContext();
		HttpUriRequest requestMethod = requestHandler.getRequestMethod();
		ResponseRet responseRet = new ResponseRet();
		outputRequestHeaderAndCookies(requestHandler);
		CloseableHttpResponse response = null;
		/*StackTraceElement[] stacks = Thread.currentThread().getStackTrace(); 
		String locationInfo = "类名："+stacks[2].getClassName() + "\n函数名：" + stacks[2].getMethodName()
    			+ "\n文件名：" + stacks[2].getFileName() + "\n行号："
    			+ stacks[2].getLineNumber() + "";
		System.out.println(locationInfo);*/
		try {
			response = (CloseableHttpResponse) httpClient.execute(requestMethod, context);
			int responseCode = outputResponseHeaderAndCookies(requestHandler, response, responseRet);
			if(200!=responseCode) {
				System.out.println("get responseCode = "+responseCode);
				return responseRet;
			}
			// 请求返回消息体
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				
				// 存储到硬盘
				// 将entity保持到内存中rushToPurchasePage
				byte[] responseBody = EntityUtils.toByteArray(entity);
				if(null==fileDirPath) {
				   fileDirPath = "F:\\testFolder\\virtual_coin\\binance\\market_depth\\";
				}
				String fileName = fileNamePrefix + "_" + System.currentTimeMillis();
				// 默认设置
				responseRet.setRetType(ResponseType.JSON);
				responseRet.setContentEncoding("gzip");
				
				// 返回文本类型
				fileName += ".txt";
				responseRet.setRetContent(responseBody);
				System.out.println("fileName = "+fileDirPath + fileName);
				File file = new File(fileDirPath + fileName);
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				EntityUtils.consume(entity);
				bos.write(responseBody);
				bos.flush();
				bos.close();
				requestHandler.getResonseRetMap().put(fileNamePrefix, responseRet);
			} else {
				System.out.println("entity is null");
			}

		} catch (ClientProtocolException e) {
            System.out.println("requst method = "+requestHandler.getRequestMethod().getMethod());
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			// 关闭response
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseRet;
	}
	/**
	 * 
	* @Title: GetHttpResponse_parseMarketTickerDataInfo
	* @Description: 处理请求MarketTickerData返回信息
	* @param requestHandler
	* @param fileNamePrefix
	* @return ResponseRet
	* @author leisure
	* @date 2018年5月17日上午11:21:04
	 */
	public ResponseRet GetHttpResponse_getMarketTickerDataInfo(HttpClientRequestHandler requestHandler, String fileNamePrefix) {
		HttpClient httpClient = requestHandler.getHttpClient();
		HttpClientContext context = requestHandler.getContext();
		HttpUriRequest requestMethod = requestHandler.getRequestMethod();
		ResponseRet responseRet = new ResponseRet();
		outputRequestHeaderAndCookies(requestHandler);
		CloseableHttpResponse response = null;
		/*StackTraceElement[] stacks = Thread.currentThread().getStackTrace(); 
		String locationInfo = "类名："+stacks[2].getClassName() + "\n函数名：" + stacks[2].getMethodName()
    			+ "\n文件名：" + stacks[2].getFileName() + "\n行号："
    			+ stacks[2].getLineNumber() + "";
		System.out.println(locationInfo);*/
		try {
			response = (CloseableHttpResponse) httpClient.execute(requestMethod, context);
			int statusCode = outputResponseHeaderAndCookies(requestHandler, response, responseRet);
			if(200!=statusCode) {
				return null;
			}
			// 请求返回消息体
			HttpEntity entity = response.getEntity();
			
			if (null != entity) {
				
				// 存储到硬盘
				// 将entity保持到内存中rushToPurchasePage
				byte[] responseBody = EntityUtils.toByteArray(entity);
				String filePath = "F:/testFolder/virtual_coin/market_ticker/";
				String fileName = fileNamePrefix + "_" + System.currentTimeMillis();
				// 默认设置
				responseRet.setRetType(ResponseType.JSON);
				responseRet.setContentEncoding("gzip");
				
				// 返回文本类型
				fileName += ".txt";
				responseRet.setRetContent(responseBody);
				System.out.println("fileName = "+filePath + fileName);
				File file = null;
				BufferedOutputStream bos = null;
				if(DEBUG) {
				      file = new File(filePath + fileName);
				      bos = new BufferedOutputStream(new FileOutputStream(file));
				      EntityUtils.consume(entity);
						
						bos.write(responseBody);
						bos.flush();
						bos.close();
				}
				
			
				requestHandler.getResonseRetMap().put(fileNamePrefix, responseRet);
			} else {
				System.out.println("entity is null");
			}

		} catch (ClientProtocolException e) {
            System.out.println();
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			// 关闭response
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseRet;
	}
	

	/**
	 * 处理Response的通用方法,
	 * 
	 * @param requestHandler
	 * @param fileNamePrefix
	 *            用于保存文件时的文件名前缀
	 * @return
	 */
	public ResponseRet GetHttpResponse_generalMethod(HttpClientRequestHandler requestHandler, String fileNamePrefix) {
		HttpClient httpClient = requestHandler.getHttpClient();
		HttpClientContext context = requestHandler.getContext();
		HttpUriRequest requestMethod = requestHandler.getRequestMethod();
		ResponseRet responseRet = new ResponseRet();
		outputRequestHeaderAndCookies(requestHandler);
		CloseableHttpResponse response = null;
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace(); 
		String locationInfo = "类名："+stacks[2].getClassName() + "\n函数名：" + stacks[2].getMethodName()
    			+ "\n文件名：" + stacks[2].getFileName() + "\n行号："
    			+ stacks[2].getLineNumber() + "";
		System.out.println(locationInfo);
		try {
			response = (CloseableHttpResponse) httpClient.execute(requestMethod, context);
			outputResponseHeaderAndCookies(requestHandler, response, responseRet);
			// 请求返回消息体
			HttpEntity entity = response.getEntity();
			if (null != entity) {
				// 消息体的返回类型 Content-Type: text/html; charset=gbk
				// 返回Json类型 Content-Type: application/json
				// 返回图片类型 Content-Type: image/png
				String contentValueValue = response.getFirstHeader("Content-Type").getValue();
				// 返回的消息体类型 默认为文本类型
				String contentType = "hext/html";
				// 返回的消息体编码 默认"GBK"
				String contentEncoding = "UTF-8";
				int ctLocation = contentValueValue.indexOf(";");
				int ceLocation = contentValueValue.indexOf("=");
				// 没有";"
				if (-1 == ctLocation) {
					contentType = contentValueValue;
					// contentEncoding = "gzip";
				} else {
					contentType = contentValueValue.substring(0, ctLocation);
					// 一般来说这种情况少见 ctLocation和ceLocation要么同时为-1要么同时存在
					/*if (-1 == ceLocation) {
						contentEncoding = "gzip";
					} else {
						contentEncoding = contentValueValue.substring(ceLocation + 1);
					}*/
					if(-1!=ceLocation) {
						contentEncoding = contentValueValue.substring(ceLocation + 1);
					}
				}
				System.out.println("contentType = " + contentType + "----contentEncoding = " + contentEncoding);
				// 存储到硬盘
				// 将entity保持到内存中rushToPurchasePage
				byte[] responseBody = EntityUtils.toByteArray(entity);
				String filePath = FILE_PATH;
				String fileName = fileNamePrefix + "_" + System.currentTimeMillis();
				// 默认设置
				responseRet.setRetType(ResponseType.HTML);
				responseRet.setContentEncoding(contentEncoding);
				
				// 返回文本类型
				if (contentType.equalsIgnoreCase("hext/html")) {
					// responseRet.setRetString(responseBodyString);
					fileName += ".html";
				} else if (contentType.equalsIgnoreCase("application/json")) {// 返回类型json
					responseRet.setRetType(ResponseType.JSON);
					fileName += ".txt";
				} else if (contentType.contains("image")) {// 返回类型Imag  image/png
					responseRet.setRetType(ResponseType.IMAG);
					fileName += ".png";
				} else {

					fileName += ".txt";
				}
				responseRet.setRetContent(responseBody);
				File file = new File(filePath + fileName);
				responseRet.setFilePath(filePath + fileName);
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				EntityUtils.consume(entity);
				bos.write(responseBody);
				bos.flush();
				bos.close();
				requestHandler.getResonseRetMap().put(fileNamePrefix, responseRet);
			} else {
				System.out.println("entity is null");
			}

		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			// 关闭response
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return responseRet;
	}
    /**
     * 对于有302重定向的返回的请求,该方法用于获取重定向返回的Location
     * @param requestHandler
     * @return 重定向的Location返回值
     */
	public String GetHttpResponse_parseLocationUrl(HttpClientRequestHandler requestHandler) {
		String locationUrl = null;
		HttpClient httpClient = requestHandler.getHttpClient();
		HttpClientContext context = requestHandler.getContext();
		HttpUriRequest requestMethod = requestHandler.getRequestMethod();
		CloseableHttpResponse response = null;
		ResponseRet responseRet = new ResponseRet();
		// 设置不支持重定向
		boolean flag = context.getRequestConfig().isRedirectsEnabled();
		if (flag) {
			resetRedirectConfigure(requestHandler, false);
		}
		outputRequestHeaderAndCookies(requestHandler);
		try {
			response = (CloseableHttpResponse) httpClient.execute(requestMethod, context);
			// 请求返回状态
			StatusLine statusLine = response.getStatusLine();
			outputResponseHeaderAndCookies(requestHandler, response,responseRet);
			// 重定向302
			if (302 == statusLine.getStatusCode()) {
				Header location = response.getFirstHeader("Location");
				if (null != location) {
					locationUrl = location.getValue();
					responseRet.setRetType(ResponseType.LOCATION);
					responseRet.setLocationUrl(locationUrl);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 恢复重定向设置
		if (flag) {
			resetRedirectConfigure(requestHandler, true);
		}
		return locationUrl;
	}
	/**
	 * 
	* @Title: httpClientRequest
	* @Description: 通用访问方法  在post方法时可能会携带Header或者Entity
	* 携带Header类似于访问时  https://www.baidu.com?a=1&b=2
	* 携带Entity是post请求特有的,提交表单数据
	* @param requestHandler
	* @param methodType
	* @param url
	* @param headerMap
	* @param entityMap
	* @param saveFileName
	* @return String
	* @author leisure
	* @date 2018年7月4日下午3:56:24
	 */
	public static String httpClientRequest(HttpClientRequestHandler requestHandler, String methodType, String url,
			                               Map<String,String> headerMap,Map<String,String> entityMap,
			                               String fileDirPath,String saveFileName) {
		// 获取responseString
		String responseString = null;
		if (null == requestHandler || null == methodType || null == url ) {
			return responseString;
		}
		RequestBuilder requestBuilder = null;

		// 设置访问的Header
		if("GET".equals(methodType)) {
			System.out.println("get method");
		   requestBuilder = RequestBuilder.get().setUri(url);
		   HttpRequestHeaderGenerator.setGetMarketInfoeaders(requestBuilder, url);
		   
		}else {//POST method
			System.out.println("post method");
			requestBuilder = RequestBuilder.post().setUri(url);
			HttpRequestHeaderGenerator.setPostMarketInfoHeaders(requestBuilder, url);
		}
		//携带表单数据
		if(null!=entityMap && 0!=entityMap.size()) {
			try {
				requestBuilder.setEntity(new UrlEncodedFormEntity(
						HttpClientRequestHandler.generateListNameValuePairs(entityMap)));
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
		}
		
		// 生成访问方法
		HttpUriRequest requestMethod = requestBuilder.build();
		
		//携带Headers
		if(null!=headerMap&&0!=headerMap.size()) {
		    Header[] header = HttpClientRequestHandler.translateMapToHeaderArray(headerMap);
		    requestMethod.setHeaders(header);
		   
		}
		// 保存访问方法
		requestHandler.setRequestMethod(requestMethod);
		// 发送请求
		ResponseRet responseRet = requestHandler.GetHttpResponse_parseMarketInfo(requestHandler, fileDirPath,saveFileName);
		if (null == responseRet || null == responseRet.getRetContent()) {
			return responseString;
		}
		try {
			responseString = new String(responseRet.getRetContent(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// log.error("parse responseString error");
			e.printStackTrace();
		}
		return responseString;
	}

	/**
	 * 处理Http请求的统一方法
	 * @param requestHandler
	 * @param fileNamePrefix 生成保存记录的文件名时需要用到的
	 * @param isRedirectsEnabled 是否需要重定向
	 * @return
	 */
	public ResponseRet HttpRequestHandler(final HttpClientRequestHandler requestHandler,final String fileNamePrefix,boolean isRedirectsEnabled) {
		if(null==requestHandler) {
			log.error("requestHandler is null");
			return null;
		}
		HttpClient httpClient = requestHandler.getHttpClient();
		HttpClientContext context = requestHandler.getContext();
		HttpUriRequest requestMethod = requestHandler.getRequestMethod();
		if(null==httpClient || null==context || null==requestMethod) {
			log.error("Http Request Param is null");
			return null;
		}
		System.out.println("----HttpRequestHandler---");
		ResponseRet responseRet = new ResponseRet();
		outputRequestHeaderAndCookies(requestHandler);
		// 设置不支持重定向
		context = resetRedirectConfigure(requestHandler, isRedirectsEnabled);
		CloseableHttpResponse response = null;
		//获取reponse
		try {
			response = (CloseableHttpResponse) httpClient.execute(requestMethod, context);
			System.out.println("HttpRequestHandler get reponse is not null");
			if(null!=response) {
				int statusCode = outputResponseHeaderAndCookies(requestHandler, response, responseRet);
				//StatusCode = 302的时候就不用解析HttpEntity了
				switch(statusCode) {
				    case  200://StatusCode值为200时,需要解析HttpEntity			    	 
						  parseResponse_generalMethod(response,fileNamePrefix,responseRet);
				    	  break;
				    case  302://302跳转 需要获取到跳转的地址locationUrl
				    	  Header location = response.getFirstHeader("Location");
				    	  
						  if (null != location) {
							String locationUrl = location.getValue();
							responseRet.setRetType(ResponseType.LOCATION);
							System.out.println("302跳转 locationUrl = "+locationUrl );
							responseRet.setLocationUrl(locationUrl);
						  }
				    	  break;
				    default:
				    	  break;
				}
			}else {
				System.out.println("get Reponse is null");
			}
		} catch (ClientProtocolException e) {
			//log.error("ClientProtocolException");
			System.out.println("ClientProtocolException");
			e.printStackTrace();
		} catch (IOException e) {
			//log.error("IOException");
			System.out.println("IOException");
			e.printStackTrace();
		}finally {
			// 关闭response
			if (null != response) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		requestHandler.getResonseRetMap().put(fileNamePrefix, responseRet);
		// 恢复本来支持的重定向策略
		resetRedirectConfigure(requestHandler, !isRedirectsEnabled);		
		return responseRet;
	}
	/**
	 * StatusCode为200时调用该方法,解析HttpEntity
	 * @param response
	 * @param fileNamePrefix
	 * @param responseRet
	 */
	public void parseResponse_generalMethod(final CloseableHttpResponse response, final String fileNamePrefix, final ResponseRet responseRet) {
		if(null==response || null==fileNamePrefix || null==responseRet) {
			return;
		}
		// 请求返回消息体
		HttpEntity entity = response.getEntity();
		if(null != entity) {
			try {
				// 消息体的返回类型 Content-Type: text/html; charset=gbk
				// 返回Json类型 Content-Type: application/json
				// 返回图片类型 Content-Type: image/png
				String contentValueValue = response.getFirstHeader("Content-Type").getValue();
				// 返回的消息体类型 默认为文本类型
				String contentType = "hext/html";
				// 返回的消息体编码 默认"GBK"
				String contentEncoding = "UTF-8";
				int ctLocation = contentValueValue.indexOf(";");
				int ceLocation = contentValueValue.indexOf("=");
				// 没有";"
				if (-1 == ctLocation) {
					contentType = contentValueValue;
					// contentEncoding = "gzip";
				} else {
					contentType = contentValueValue.substring(0, ctLocation);
					// 一般来说这种情况少见 ctLocation和ceLocation要么同时为-1要么同时存在
					/*if (-1 == ceLocation) {
						contentEncoding = "gzip";
					} else {
						contentEncoding = contentValueValue.substring(ceLocation + 1);
					}*/
					if(-1!=ceLocation) {
						contentEncoding = contentValueValue.substring(ceLocation + 1);
					}
				}
				System.out.println("contentType = " + contentType + "----contentEncoding = " + contentEncoding);
				// 存储到硬盘
				// 将entity保持到内存中rushToPurchasePage
				byte[] responseBody = EntityUtils.toByteArray(entity);
				String filePath = FILE_PATH;
				String fileName = fileNamePrefix + "_" + System.currentTimeMillis();
				// 默认设置
				responseRet.setRetType(ResponseType.HTML);
				responseRet.setContentEncoding(contentEncoding);
				
				// 返回文本类型
				if (contentType.equalsIgnoreCase("hext/html")) {
					// responseRet.setRetString(responseBodyString);
					fileName += ".html";
				} else if (contentType.equalsIgnoreCase("application/json")) {// 返回类型json
					responseRet.setRetType(ResponseType.JSON);
					fileName += ".txt";
				} else if (contentType.contains("image")) {// 返回类型Imag  image/png
					responseRet.setRetType(ResponseType.IMAG);
					fileName += ".png";
				} else {
		
					fileName += ".txt";
				}
				responseRet.setRetContent(responseBody);
				File file = new File(filePath + fileName);
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				EntityUtils.consume(entity);
				bos.write(responseBody);
				bos.flush();
				bos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			
		} else {
			System.out.println("entity is null");
		}
		
	}
	public void outputRequestHeaderAndCookies(HttpClientRequestHandler requestHandler) {
		HttpUriRequest requestMethod = requestHandler.getRequestMethod();
		HttpClientContext context = requestHandler.getContext();
		// 输出请求方法的Url
		String requestUrl = requestMethod.getURI().toString();
		//System.out.println("输出requset的Headers和Cookies---" + "requestUrl = " + requestUrl);
		log.info("输出requset的Headers和Cookies---" + "requestUrl = " + requestUrl);
		// 输出请求方法中的Headers
		Header[] headers = requestMethod.getAllHeaders();
		if (null != headers) {
			for (int index = 0; index < headers.length; index++) {
				System.out.println("requestHeaders[" + index + "].name = " + headers[index].getName()
						+ "--------requestHeaders[" + index + "].value = " + headers[index].getValue());
				
				/*log.info("requestHeaders[" + index + "].name = " + headers[index].getName()
						+ "--------requestHeaders[" + index + "].value = " + headers[index].getValue());*/
			}
		}
		// 输出Cookies
		outputCookies(context);
	}
    /**
     * 输出HttpResponse的Header和Cookies
     * @param requestHandler
     * @param response
     * @param responseRet
     * @return StatusCode 200/302等等 后面根据这个返回值做进一步的处理
     */
	public int outputResponseHeaderAndCookies(HttpClientRequestHandler requestHandler,
			CloseableHttpResponse response, ResponseRet responseRet) {
		HttpUriRequest requestMethod = requestHandler.getRequestMethod();
		HttpClientContext context = requestHandler.getContext();
		// 输出请求方法的Url
		String requestUrl = requestMethod.getURI().toString();
		//System.out.println("输出response的Headers和Cookies----" + "requestUrl = " + requestUrl);
		log.info("输出response的Headers和Cookies----" + "requestUrl = " + requestUrl);
		// 请求返回状态
		StatusLine statusLine = response.getStatusLine();
		responseRet.setStautsLine(statusLine);
		//statusLine.get
		System.out.println("statusLine = " + statusLine);
		// 输出请求方法中的Headers
		Header[] headers = response.getAllHeaders();
		if (null != headers) {
			responseRet.setResponseHeaders(headers);
			for (int index = 0; index < headers.length; index++) {
				/*System.out.println("responseHeaders[" + index + "].name = " + headers[index].getName()
						+ "--------responseHeaders[" + index + "].value = " + headers[index].getValue());*/
				
				log.info("responseHeaders[" + index + "].name = " + headers[index].getName()
						+ "--------responseHeaders[" + index + "].value = " + headers[index].getValue());
			}
		}
		// 输出Cookies
		outputCookies(context,responseRet);
		return statusLine.getStatusCode();

	}
	
	
	public void outputCookies(HttpClientContext context) {
		List<Cookie> cookieList = context.getCookieStore().getCookies();
		for (int index = 0; index < cookieList.size(); index++) {
			/*
			 * System.out.println("cookieList[" + index + "].name = " +
			 * cookieList.get(index).getName() + "-----cookieList[" + index + "].value = " +
			 * cookieList.get(index).getValue());
			 */
			System.out.println("第" + index + "个Cookie:" + cookieList.get(index).toString());
			log.info("第" + index + "个Cookie:" + cookieList.get(index).toString());
		}
	}
	/**
	 * 输出Cookie
	 * @param context
	 * @param responseRet 将Cookie保存到responseRet中
	 */
	public void outputCookies(HttpClientContext context,ResponseRet responseRet) {
		List<Cookie> cookieList = context.getCookieStore().getCookies();
		
		for (int index = 0; index < cookieList.size(); index++) {
			/*
			 * System.out.println("cookieList[" + index + "].name = " +
			 * cookieList.get(index).getName() + "-----cookieList[" + index + "].value = " +
			 * cookieList.get(index).getValue());
			 */
			//System.out.println("第" + index + "个Cookie:" + cookieList.get(index).toString());
			log.info("第" + index + "个Cookie:" + cookieList.get(index).toString());
			
		}
		responseRet.setCookieList(cookieList);
	}
	/**
	 * 从CookieList中获取指定name的Cookie  name-value pair
	 * @param cookieList
	 * @param nameList
	 * @return
	 */
	public Map<String,Object> getCookieByName(final List<Cookie> cookieList,final List<String> nameList){
		if(null==cookieList || null==nameList || 0==cookieList.size() || 0==nameList.size()) {
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		for(String name:nameList) {
			for(Cookie cookie:cookieList) {
				if(name.equalsIgnoreCase(cookie.getName())) {
					map.put(name, cookie.getValue());
				}
			}
		}
		return map;
	}
	/**
	 * 从CookieList中获取指定name的Cookie  name-value pair
	 * @param cookieList
	 * @param name
	 * @return
	 */
	public Map<String,Object> getCookieByName(final List<Cookie> cookieList,final String name){
		if(null==cookieList || null == name || 0==cookieList.size() ) {
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
			for(Cookie cookie:cookieList) {
				if(name.equalsIgnoreCase(cookie.getName())) {
					map.put(name, cookie.getValue());
				}
			
		}
		return map;
	}
	/**
	 * 从CookieList中获取指定name的Cookie  name-value pair
	 * @param requestHandler
	 * @param name
	 * @return
	 */
	public Map<String,Object> getCookieByName(final HttpClientRequestHandler requestHandler, final String name){
		if(null==requestHandler || null == name) {
			return null;
		}
		
		List<Cookie> cookieList = requestHandler.getContext().getCookieStore().getCookies();
		Map<String,Object> map = getCookieByName(cookieList,name);
		return map;
	}

	/**
	 * 根据value值重置"重定向"选择,用于配合获取Location值
	 * 
	 * @param requestHandler
	 * @param value
	 */
	public HttpClientContext resetRedirectConfigure(final HttpClientRequestHandler requestHandler, final boolean value) {
		if(null==requestHandler || null==requestHandler.getContext()) {
			return null;
		}
		RequestConfig requestConfig = RequestConfig.custom().setRedirectsEnabled(value).build();
		HttpClientContext context = requestHandler.getContext();
		context.setRequestConfig(requestConfig);
		return context;
	}
	
	public Cookie generateBasicClientCookie(String name,String value) {
		BasicClientCookie cookie = new BasicClientCookie(name,value);
		cookie.setVersion(0);
		cookie.setDomain("jd.com");
		cookie.setPath("/");
		cookie.setExpiryDate(null);
		//cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "/");
		return cookie;
	}

	/********************************************************/

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public HttpClientContext getContext() {
		return context;
	}

	public void setContext(HttpClientContext context) {
		this.context = context;
	}

	public HttpUriRequest getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(HttpUriRequest requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Map<String, ResponseRet> getResonseRetMap() {
		return resonseRetMap;
	}

	public void setResonseRetMap(Map<String, ResponseRet> resonseRetMap) {
		this.resonseRetMap = resonseRetMap;
	}

	public Map<String, Map<String, Object>> getParseMap() {
		return parseMap;
	}

	public void setParseMap(Map<String, Map<String, Object>> parseMap) {
		this.parseMap = parseMap;
	}
	public String getAuthCodeImgPath() {
		return authCodeImgPath;
	}
	public void setAuthCodeImgPath(String authCodeImgPath) {
		this.authCodeImgPath = authCodeImgPath;
	}

}
