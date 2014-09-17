package com.subway.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

public class DbBackAction {
	 private static final Logger log=Logger.getLogger(DbBackAction.class);
	    public static void  export(HSSFWorkbook wb,HSSFSheet sheet,String sheetname,String tip,List<String> title,
	            int[] redcloumn,List<String[]> values) {
	         export(wb,sheet, tip, title, redcloumn, values, (short)75);
	    }
	    
	    public static HSSFSheet createMySheet(HSSFWorkbook wb,String sheetname)
	    {
	    	HSSFSheet sheet = DbBackAction.createSheet(wb, sheetname);
	    	return sheet;
	    }
	   
	    public static void export(HSSFWorkbook wb,HSSFSheet sheet,String tip,List<String> title,
	            int[] redcloumn,List<String[]> values,short hight) {
	    	//HSSFWorkbook wb = new HSSFWorkbook();
	        // 创建一个webbook，对应一个Excel文件
	        
	        // 在webbook中添加一个sheet,对应Excel文件中的sheet
	        
	        // 在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
	        HSSFRow row = DbBackAction.createRow(sheet, 0, (short) 15.625 * hight);
	        // 创建单元格，并设置值表头 设置表头居中
	        Font font_title = DbBackAction.createFont(wb, HSSFFont.BOLDWEIGHT_BOLD,
	                HSSFColor.BLACK.index, (short) 11);
	        Font font_red = DbBackAction.createFont(wb, HSSFFont.BOLDWEIGHT_BOLD,
	                HSSFColor.RED.index, (short) 11);
	        Font font = DbBackAction.createFont(wb, HSSFFont.BOLDWEIGHT_NORMAL,
	                HSSFColor.BLACK.index, (short) 11);

	        CellStyle style_black_0 = DbBackAction.createCellStyle(wb,
	                HSSFColor.LIME.index, HSSFColor.LIME.index,
	                HSSFCellStyle.ALIGN_LEFT, font_title);
	        style_black_0.setWrapText(true);
	        CellStyle style_black = DbBackAction.createCellStyle(wb,
	                HSSFColor.LIGHT_GREEN.index, HSSFColor.LIGHT_GREEN.index,
	                HSSFCellStyle.ALIGN_LEFT, font_title);
	        CellStyle style_red = DbBackAction.createCellStyle(wb,
	                HSSFColor.LIGHT_GREEN.index, HSSFColor.LIGHT_GREEN.index,
	                HSSFCellStyle.ALIGN_LEFT, font_red);
	      

	        HSSFCell cell = null;
	        for (int i = 0; i < title.size(); i++) {
	            cell = DbBackAction.createCell(row, i, style_black_0);
	        }
	        int num = DbBackAction.mergeCell(sheet, 0, 0, 0, title.size()-1);
	        cell = row.getCell(num);
	        cell.setCellValue(tip);

	        // 设置值表头
	        row = DbBackAction.createRow(sheet, 1, (short) 15.625 * 25);
	        
	        for (int i = 0; i < title.size(); i++) {
	            cell = DbBackAction.createCell(row, i, style_black);
	            cell.setCellValue(title.get(i));
	        }
	        
	        for(int i=0;i< redcloumn.length;i++){
	            cell=row.getCell(redcloumn[i]);
	            cell.setCellStyle(style_red);
	            
	        }
	        
	        // 第六步，将文件存到指定位置
	    }
	    
