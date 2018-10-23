package com.rensu.education.hctms.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

public class ExcelUtil {
	/**
     * @Title: createExcelTemplate 
     * @Description: 生成Excel导入模板  
     * @param @param filePath  Excel文件路径
     * @param @param handers   Excel列标题(数组)
     * @param @param downData  下拉框数据(数组)
     * @param @param downRows  下拉列的序号(数组,序号从0开始)
     * @return void
     * @throws
     */
    public static void createExcelTemplate(HttpServletResponse response,String fileName, String[] handers, 
            List<String[]> downData, String[] downRows) throws IOException{
//    	OutputStream os = response.getOutputStream();// 取得输出流      
    	FileOutputStream os = new FileOutputStream("f:\\"+fileName+".xlsx");
        HSSFWorkbook wb = new HSSFWorkbook();//创建工作薄
        //表头样式
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        //字体样式
        HSSFFont fontStyle = wb.createFont();    
//        fontStyle.setFontName("微软雅黑");    
        fontStyle.setFontHeightInPoints((short)12);    
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); 
        style.setFont(fontStyle);
        
        //新建sheet
        HSSFSheet sheet1 = wb.createSheet("Sheet1");
        HSSFSheet sheet2 = wb.createSheet("Sheet2");
        
        //生成sheet1内容
        HSSFRow rowFirst = sheet1.createRow(0);//第一个sheet的第一行为标题
        //写标题
        for(int i=0;i<handers.length;i++){
            HSSFCell cell = rowFirst.createCell(i); //获取第一行的每个单元格
            sheet1.setColumnWidth(i, 4000); //设置每列的列宽
            cell.setCellStyle(style); //加样式
            cell.setCellValue(handers[i]); //往单元格里写数据
        }
        
        //设置下拉框数据
        String[] arr = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        int index = 0;
        HSSFRow row = null;
        for(int r=0;r<downRows.length;r++){
            String[] dlData = downData.get(r);//获取下拉对象
            int rownum = Integer.parseInt(downRows[r]);
            
            if(dlData.length<5){ //255以内的下拉
                //255以内的下拉,参数分别是：作用的sheet、下拉内容数组、起始行、终止行、起始列、终止列
                sheet1.addValidationData(setDataValidation(sheet1, dlData, 1, 50000, rownum ,rownum)); //超过255个报错 
            } else { //255以上的下拉，即下拉列表元素很多的情况
                
                //1、设置有效性
                   //String strFormula = "Sheet2!$A$1:$A$5000" ; //Sheet2第A1到A5000作为下拉列表来源数据
                String strFormula = "Sheet2!$"+arr[index]+"$1:$"+arr[index]+"$5000"; //Sheet2第A1到A5000作为下拉列表来源数据
                   sheet2.setColumnWidth(r, 4000); //设置每列的列宽
                   //设置数据有效性加载在哪个单元格上,参数分别是：从sheet2获取A1到A5000作为一个下拉的数据、起始行、终止行、起始列、终止列
                      sheet1.addValidationData(SetDataValidation(strFormula, 1, 50000, rownum, rownum)); //下拉列表元素很多的情况
                      
                      //2、生成sheet2内容
                      for(int j=0;j<dlData.length;j++){
                    if(index==0){ //第1个下拉选项，直接创建行、列
                        row = sheet2.createRow(j); //创建数据行
                        sheet2.setColumnWidth(j, 4000); //设置每列的列宽
                        row.createCell(0).setCellValue(dlData[j]); //设置对应单元格的值
                        
                    } else { //非第1个下拉选项
                        
                        int rowCount = sheet2.getLastRowNum();
                        //System.out.println("========== LastRowNum =========" + rowCount);
                        if(j<=rowCount){ //前面创建过的行，直接获取行，创建列
                            //获取行，创建列
                            sheet2.getRow(j).createCell(index).setCellValue(dlData[j]); //设置对应单元格的值
                            
                        } else { //未创建过的行，直接创建行、创建列
                            sheet2.setColumnWidth(j, 4000); //设置每列的列宽
                            //创建行、创建列
                            sheet2.createRow(j).createCell(index).setCellValue(dlData[j]); //设置对应单元格的值
                        }
                    }
                }
                      index++;
            }
        }
        
//        try {
//            File f = new File(fileName); //写文件
//            //不存在则新增
//            if(!f.getParentFile().exists()){
//                f.getParentFile().mkdirs();
//            }
//            if(!f.exists()){
//                f.createNewFile();
//            }
//            
//            FileOutputStream out = new FileOutputStream(f);
//            out.flush();  
            wb.write(os);
//            out.close(); 
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    
    /**
     * 
     * @Title: SetDataValidation 
     * @Description: 下拉列表元素很多的情况 (255以上的下拉)
     * @param @param strFormula
     * @param @param firstRow   起始行
     * @param @param endRow     终止行
     * @param @param firstCol   起始列
     * @param @param endCol     终止列
     * @param @return
     * @return HSSFDataValidation
     * @throws
     */
    private static HSSFDataValidation SetDataValidation(String strFormula, 
            int firstRow, int endRow, int firstCol, int endCol) {
        
        // 设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(strFormula);
        HSSFDataValidation dataValidation = new HSSFDataValidation(regions,constraint);
        
        dataValidation.createErrorBox("Error", "Error");
        dataValidation.createPromptBox("", null);
    
        return dataValidation;
    }
    
