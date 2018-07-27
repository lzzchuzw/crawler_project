package com.jingdong.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jingdong.account.AccountState;
import com.jingdong.account.JDAccount;
import com.jingdong.account.JDAccountWrapper;
import com.utils.request.HttpClientRequestHandler;




public class JDFrame extends JFrame{
	public static final int TABLE_ROW_HEIGHT = 35;
	public static final int CHECKCODE_COLUMN_INDEX = 4;
	private List<JDAccountWrapper> jdAccountWrapperList;
	private JDAccountTableModel mTalbeModel;
	private MyImageIconRenderer imageIconRenderer ;
	private JPanel mPanel;
	private JScrollPane mScrollPane;
	private JTable mTable;
	
	public JDFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JDFrame(List<JDAccountWrapper> jdAccountWrapperList) {
		this.jdAccountWrapperList = jdAccountWrapperList;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void createAndShowGui() {	
		//layout 
		this.setLayout(new BorderLayout());
		//table model
		mTalbeModel = new JDAccountTableModel(jdAccountWrapperList);
		//table 
		mTable = new JTable(mTalbeModel);
		//验证码Column的渲染类
		imageIconRenderer = new MyImageIconRenderer();
		
		mTable.setPreferredScrollableViewportSize(new Dimension(800,600));
		mTable.setRowHeight(TABLE_ROW_HEIGHT);
    	mTable.setFillsViewportHeight(true);
    	
    	//mTable.getColumnModel().getColumn(CHECKCODE_COLUMN_INDEX).setCellRenderer(imageIconRenderer);
    	mTable.setDefaultRenderer(ImageIcon.class, imageIconRenderer);
    	
    	mScrollPane = new JScrollPane();
    	mScrollPane.setViewportView(mTable);
    	
    	mPanel = new JPanel();
        mPanel.add(mScrollPane);
       /* mPanel.add(loginButton);
        mPanel.add(rushPurchaseButton);*/
    	mPanel.setOpaque(true);
    	
    	
    	this.getContentPane().add(mPanel,BorderLayout.CENTER);
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		JDFrame frame = new JDFrame(generateJDAccountWrapperList());
		frame.createAndShowGui();
	}
	
	public static List<JDAccountWrapper> generateJDAccountWrapperList() {
		
		List<JDAccountWrapper> jdAccountWrapperList = new ArrayList<JDAccountWrapper>();

		for(int index=1;index<20;index++) {
			JDAccount jdAccount = new JDAccount();
			jdAccount.setUserName("jd_4c2b3d8b3d6c"+index);
			jdAccount.setLoginPassWd("ananky684");
			HttpClientRequestHandler requestHandler = new HttpClientRequestHandler();
			JDAccountWrapper jdAccountWraper = new JDAccountWrapper();
			jdAccountWraper.setAccountState(AccountState.OFF_LINE);
			jdAccountWraper.setJdAccount(jdAccount);
			jdAccountWraper.setRequestHandler(requestHandler);
			jdAccountWrapperList.add(jdAccountWraper);
		}
		
		return jdAccountWrapperList;
		
	}

}
