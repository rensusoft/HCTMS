package com.rensu.education.hctms.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportExcel {
	//判断excel版本
	public static Workbook openWorkbook(InputStream in,String filename)throws IOException
	{
		Workbook wb = null;
		if(filename.endsWith(".xlsx")){
			wb = new XSSFWorkbook(in);//Excel 2007
		} else if(filename.endsWith(".xls")){
			wb = new HSSFWorkbook(in);//Excel 2003
		}
		return wb;
	}
	
	public void getExcelData(String fileName) throws Exception 
	{
		InputStream in = new FileInputStream(fileName);
		Workbook wb = openWorkbook(in, fileName);
		Sheet sheet = (Sheet)wb.getSheetAt(0);
		Row row = null;
		Cell cell = null;
		
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
		
		for(int r=0; r<totalRows; r++) 
		{
			row = sheet.getRow(r);
			System.out.print("第" + r + "行");
			for(int c = 0; c < totalCells; c++)
			{
				cell = row.getCell(c);
				String cellValue = getCellValue(cell);
				System.out.println(cellValue);
			}
			System.out.println();
		}
	}
	
	/**
	 *  读取单元格的值 
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell){
		String cellValue = "";
		if(null != cell){
			// 以下是判断数据的类型
			switch (cell.getCellType()) 
			{
				case HSSFCell.CELL_TYPE_NUMERIC: // 数字
					//时间格式
					if(HSSFDateUtil.isCellDateFormatted(cell)){
						Date dd = cell.getDateCellValue();
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						cellValue = df.format(dd);
					}else{
						cellValue = cell.getNumericCellValue() + "";
					}
					break;

				case HSSFCell.CELL_TYPE_STRING: // 字符串
					cellValue = cell.getStringCellValue();
					break;

				case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
					cellValue = cell.getBooleanCellValue() + "";
					break;

				case HSSFCell.CELL_TYPE_FORMULA: // 公式
					try {
						cellValue = String.valueOf(cell.getNumericCellValue());
					} catch (IllegalStateException e) {
						cellValue = String.valueOf(cell.getRichStringCellValue());
					}
					break;

				case HSSFCell.CELL_TYPE_BLANK: // 空值
					cellValue = "";
					break;

				case HSSFCell.CELL_TYPE_ERROR: // 故障
					cellValue = "非法字符";
					break;

				default:
					cellValue = "未知类型";
					break;
			}
		}
		return cellValue;
	}
	
	public static void main(String[] args) throws Exception
	{
		String fileName = "D:/test2.xlsx";
		//String fileName = "C:/test.xlsx";
		ImportExcel upload = new ImportExcel();
		upload.getExcelData(fileName);
	}
}
