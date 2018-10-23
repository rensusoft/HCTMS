package com.rensu.education.hctms.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlUtil {
	private static WritableFont HeaderFont = new WritableFont(WritableFont.ARIAL,
			12, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
			jxl.format.Colour.BLACK);
	private static WritableCellFormat HeaderFormat = new WritableCellFormat(
			HeaderFont);
	private static WritableFont DataFont = new WritableFont(WritableFont.ARIAL,
			10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
			jxl.format.Colour.BLACK);
	private static WritableCellFormat DataFormat = new WritableCellFormat(
			DataFont);
	private File fileExcel = null;
	private WritableWorkbook workbook = null;
	private WritableSheet sheet = null;

	public JxlUtil(String filename) {
		fileExcel = new File(filename);
		try {
			workbook = Workbook.createWorkbook(fileExcel);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建sheet
	 * 
	 * @param sheetname
	 *          sheet的名字
	 * @param index
	 *          sheet的序号
	 */
	public void buildSheet(String sheetname, int index) {
		if (workbook != null)
			sheet = workbook.createSheet(sheetname, index);
	}

	/**
	 * 增加头单元格
	 * 
	 * @param headerValue
	 *          头内容
	 * @param row
	 *          当前sheet的行号
	 * @param col
	 *          当前sheet的列号
	 */
	public void addHeaderCell(String headerValue, int row, int col) {
		try {
			if (headerValue != null) {
				Label label = new Label(col, row, headerValue, HeaderFormat);
				sheet.addCell(label);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 增加普通单元格
	 * 
	 * @param cellValue
	 *          单元格内容
	 * @param row
	 *          当前sheet的行号
	 * @param col
	 *          当前sheet的列号
	 */
	public void addDataCell(String cellValue, int row, int col) {
		try {
			if (cellValue != null) {
				Label label = new Label(col, row, cellValue, DataFormat);
				sheet.addCell(label);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 增加普通单元格
	 * 
	 * @param cellValue
     *          单元格内容
	 * @param row
     *          当前sheet的行号
	 * @param col
     *          当前sheet的列号
	 * @param rowSpan
     *          当前sheet的行高倍数
     * @author 
	 */
    public void addDataCell(String cellValue, int row, int col, int rowSpan) {
        try {
            if (cellValue != null) {
                WritableCellFormat gFormat = DataFormat;
                if(rowSpan > 1){
                    sheet.setRowView(row, rowSpan * sheet.getSettings().getDefaultRowHeight());
                    gFormat = new WritableCellFormat();  
                    gFormat.setWrap(true);
                }

                Label label = new Label(col, row, cellValue, gFormat);
                sheet.addCell(label);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }	

	/**
	 * 返回创建好的excel文件
	 * 
	 * @return excel文件
	 */
	public File getFileExcel() {
		try {
			workbook.write();
			workbook.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return fileExcel;
	}

	public static void main(String[] args) {
		JxlUtil jxl = new JxlUtil("c:\\test.xls");
		for (int k = 0; k < 3; k++) {
			jxl.buildSheet("测试" + k, k);
			for (int i = 0; i < 10; i++) {
				jxl.addHeaderCell("测试" + k + "_头" + i, 0, i);
				for (int j = 1; j < 10; j++) {
					jxl.addDataCell("内容" + j + i, j, i);
				}
			}
		}
		File file = jxl.getFileExcel();
		System.out.println(file.exists());
	}
	
	/**
	* Method downLoadExcel.
	* 下载Excel文件
	*/
	public static void downLoadExcel(File fileExcel,HttpServletRequest req,HttpServletResponse rsp) {
		try {
			//往客户端输出的流
			ServletOutputStream out = rsp.getOutputStream();
			//输入流
			FileInputStream hFile = new FileInputStream(fileExcel);
			//创建一个hFile大小的字节型数组
			byte[] data = new byte[hFile.available()];
			//头信息的编码默认为ISO-8859-1，把文件名转成默认编码传递
			String userAgent = req.getHeader("User-Agent");
			byte[] bytes = null;
			String fileName = fileExcel.getName();
			bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8");
			fileName = userAgent.contains("MSIE") ? URLEncoder.encode(fileName, "UTF-8"):new String(bytes, "ISO-8859-1");
			//头信息
			rsp.addHeader(
				"Content-Disposition",
				"attachment; filename=" + fileName);
			rsp.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
			System.out.println("filename=" + fileName);
			//头信息
			rsp.addIntHeader("Content-Length", hFile.available());
			//读数据到数组
			hFile.read(data);
			//记得要关闭输入流
			hFile.close();
			rsp.setContentType("application/OCTET-STREAM;charset=UTF-8");
            rsp.setCharacterEncoding("UTF-8");
			out.write(data);
			out.close();

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//删除文件
			fileExcel.delete();
		}
	}
	
	/**
	* Method downLoadTxtFile.
	* title中后缀加.xls即可导出excel，cont为导出的内容拼接的字符串（text文本样式） zan 2013-9-2
	* 下载Excel文件
	*/
	public static void downLoadTxtFile(String title, String cont, HttpServletResponse rsp) {
        ServletOutputStream out = null;
        ByteArrayInputStream hFile = null;
	    try {
            hFile = new ByteArrayInputStream(cont.getBytes());
            //头信息的编码默认为ISO-8859-1，把文件名转成默认编码传递
            String fileName = new String(title.getBytes(),"ISO-8859-1");
            //头信息
            rsp.addHeader(
                "Content-Disposition",
                "attachment; filename=" + fileName);
            //头信息 
            rsp.setContentType("text/html;charset=UTF-8");
            //往客户端输出的流
            out = rsp.getOutputStream();
            //创建一个hFile大小的字节型数组
            byte[] data = new byte[8192];
            int bytesRead = 0;
            while((bytesRead = hFile.read(data, 0, 8192))!= -1){
                //读数据到数组
                out.write(data,0,bytesRead);
            }   
            out.flush();
            out.close();
            //记得要关闭输入流
            hFile.close();
        }
        catch (Exception e) {
            try {
                hFile.close();
                if(out != null){
                    out.close();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
	}
	
}
