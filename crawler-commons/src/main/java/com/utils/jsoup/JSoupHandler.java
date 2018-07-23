package com.utils.jsoup;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.utils.map.MapUtils;
import com.utils.request.ResponseRet;


/**
 * 
 * @Description 用于小米商城解析返回页面
 * @author lzz
 * @time 2018年3月5日 下午3:47:04
 */
public class JSoupHandler {

	public static Map<String, Object> parserResponseRet_gotoPersonalCenterHomePage(final ResponseRet responseRet) {
		if (null == responseRet || null == responseRet.getRetContent()) {
			System.out.println("--------------------parserResponseRet_gotoPersonalCenterHomePage---------------参数为null");
			return null;
		}
		Map<String, Object> personalCenterHomePageMap = new HashMap<String, Object>();
		String htmlString = null;
		Document doc = null;
		try {
			htmlString = new String(responseRet.getRetContent(),responseRet.getContentEncoding());
			doc = Jsoup.parse(htmlString,responseRet.getContentEncoding());
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		if(null!=doc) {
			System.out.println("doc is not null");
			Elements ulElements = doc.getElementsByClass("uc-nav-list");
			for(Element ulElement:ulElements) {
				Elements liElements = ulElement.children();
				int count = 1;
				for(Element li:liElements) {
					System.out.println("--------------------count = "+count+"----------------------");
					String html = li.html();
					String val = li.val();
					String text = li.text();
					String href = li.attr("href");
					System.out.println("html = "+html);
					System.out.println("val = "+val);
					System.out.println("text = "+text);
					System.out.println("href = "+href);
					Elements aTag = li.getElementsByTag("a");
					Element aTagElement = aTag.first();
					if(null!=aTagElement) {
						System.out.println("aTag href = "+aTagElement.attr("href"));
						System.out.println("aTag html = "+aTagElement.html());
						System.out.println("aTag text = "+aTagElement.text());
						System.out.println("aTag val = "+aTagElement.val());
						personalCenterHomePageMap.put(aTagElement.html(), aTagElement.attr("href"));
					}
					count++;
					
				}
			}
		}
		MapUtils.traversalMap(personalCenterHomePageMap);
		String shdzUrl = String.valueOf(personalCenterHomePageMap.get("收货地址"));
		System.out.println("收货地址url = "+shdzUrl);
		
		return personalCenterHomePageMap;
	}

}
