package com.jingdong.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.opencv.core.Point;

import com.jingdong.account.JDAccount;
import com.jingdong.account.JDAccountWrapper;
import com.jingdong.account.JDRegisterAndLogin;
import com.jingdong.image.OpencvHandler;
import com.jingdong.test.CalculateTraceArray;
import com.jingdong.test.GenerateTraceArray;
import com.utils.map.MapUtils;
import com.utils.request.HttpClientRequestHandler;

public class JDAccountTableModel extends AbstractTableModel{
	
	private List<JDAccountWrapper> jdAccountWrapperList;
	private Vector<String> columnNames;
	private Vector<Vector<Object>> data;
	public Log log = LogFactory.getLog(JDAccountTableModel.class);
	public static final int INIT_ACCOUNT_COUNT = 10;
	
	
	public JDAccountTableModel() {
		initColumnNames();
		this.data = new Vector<Vector<Object>>();
		this.jdAccountWrapperList = new ArrayList<JDAccountWrapper>();
		//初始化可以添加INIT_ACCOUNT_COUNT个账号
		for(int i=0;i<INIT_ACCOUNT_COUNT;i++) {
			Vector<Object> rowData = new Vector<Object>();
			for(int j=0;j<this.columnNames.size();j++) {
				rowData.add("");
			}
		}
	}
	
	public JDAccountTableModel(List<JDAccountWrapper> jdAccountWrapperList) {
		this.jdAccountWrapperList = jdAccountWrapperList;
		
		initColumnNames();
		
		data = new Vector<Vector<Object>>();
		if(null!=jdAccountWrapperList && 0!=jdAccountWrapperList.size()) {
			for(int index=0;index<jdAccountWrapperList.size();index++) {
				JDAccountWrapper jdAccountWrapper = jdAccountWrapperList.get(index);
				JDRegisterAndLogin jdRegisterAndLogin = new JDRegisterAndLogin();
				Map<String, Object> visitJDLoginHomePageMap = jdRegisterAndLogin.visitJDLoginHomePage(jdAccountWrapper);
				MapUtils.traversalMap(visitJDLoginHomePageMap);
				boolean isCheckCode = jdRegisterAndLogin.checkAuthCode(jdAccountWrapper);
				log.info("isCheckCode = "+isCheckCode);
				System.out.println("isCheckCode = "+isCheckCode);
				HttpClientRequestHandler requestHandler = jdAccountWrapper.getRequestHandler();
				String authCodePath = "";
				if(isCheckCode) {
					//滑动模块
					Map<String,Object> slidingImageMap = jdRegisterAndLogin.fetchSlidingImage(requestHandler);
					Point maxPoint = OpencvHandler.matchTemplate(String.valueOf(slidingImageMap.get("bgImgPath")), String.valueOf(slidingImageMap.get("patchImgPath")));
					//GenerateTraceArray
					long[][] c = GenerateTraceArray.generateArray((int)maxPoint.x, (int)maxPoint.y, 35);
					long time = c[c.length-1][2]-c[0][2];
					System.out.println("time = "+time);
					try {
						Thread.sleep(time);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//CalculateTraceArray
					String d = CalculateTraceArray.gc(c);
					System.out.println("d = "+d);
					String s = jdRegisterAndLogin.getJdtdmapSessionId(requestHandler);
					System.out.println("s = "+s);
					slidingImageMap.put("traceValueD", d);
					//slidingImageMap.put("jdtdmapSessionId", s);
					slidingImageMap.put("jdtdmapSessionId", s);
					jdRegisterAndLogin.submitSlidingVerification(requestHandler, slidingImageMap);
					//获取验证码
					jdRegisterAndLogin.fetchAuthCode(requestHandler);
					
					
				}
				
				Vector<Object> rowData = new Vector<Object>();
				JDAccount jdAccount = jdAccountWrapper.getJdAccount();
				rowData.add(index);//后面可以通过index取list中的某项JDAccountWrapper
				rowData.add(jdAccount.getUserName());//登录名
				rowData.add(jdAccount.getLoginPassWd());//登录密码
				rowData.add(new String("checkCode"));//提示输入验证码
				authCodePath = requestHandler.getAuthCodeImgPath();
				System.out.println("验证码路径: "+authCodePath);
				if(null!=authCodePath && ""!=authCodePath) {					
				   rowData.add(ImageIconUtils.createImageIconByFilePath(jdAccountWrapper.getRequestHandler().getAuthCodeImgPath()));//本次登录的验证码
				}else {
				   rowData.add(new ImageIcon());
				}
				rowData.add(jdAccountWrapper.getAccountState());//账号登录状态	
				data.add(rowData);
			}
		}
	}
	/**
	 * 
	* @Title: initColumnNames
	* @Description: 初始化columnNames
	* @author leisure
	* @date 2018年7月25日下午5:26:33
	 */
	public void initColumnNames() {
		this.columnNames = new Vector<String>();
		this.columnNames.add("账户序号");
		this.columnNames.add("用户名");		
		this.columnNames.add("登录密码");
		this.columnNames.add("验证码");		
		this.columnNames.add("验证码图片");
		this.columnNames.add("登录状态");
		
	}
	
	

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}
	@Override
	public String getColumnName(int col) {
	     return columnNames.get(col).toString();
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex).get(columnIndex);
	}
	@Override
	public void setValueAt(Object value, int row, int col) {
	     data.get(row).set(col,value);
	     fireTableCellUpdated(row, col);
	}
	/*
	* JTable uses this method to determine the default renderer/
	* editor for each cell.  If we didn't implement this method,
	* then the last column would contain text ("true"/"false"),
	* rather than a check box.
	*/
	@Override
	public Class getColumnClass(int c) {
		 Object t = getValueAt(0, c);
		 //System.out.println("getColumnClass t = "+t.toString()+"---instance of t = "+t.getClass());
	     return getValueAt(0, c).getClass();
	}
	/*
	* Don't need to implement this method unless your table's
	* editable.
	*/
	@Override
	public boolean isCellEditable(int row, int col) {
		//用户名 登录密码 和验证码是可以修改的 其它的暂不支持修改
		if(col>0 && col<4) {
			return true;
		}
		return false;		
	}

}