	    public static void writeInfo(HSSFWorkbook wb,HSSFSheet sheet,List<String> title,List<String[]> values,int index)
	    {
	    	HSSFRow row=null;
	    	Font font = DbBackAction.createFont(wb, HSSFFont.BOLDWEIGHT_NORMAL,
	                HSSFColor.BLACK.index, (short) 11);
	    	  CellStyle style = DbBackAction.createCellStyle(wb,
	                  HSSFColor.LIGHT_GREEN.index, HSSFColor.LIGHT_GREEN.index,
	                  HSSFCellStyle.ALIGN_LEFT, font);
	    	for (int i = 0; i < values.size(); i++) {
	            row = DbBackAction.createRow(sheet, i + index+2, (short) 15.625 * 25);
	            
	            // 第四步，创建单元格，并设置值
	            for(int j=0;j < title.size();j++){
	            	DbBackAction.createCell(row, j, style).setCellValue(values.get(i)[j]);
	            }
	        }
	    }

	    
	    /*public static void main(String[] args) {
	    	HSSFWorkbook wb = new HSSFWorkbook();
	    	List<String> title=new ArrayList<String>();
	    	List<String[]> values=new ArrayList<String[]>();
	    	int[] recol=new int[3];
	    	//ExclUtil.export(wb,"firstSheet", "liyanfeng", title, recol , values);
	    	ExclUtil.writeWorkbook(wb, "test.xls");
		}*/
	    /**
	     * 功能：将HSSFWorkbook写入Excel文件
	     * @param     wb        HSSFWorkbook
	     * @param     absPath    写入文件的相对路径
	     * @param     wbName    文件名
	     */
	    public static boolean writeWorkbook(HSSFWorkbook wb,String fileName){
	        FileOutputStream fos=null;
	        try {
	            fos=new FileOutputStream(fileName);
	            wb.write(fos);
	        } catch (FileNotFoundException e) {
	            log.error(new StringBuffer("[").append(e.getMessage()).append("]").append(e.getCause()));
	        } catch (IOException e) {
	            log.error(new StringBuffer("[").append(e.getMessage()).append("]").append(e.getCause()));
	        } finally{
	            try {
	                if(fos!=null){
	                    fos.close();
	                    return true;
	                }
	            } catch (IOException e) {
	                log.error(new StringBuffer("[").append(e.getMessage()).append("]").append(e.getCause()));
	            }
	        }
	        return false;
	    }
	    public static byte[] writeWorkbook(HSSFWorkbook wb,OutputStream fos){
	        
	        try {    
	            ByteArrayOutputStream os = (ByteArrayOutputStream)fos;
	            wb.write(os);
	            return os.toByteArray();
	        } catch (FileNotFoundException e) {
	            log.error(new StringBuffer("[").append(e.getMessage()).append("]").append(e.getCause()));
	        } catch (IOException e) {
	            log.error(new StringBuffer("[").append(e.getMessage()).append("]").append(e.getCause()));
	        } finally{
	            try {
	                if(fos!=null){
	                    fos.close();
	                }
	            } catch (IOException e) {
	                log.error(new StringBuffer("[").append(e.getMessage()).append("]").append(e.getCause()));
	            }
	        }
	        return null;
	    }

	    
	    /**
	     * 功能：创建HSSFSheet工作簿
	     * @param     wb    HSSFWorkbook
	     * @param     sheetName    String
	     * @return    HSSFSheet
	     */
	    public static HSSFSheet createSheet(HSSFWorkbook wb,String sheetName){
	        HSSFSheet sheet=wb.createSheet(sheetName);
	        sheet.setDefaultColumnWidth(30);
	        sheet.setGridsPrinted(false);
	        sheet.setDisplayGridlines(false);
	        return sheet;
	    }
	    /**
	     * 功能：创建HSSFRow
	     * @param     sheet    HSSFSheet
	     * @param     rowNum    int
	     * @param     height    int
	     * @return    HSSFRow
	     */
	    public static HSSFRow createRow(HSSFSheet sheet,int rowNum,int height){
	        HSSFRow row=sheet.createRow(rowNum);
	        row.setHeight((short)height);
	        return row;
	    }
	    /**
	     * 功能：创建CellStyle样式
	     * @param     wb                HSSFWorkbook    
	     * @param     backgroundColor    背景色    
	     * @param     foregroundColor    前置色
	     * @param    font            字体
	     * @return    CellStyle
	     */
	    public static CellStyle createCellStyle(HSSFWorkbook wb,short backgroundColor,short foregroundColor, short halign,Font font){
	        CellStyle cs=wb.createCellStyle();
	        cs.setAlignment(halign);
	        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        cs.setFillBackgroundColor(backgroundColor);
	        cs.setFillForegroundColor(foregroundColor);
	        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);        
	        cs.setFont(font);
	        cs.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
	        cs.setBorderLeft( HSSFCellStyle.BORDER_THIN);  
	        cs.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	        cs.setBorderTop( HSSFCellStyle.BORDER_THIN );        
	        cs.setBottomBorderColor(HSSFColor.GREY_25_PERCENT.index); 
	        cs.setLeftBorderColor(HSSFColor.GREY_25_PERCENT.index); 
	        cs.setRightBorderColor(HSSFColor.GREY_25_PERCENT.index); 
	        cs.setTopBorderColor(HSSFColor.GREY_25_PERCENT.index); 
	    
