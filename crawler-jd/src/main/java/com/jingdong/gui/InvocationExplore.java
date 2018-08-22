package com.jingdong.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
/**
 * 
* @ClassName: InvocationExplore
* @Description: java 调用浏览器的方法
* @author: leisure
* @date: 2018年5月15日 上午10:52:19
 */
public class InvocationExplore {
	public static final String PROCESS = "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe";
	//public static final String PROCESS = ""C:\\Program Files\\Internet Explorer\\iexplore.exe";
	//public static final String URL = "https://www.baidu.com";
	public static final String URL = "https://www.binance.com/";
	
	public static void main(String[] args) {
		//processBuilderExplore();
		//openDefaultExplore();
		//systemCallOpenExplore();
		try {
			invocationDefaultExplore(URL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: processBuilderExplore
	* @Description: TODO 通过ProcessBuilder调用指定浏览器
	* @author leisure
	* @date 2018年5月15日上午10:52:39
	 */
	public static void processBuilderExplore() {
		ProcessBuilder pb = new ProcessBuilder(PROCESS,"https://api.binance.com/api/v1/depth?symbol=BTCUSDT&limit=100");
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: openDefaultExplore
	* @Description: TODO 打开默认浏览器
	* @author leisure
	* @date 2018年5月15日上午10:53:07
	 */
	public static void openDefaultExplore() {
		URI uri = URI.create(URL);
		Desktop desktop = Desktop.getDesktop();
		if(desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	* @Title: systemCallOpenExplore
	* @Description: TODO 通过cmd系统调用打开浏览器
	* @author leisure
	* @date 2018年5月15日上午10:53:21
	 */
	public static void systemCallOpenExplore() {
		Runtime rt = Runtime.getRuntime();
		try {
			rt.exec("cmd /c start "+URL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	* @Title: invocationDefaultExplore
	* @Description: TODO
	* @param url
	* @throws Exception void
	* @author leisure
	* @date 2018年5月15日上午10:59:53
	 */
	public static void invocationDefaultExplore(String url) throws Exception {
		if(null==url) {
			System.out.println("input url is null");
			return;
		}
		String osName = System.getProperty("os.name","");
		System.out.println("osName = "+osName);
		if(osName.startsWith("Mac OS")) {
			 // 苹果的打开方式  
            Class fileMgr = Class.forName("com.apple.eio.FileManager");  
            Method openURL = fileMgr.getDeclaredMethod("openURL",  
                    new Class[] { String.class });  
            openURL.invoke(null, new Object[] { url });  
		}else if (osName.startsWith("Windows")) {  
            // windows的打开方式。  
            Runtime.getRuntime().exec(  
                    "rundll32 url.dll,FileProtocolHandler " + url);  
        } else {  
            // Unix or Linux的打开方式  
            String[] browsers = { "firefox", "opera", "konqueror", "epiphany",  
                    "mozilla", "netscape" };  
            String browser = null;  
            for (int count = 0; count < browsers.length && browser == null; count++)  
                // 执行代码，在brower有值后跳出，  
                // 这里是如果进程创建成功了，==0是表示正常结束。  
                if (Runtime.getRuntime()  
                        .exec(new String[] { "which", browsers[count] })  
                        .waitFor() == 0)  
                    browser = browsers[count];  
            if (browser == null)  
                throw new Exception("Could not find web browser");  
            else  
                // 这个值在上面已经成功的得到了一个进程。  
                Runtime.getRuntime().exec(new String[] { browser, url });  
        }  
	}

}
