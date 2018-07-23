package com.utils.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 
* @ClassName: PoiUtils
* @Description: Office工具类
* @author: leisure
* @date: 2018年5月21日 下午4:58:36
 */
public class PoiUtils {
	public static final String EXCEL_XLS = "xls";
	public static final String EXCEL_XLSX = "xlsx";
	
	public static void main(String[] args) {
		//getExcelColumnValues("D:/非小号ETH.xls",0,2,2,96);
		//getExcelColumnValues("D:/非小号ETH.xls",0);
		//getExcelColumnListValues("D:/stock_kline.xls", 0, 4, 1, 3314);
		getExcelColumnMapValues("D:/stock_kline.xls", 0, 0,10);
	}
	/**
	 * 
	* @Title: getExcelColumnValues
	* @Description: 获取Excel表所有列包含的数据值(String类型)---除去第一行
	* @param filePath
	* @param sheetIndex
	* @return Set<String>
	* @author leisure
	* @date 2018年5月21日下午5:22:29
	 */
	public static Set<String> getExcelColumnValues(String filePath,int sheetIndex){
		if(null==filePath || 0 > sheetIndex ) {
			return null;
		}
		Set<String> valueSet = new LinkedHashSet<String>();
		try {
			Workbook wb = getExcelWorkbok(new File(filePath));
			if(null==wb) {
				return null;
			}
			//获取sheet
			Sheet sheet = wb.getSheetAt(sheetIndex);
			if(null==sheet) {
				return null;
			}
			//不包括第一行----第一行是标题行 这里需要获取的是Value
			for(int i=sheet.getFirstRowNum()+1;i<sheet.getLastRowNum();i++) {
				Row row = sheet.getRow(i);
				if(null!=row) {
					for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++) {
					Cell cell = row.getCell(j);
					if(null!=cell) {
						RichTextString rts = cell.getRichStringCellValue();
						if(null!=rts) {
						  String cellValue = rts.getString();
						  if(null!=cellValue && !"".equals(cellValue.trim())) {
						    valueSet.add(cellValue);
						  }
						}
					}
				  }
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//遍历valueSet
		int count = 1;
		Iterator<String> iterator = valueSet.iterator();
		while(iterator.hasNext()) {
			System.out.println("valueSet[" +count+"] = "+iterator.next());
			count++;
		}
		return valueSet;
	}
	/**
	 * 
	* @Title: getExcelColumnValues
	* @Description: 获取Excel表指定列的数据值(String类型)
	* @param filePath
	* @param sheetIndex sheet的Index
	* @param columnIndex
	* @param rowStart  起始行数
	* @param rowLast   最后的行数
	* @return Set<String>
	* @author leisure
	* @date 2018年5月21日下午5:18:48
	 */
	public static Set<String> getExcelColumnValues(String filePath,int sheetIndex,int columnIndex,int rowStart,int rowLast){
		if(null==filePath || 0 > sheetIndex || 0 > columnIndex || rowStart >rowLast) {
			return null;
		}
		Set<String> valueSet = new LinkedHashSet<String>();
		try {
			Workbook wb = getExcelWorkbok(new File(filePath));
			if(null==wb) {
				return null;
			}
			//获取sheet
			Sheet sheet = wb.getSheetAt(sheetIndex);
			if(null==sheet) {
				return null;
			}
			for(int i=rowStart;i<rowLast;i++) {
				Row row = sheet.getRow(i);
				if(null!=row) {
					Cell cell = row.getCell(columnIndex);
					if(null!=cell) {
						//RichTextString rts = 
						CellType cellType = cell.getCellTypeEnum();
						Object o = null;
						switch(cellType) {
						case _NONE://unknow type
							 break;
						case NUMERIC:
							 o = cell.getNumericCellValue();
							 break;
						case STRING:
							 o = cell.getStringCellValue();
							 break;
						case FORMULA:
							 o = cell.getCellFormula();
							 break;
						case BLANK:
							 break;
						case BOOLEAN:
							 break;
						case ERROR:
							 break;
						}
						String cellValue = String.valueOf(o);
						valueSet.add(cellValue);
					}
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//遍历valueSet
		int count = 1;
		Iterator<String> iterator = valueSet.iterator();
		while(iterator.hasNext()) {
			System.out.println("valueSet[" +count+"] = "+iterator.next());
			count++;
		}
		return valueSet;
	}
	/**
	 * 
	* @Title: getExcelColumnListValues
	* @Description: 获取指定列数据list
	* @param filePath
	* @param sheetIndex
	* @param columnIndex
	* @param rowStart
	* @param rowLast
	* @return List<String>
	* @author leisure
	* @date 2018年6月14日下午4:49:03
	 */
	public static List<String> getExcelColumnListValues(String filePath,int sheetIndex,int columnIndex,int rowStart,int rowLast){
		if(null==filePath || 0 > sheetIndex || 0 > columnIndex || rowStart >rowLast) {
			return null;
		}
		List<String> valueList = new ArrayList<String>();
		try {
			Workbook wb = getExcelWorkbok(new File(filePath));
			if(null==wb) {
				return null;
			}
			//获取sheet
			Sheet sheet = wb.getSheetAt(sheetIndex);
			if(null==sheet) {
				return null;
			}
			for(int i=rowStart;i<rowLast;i++) {
				Row row = sheet.getRow(i);
				if(null!=row) {
					Cell cell = row.getCell(columnIndex);
					if(null!=cell) {
						//RichTextString rts = 
						CellType cellType = cell.getCellTypeEnum();
						Object o = null;
						switch(cellType) {
						case _NONE://unknow type
							 break;
						case NUMERIC:
							 o = cell.getNumericCellValue();
							 break;
						case STRING:
							 o = cell.getStringCellValue();
							 break;
						case FORMULA:
							 o = cell.getCellFormula();
							 break;
						case BLANK:
							 break;
						case BOOLEAN:
							 break;
						case ERROR:
							 break;
						}
						String cellValue = String.valueOf(o);
						valueList.add(cellValue);
					}
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//遍历valueSet
		int count = 1;
		Iterator<String> iterator = valueList.iterator();
		while(iterator.hasNext()) {
			System.out.println("valueSet[" +count+"] = "+iterator.next());
			count++;
		}
		return valueList;
	}
	/**
	 * 
	* @Title: getExcelColumnMapValues
	* @Description: 读出Excel表中的数据
	* @param filePath
	* @param sheetIndex
	* @param rowLast
	* @return List<Map<String,String>>
	* @author leisure
	* @date 2018年6月15日上午11:04:50
	 */
	public static List<Map<String,String>> getExcelColumnMapValues(String filePath,int sheetIndex,int rowStart,int rowLast){
		if(null==filePath || 0 > sheetIndex ) {
			return null;
		}
		
		List<Map<String,String>> mList = new ArrayList<Map<String,String>>();
		try {
			Workbook wb = getExcelWorkbok(new File(filePath));
			if(null==wb) {
				return null;
			}
			//获取sheet
			Sheet sheet = wb.getSheetAt(sheetIndex);
			if(null==sheet) {
				return null;
			}
			if(0==rowStart) {
				rowStart ++;
			}
			if(0==rowLast) {
				rowLast = sheet.getLastRowNum(); 
			}
			//不包括第一行----第一行是标题行 这里需要获取的是Value
			//for(int i=sheet.getFirstRowNum()+1;i<rowLast;i++) {
			for(int i=rowStart;i<rowLast;i++) {
				Row row = sheet.getRow(i);
				if(null!=row) {
					Map<String,String> map = new HashMap<String,String>();
					for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++) {
					Cell cell = row.getCell(j);
					if(null!=cell) {
						CellType cellType = cell.getCellTypeEnum();
						Object o = null;
						switch(cellType) {
						case _NONE://unknow type
							 break;
						case NUMERIC:
							 o = cell.getNumericCellValue();
							 break;
						case STRING:
							 o = cell.getStringCellValue();
							 break;
						case FORMULA:
							 o = cell.getCellFormula();
							 break;
						case BLANK:
							 break;
						case BOOLEAN:
							 break;
						case ERROR:
							 break;
						}
						String cellValue = String.valueOf(o);
						map.put("column_"+j, cellValue);
						
					}
				  }
					mList.add(map);
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		//遍历mList
		for(int index=0;index<mList.size();index++) {
			Map<String,String> map = mList.get(index);
			Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
			System.out.println("-------------index = "+index);
			while(iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				String key = entry.getKey();
				String value = entry.getValue();
				System.out.println(key+" : "+value);
			}
		}
		return mList;
	}
	 /**
     * 判断Excel的版本,获取Workbook
     * @param in
     * @param filename
     * @return
     * @throws IOException
     */
    public static Workbook getExcelWorkbok(File file) throws IOException{
        Workbook wb = null;
        FileInputStream in = new FileInputStream(file);
        if(file.getName().endsWith(EXCEL_XLS)){     //Excel&nbsp;2003
            wb = new HSSFWorkbook(in);
        }else if(file.getName().endsWith(EXCEL_XLSX)){    // Excel 2007/2010
            wb = new XSSFWorkbook(in);
        }
        return wb;
    }

}
