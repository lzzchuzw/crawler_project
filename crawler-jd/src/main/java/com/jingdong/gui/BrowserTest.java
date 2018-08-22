package com.jingdong.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class BrowserTest {
	
	public static void main(String[] args) {
		testMethod1();
	}
	
	public static void testMethod1() {
		 Display display=new Display();
	        Shell shell=new Shell(display);
	        shell.setText("SWT Browser Test");
	        shell.setSize(1920,1200);
	        
	        final Text text=new Text(shell,SWT.BORDER);
	        text.setBounds(110,5,560,25);
	        Button button=new Button(shell,SWT.BORDER);
	        button.setBounds(680,5,200,25);        
	        button.setText("go");
	        Label label=new Label(shell,SWT.LEFT);
	        label.setText("测试浏览器");
	        label.setBounds(5, 5, 100, 25);
	        
	        //final Browser browser=new Browser(shell,SWT.EMBEDDED_CHROMIUM);
	        final Browser browser=new Browser(shell,SWT.WEBKIT);
	        browser.setBounds(5,30,1200,800);
	        
	        button.addListener(SWT.Selection, new Listener()
	        {
	            public void handleEvent(Event event)
	            {
	                String input=text.getText().trim();
	                if(input.length()==0)return;
	               /* if(!input.startsWith("http://"))
	                {
	                    input="http://"+input;
	                    text.setText(input);
	                }*/
	                browser.setUrl(input);
	            }
	        });
	        
	        shell.open();
	        while (!shell.isDisposed()) {
	            if (!display.readAndDispatch())
	              display.sleep();
	          }
	          display.dispose();
	}

}
