package com.miaocup.common.utils;

import com.jeesite.common.lang.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 通用Excel导出
 * 
 * @Title: ExcelUtils.java
 * @Package: com.ccssoft.rsas.common.utils
 * @Author: Administrator
 * @Date: 2017年5月5日
 * @Description: 通用Excel导出
 */
public class ExcelUtils {

	private static final String XLS_FILE_SUFFIX = ".xls";

	public static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

	/**
	 * 
	 * @param excelName Excel文件名称
	 * @param sheetName sheet页名称
	 * @param headers 标题
	 * @param dataSet 导出的数据
	 */
	public static void export(String excelName, String sheetName, String[] headers,
			List<List<Object>> dataSet, HttpServletResponse response) {
		InputStream in = null;
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet(sheetName);
			Row row = sheet.createRow(0);
			for (int i = 0; i < headers.length; i++) {
				row.createCell(i).setCellValue(headers[i]);
			}
			int dataSetSize = dataSet.size();
			int columnSize = 0;
			List<Object> columnData = null;
			for (int i = 0; i < dataSetSize; i++) {
				row = sheet.createRow(i + 1);
				columnData = dataSet.get(i);
				columnSize = columnData.size();
				for (int j = 0; j < columnSize; j++) {
					if (columnData.get(j) != null) {
						row.createCell(j).setCellValue(columnData.get(j).toString());
					}
				}
			}
			
			/******设置列宽自适应开始 2018-2-23 增加*******************************/
			
			 //列宽自适应，只对英文和数字有效
		      for (int i = 0; i <= dataSetSize; i++){
		    	  sheet.autoSizeColumn(i);
		      }
		    //获取当前列的宽度，然后对比本列的长度，取最大值
		      for (int columnNum = 0; columnNum <= dataSetSize; columnNum++){
		        int columnWidth = sheet.getColumnWidth(columnNum) / 256;
		        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++){
		        	Row currentRow;
		          //当前行未被使用过
		          if (sheet.getRow(rowNum) == null){
		            currentRow = sheet.createRow(rowNum);
		          }else{
		            currentRow = sheet.getRow(rowNum);
		          }

		          if (currentRow.getCell(columnNum) != null){
		            Cell currentCell = currentRow.getCell(columnNum);
		            int length = currentCell.toString().getBytes().length;
		            if (columnWidth < length){
		              columnWidth = length;
		            }
		          }
		        }
		        sheet.setColumnWidth(columnNum, columnWidth * 256);
		      }
		      /******设置列宽自适应结束*******************************/
		      
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setCharacterEncoding("UTF-8");
			excelName = new String(excelName.getBytes("GBK"), "ISO8859-1") + ".xls";
			response.addHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\"");
			wb.write(response.getOutputStream());
		} catch (Exception e) {
			ExcelUtils.logger.error("Error:", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}



	/**
	 * @param excelName Excel文件名称
	 * @param sheetName sheet页名称
	 * @param headers 标题
	 * @param dataSet 导出的数据
	 */

	public static void export(String excelName, String[] sheetName, List<String[]> headers,
			List<List<List<Object>>> dataSet, HttpServletResponse response) {
		InputStream in = null;
		try {
			Workbook wb = new HSSFWorkbook();
			for(int j=0;j<sheetName.length;j++){
				Sheet sheet = wb.createSheet(sheetName[j]);
				Row row = sheet.createRow(0);
				for (int i = 0; i < headers.get(j).length; i++) {
					row.createCell(i).setCellValue(headers.get(j)[i]);
				}
				int dataSetSize = dataSet.get(j).size();
				int columnSize = 0;
				List<Object> columnData = null;
				for (int i = 0; i < dataSetSize; i++) {
					row = sheet.createRow(i + 1);
					columnData = dataSet.get(j).get(i);
					//columnData = dataSet.get(i);
					columnSize = columnData.size();
					for (int k = 0; k < columnSize; k++) {
						if (columnData.get(k) != null) {
							row.createCell(k)
							.setCellValue(columnData.get(k).toString());
						}
					}
				}
			}
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setCharacterEncoding("UTF-8");
			excelName = new String(excelName.getBytes("GBK"), "ISO8859-1") + ".xls";
			response.addHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\"");
			wb.write(response.getOutputStream());
		} catch (Exception e) {
			ExcelUtils.logger.error("Error:", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}



	/**
	 * 
	 * @param sheetName sheet页名称
	 * @param tplPath 模板路径
	 * @param ignoreLine 忽略的行数
	 * @param dataSet 导出的数据
	 */
	public static void export(String excelName, String sheetName, String tplPath, int ignoreLine,
			List<List<Object>> dataSet, HttpServletResponse response) {
		InputStream in = null;
		InputStream dest = null;
		OutputStream tmp = null;
		try {
			in = new FileInputStream(tplPath);
			String filePath = excelName + XLS_FILE_SUFFIX;
			tmp = new FileOutputStream(filePath);
			IOUtils.copy(in, tmp);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(tmp);
			dest = new FileInputStream(filePath);
			Workbook wb = new HSSFWorkbook(dest);
			Sheet sheet = wb.createSheet(sheetName);
			Row row = sheet.createRow(0);
			int dataSetSize = dataSet.size();
			int columnSize = 0;
			List<Object> columnData = null;
			for (int i = 0; i < dataSetSize; i++) {
				row = sheet.createRow(i + ignoreLine);
				columnData = dataSet.get(i);
				columnSize = columnData.size();
				for (int j = 0; j < columnSize; j++) {
					row.createCell(j)
					.setCellValue(columnData.get(j).toString());
				}
			}
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setCharacterEncoding("UTF-8");
			excelName = new String(excelName.getBytes("GBK"), "ISO8859-1") + ".xls";
			response.addHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\"");
			wb.write(response.getOutputStream());
		} catch (Exception e) {
			ExcelUtils.logger.error("Error:", e);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(tmp);
			IOUtils.closeQuietly(dest);
		}
	}

	/**
	 * 根据Excel单元格的类型获取数据
	 * 
	 * @author 杨杰
	 * @param cell 单元格
	 * @return 处理后的单元格数据
	 */
	public static String getCellValue(Cell cell) {
		String cellValue = "";
		DecimalFormat df = new DecimalFormat("#");
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getRichStringCellValue().getString().trim();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
				return fmt.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			cellValue = df.format(cell.getNumericCellValue()).toString();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		default:
			cellValue = "";
		}
		return cellValue;
	}

	/**
	 * 获取合并单元格的值
	 * 
	 * @author 杨杰
	 * @param sheet 当前的Sheet
	 * @param row 行下标
	 * @param column 列下标
	 * @return 处理后的单元格信息
	 */
	public static String getMergedRegionValue(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return ExcelUtils.getCellValue(fCell);
				}
			}
		}
		return "";
	}

	/**
	 * 判断指定的单元格是否合并单元格
	 * 
	 * @author 杨杰
	 * @param sheet
	 *            当前的Sheet
	 * @param row
	 *            行下标
	 * @param column
	 *            列下标
	 * @return boolean
	 */
	public static boolean isMergedRegion(Sheet sheet, int row, int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();
			if (row >= firstRow && row <= lastRow) {
				if (column >= firstColumn && column <= lastColumn) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * @param title      标题
	 * @param excelName Excel文件名称
	 * @param headerMsg Excel文件名称
	 * @return
	 */
	public static void buildExcel(String title,String excelName,String[] headerMsg ,List<List<Object>> dataSet,HttpServletResponse response) {
		InputStream in = null;
		try {  
		//初始化  
		HSSFWorkbook wk = new HSSFWorkbook();       //创建工作簿  
		  
		/************************************************/  
		/*  分页逻辑                */  
		/*  定义每页最大记录数           */  
		/*  根据最大记录数计算出sheet数    <span style="white-space:pre">    </span>*/  
		/*  有可能最后一页记录达不到最大记录数<span style="white-space:pre">   </span>*/  
		/************************************************/  
		int maxRow = 30000;           // <span style="white-space:pre">    </span>//每页最大记录数  
		int pageSheet = (dataSet.size()/maxRow) + 1;//<span style="white-space:pre">    </span>//分的页数  

		for(int k = 0 ; k<pageSheet; k++){  
			HSSFSheet sheet = wk.createSheet("第" + (k+1) + "页");  
			sheet.setColumnWidth(0,900);// Excel空一行    
			if(StringUtils.isEmpty(title)){
				ExcelUtils.initTitle(headerMsg, title, sheet, wk); 
			} 
			ExcelUtils.initHeader(headerMsg, sheet, wk);   
			creatCell(dataSet, sheet, wk);  
		}  
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setCharacterEncoding("UTF-8");
		excelName = new String(excelName.getBytes("GBK"), "ISO8859-1") + ".xls";
		response.addHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\""); 
		//生成Excel  
		
			wk.write(response.getOutputStream());  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  finally {
			IOUtils.closeQuietly(in);
		}
	}
	/** 
	 * 设置表头 
	 * @author longdage
	 * @param sheet:所在页 
	 * @param wk:工作簿 
	 * */  
	public static void creatCell(List<List<Object>> dataSet,HSSFSheet sheet,HSSFWorkbook wk){  
		HSSFCellStyle style = wk.createCellStyle();  

		//边框  
		style.setBorderBottom(CellStyle.BORDER_THIN);  
		style.setBorderLeft(CellStyle.BORDER_THIN);  
		style.setBorderRight(CellStyle.BORDER_THIN);  
		style.setBorderTop(CellStyle.BORDER_THIN);  

		//背景  
		style.setFillForegroundColor(HSSFColor.YELLOW.index);  
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  

		//字体  
		HSSFFont font = wk.createFont();  
		font.setFontName("宋体");  
		font.setFontHeightInPoints((short)8);  
		style.setFont(font);  
		int dataSetSize = dataSet.size();
		int columnSize = 0;
		List<Object> columnData = null;
		for (int i = 0; i < dataSetSize; i++) {
			HSSFRow row = sheet.createRow(i + 1);
			columnData = dataSet.get(i);
			columnSize = columnData.size();
			for (int j = 0; j < columnSize; j++) {
				if (columnData.get(j) != null) {
					HSSFCell headerCell = row.createCell(j+1);//第一行空出来  
					headerCell.setCellStyle(style);  
					headerCell.setCellValue(columnData.get(j).toString());  
				}
			}
		}
	}  
	/** 
	 * 设置表头 
	 * @author longdage 
	 * @param headerMsg:表头字段 
	 * @param sheet:所在页 
	 * @param wk:工作簿 
	 * */  
	public static void initHeader(String[] headerMsg,HSSFSheet sheet,HSSFWorkbook wk){  

		//初始化  
		HSSFRow headerRow = sheet.createRow(2);  
		HSSFCellStyle style = wk.createCellStyle();  

		//位置  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		style.setBorderBottom(CellStyle.BORDER_THIN);  
		style.setBorderLeft(CellStyle.BORDER_THIN);  
		style.setBorderRight(CellStyle.BORDER_THIN);  
		style.setBorderTop(CellStyle.BORDER_THIN);  

		//背景  
		style.setFillForegroundColor(HSSFColor.GREEN.index);  
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  

		//字体  
		HSSFFont font = wk.createFont();  
		font.setFontName("宋体");  
		font.setFontHeightInPoints((short)11);  
		style.setFont(font);  

		//text  
		for(int i=0;i<headerMsg.length;i++){  
			HSSFCell headerCell = headerRow.createCell(i+1);//第一行空出来  
			headerCell.setCellStyle(style);  
			headerCell.setCellValue(headerMsg[i]);  
		}  
	}  
	/** 
	 * 设置标题 
	 * @author longdage
	 * @param title:标题 
	 * @param sheet:所在页 
	 * @param wk:工作簿 
	 * */  
	public static void initTitle(String[] headerMsg,String title,HSSFSheet sheet,HSSFWorkbook wk){  

		//初始化  
		HSSFRow titleRow = sheet.createRow(0);  
		HSSFCell titleCell = titleRow.createCell(1);  
		HSSFCellStyle style = wk.createCellStyle();  

		//位置  
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  

		//边框  
		style.setBorderBottom(CellStyle.BORDER_THIN);  
		style.setBorderLeft(CellStyle.BORDER_THIN);  
		style.setBorderRight(CellStyle.BORDER_THIN);  
		style.setBorderTop(CellStyle.BORDER_THIN);  

		//背景  
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  

		//字体  
		HSSFFont font = wk.createFont();  
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
		font.setFontName("宋体");  
		font.setFontHeightInPoints((short)16);  
		style.setFont(font);  
		titleRow.setHeightInPoints((float)20);  
		titleCell.setCellStyle(style);  

		//text  
		titleCell.setCellValue(title);  

		//合并  
		sheet.addMergedRegion(new CellRangeAddress(0,1,1,headerMsg.length));  
	}  
}
