package com.jingdong.gui;

import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class JEditorPaneTest {

    //text/html文本类型  
    static JEditorPane jep = null;
	public static void main(String[] args) {
		 JFrame jf = new JFrame("JEditorPane示例");  
         
			try {
				jep = new JEditorPane("https://passport.jd.com/new/login.aspx");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}  
	        JScrollPane jsp = new JScrollPane(jep);//添加滚动支持  
	        jep.addHyperlinkListener(new HyperlinkListener() {//添加链接点击监听者  
	              
	            @Override  
	            public void hyperlinkUpdate(HyperlinkEvent e) {  
	                if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED){  
	                    try {  
	                        jep.setPage(e.getURL());//设置显示的URL资源  
	                    } catch (IOException e1) {  
	                        e1.printStackTrace();  
	                    }  
	                }  
	                  
	            }  
	        });  
	        jf.add(jsp);  
	          
	        jf.setSize(Toolkit.getDefaultToolkit().getScreenSize().getSize());//窗口全屏  
	        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	        jf.setVisible(true);  
	}

}