    /**
     * 
     * @Title: setDataValidation 
     * @Description: 下拉列表元素不多的情况(255以内的下拉)
     * @param @param sheet
     * @param @param textList
     * @param @param firstRow
     * @param @param endRow
     * @param @param firstCol
     * @param @param endCol
     * @param @return
     * @return DataValidation
     * @throws
     */
    private static DataValidation setDataValidation(Sheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {

        DataValidationHelper helper = sheet.getDataValidationHelper();
        //加载下拉列表内容
        DataValidationConstraint constraint = helper.createExplicitListConstraint(textList);
        //DVConstraint constraint = new DVConstraint();
        constraint.setExplicitListValues(textList);
        
        //设置数据有效性加载在哪个单元格上。四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);
    
        //数据有效性对象
        DataValidation data_validation = helper.createValidation(constraint, regions);
        //DataValidation data_validation = new DataValidation(regions, constraint);
    
        return data_validation;
    }
    
    /**
     * @Title: getExcel 
     * @Description: 下载指定路径的Excel文件 
     * @param @param url 文件路径
     * @param @param fileName  文件名
     * @param @param response
     * @return void
     * @throws
     */
    public static void getExcel(String url, String fileName, HttpServletResponse response,HttpServletRequest request){
        
         try {  
            
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
            response.setContentType("multipart/form-data");  
            
            //2.设置文件头：最后一个参数是设置下载文件名  
            response.setHeader("Content-disposition", "attachment; filename=\""
                    + encodeChineseDownloadFileName(request, fileName+".xls") +"\"");
//            response.setHeader("Content-Disposition", "attachment;filename="  
//                    + new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + ".xls"); //中文文件名
            
            //通过文件路径获得File对象
            File file = new File(url);  
            
            FileInputStream in = new FileInputStream(file);  
            //3.通过response获取OutputStream对象(out)  
            OutputStream out = new BufferedOutputStream(response.getOutputStream());  
            
            int b = 0;  
            byte[] buffer = new byte[2048];  
            while ((b=in.read(buffer)) != -1){  
                out.write(buffer,0,b); //4.写到输出流(out)中  
            }  
            
            in.close();
            out.flush(); 
            out.close();  
            
        } catch (IOException e) {  
        }  
    }

/**
     * 
     * @Title: encodeChineseDownloadFileName 
     * @Description: TODO(这里用一句话描述这个方法的作用) 
     * @param @param request
     * @param @param pFileName
     * @param @return
     * @param @throws UnsupportedEncodingException
     * @return String
     * @throws
     */
    private static String encodeChineseDownloadFileName(HttpServletRequest request, String pFileName)
            throws UnsupportedEncodingException {
        
        String filename = null;
        String agent = request.getHeader("USER-AGENT");
        //System.out.println("agent==========》"+agent);
        
        if (null != agent) {
            if (-1 != agent.indexOf("Firefox")) {//Firefox  
                filename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(pFileName.getBytes("UTF-8")))) + "?=";
            } else if (-1 != agent.indexOf("Chrome")) {//Chrome  
                filename = new String(pFileName.getBytes(), "ISO8859-1");
            } else {//IE7+  
                filename = java.net.URLEncoder.encode(pFileName, "UTF-8");
                filename = StringUtils.replace(filename, "+", "%20");//替换空格 
            }
        } else {
            filename = pFileName;
        }
        
        return filename;
    }
    
    /**
     * @Title: delFile 
     * @Description: 删除文件
     * @param @param filePath  文件路径
     * @return void
     * @throws
     */
    public static void delFile(String filePath) {     
        java.io.File delFile = new java.io.File(filePath);     
        delFile.delete();     
    }
}