	        return cs;
	    }
	    /**
	     * 功能：创建带边框的CellStyle样式
	     * @param     wb                HSSFWorkbook    
	     * @param     backgroundColor    背景色    
	     * @param     foregroundColor    前置色
	     * @param    font            字体
	     * @return    CellStyle
	     */
	    public static CellStyle createBorderCellStyle(HSSFWorkbook wb,short backgroundColor,short foregroundColor,short halign,Font font){
	        CellStyle cs=wb.createCellStyle();
	        cs.setAlignment(halign);
	        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	        cs.setFillBackgroundColor(backgroundColor);
	        cs.setFillForegroundColor(foregroundColor);
	        cs.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        cs.setFont(font);
	        cs.setBorderLeft(CellStyle.BORDER_DASHED);
	        cs.setBorderRight(CellStyle.BORDER_DASHED);
	        cs.setBorderTop(CellStyle.BORDER_DASHED);
	        cs.setBorderBottom(CellStyle.BORDER_DASHED);  
	        return cs;
	    }
	    /**
	     * 功能：创建CELL
	     * @param     row        HSSFRow    
	     * @param     cellNum    int
	     * @param     style    HSSFStyle
	     * @return    HSSFCell
	     */
	    public static HSSFCell createCell(HSSFRow row,int cellNum,CellStyle style){
	        HSSFCell cell=row.createCell(cellNum);
	        cell.setCellStyle(style);
	        return cell;
	    }
	    /**
	     * 功能：合并单元格
	     * @param     sheet        HSSFSheet
	     * @param     firstRow    int
	     * @param     lastRow        int
	     * @param     firstColumn    int
	     * @param     lastColumn    int
	     * @return    int            合并区域号码
	     */
	    public static int mergeCell(HSSFSheet sheet,int firstRow,int lastRow,int firstColumn,int lastColumn){
	        return sheet.addMergedRegion(new CellRangeAddress(firstRow,lastRow,firstColumn,lastColumn));    
	    }
	    /**
	     * 功能：创建字体
	     * @param     wb            HSSFWorkbook    
	     * @param     boldweight    short
	     * @param     color        short
	     * @return    Font    
	     */
	    public static Font createFont(HSSFWorkbook wb,short boldweight,short color,short size){
	        Font font=wb.createFont();
	        font.setBoldweight(boldweight);
	        font.setColor(color);
	        font.setFontHeightInPoints(size);
	        return font;
	    }
	    /**
	     * 设置合并单元格的边框样式
	     * @param    sheet    HSSFSheet    
	     * @param     ca        CellRangAddress
	     * @param     style    CellStyle
	     */
	    public static void setRegionStyle(HSSFSheet sheet, CellRangeAddress ca,CellStyle style) {  
	        for (int i = ca.getFirstRow(); i <= ca.getLastRow(); i++) {  
	            HSSFRow row = HSSFCellUtil.getRow(i, sheet);  
	            for (int j = ca.getFirstColumn(); j <= ca.getLastColumn(); j++) {  
	                HSSFCell cell = HSSFCellUtil.getCell(row, j);  
	                cell.setCellStyle(style);  
	            }  
	        }  
	    }  
}
