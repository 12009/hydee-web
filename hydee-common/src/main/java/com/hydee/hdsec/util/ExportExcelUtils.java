package com.hydee.hdsec.util;

import com.hydee.hdsec.bean.ExcelModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ExportExcelUtils {
	

	/**
	 * @param wb SXSSFWorkbook Workbook
	 */
    public static void createCell(Workbook wb, String content, Row row, int column, short halign, short valign) {
    	if(StringUtils.isEmpty(content)){
    		content = "-";
    	}
        Cell cell = row.createCell(column);
        cell.setCellValue(new XSSFRichTextString(content));
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cell.setCellStyle(cellStyle);
    }
    
	public static void simpleExportExcel(HttpServletRequest request, HttpServletResponse response, String exportName, String sheetName,
										 String[] columns, int columnWidth[], List<Object[]> data) throws IOException {
		setExportName(request, response, exportName);
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(sheetName);
		for (int i = 0; i < columnWidth.length; i++) {
			int j = columnWidth[i];
			sheet.setColumnWidth(i, 256 * j);
		}
		Row columnRow = sheet.createRow(0);
		for (int i = 0; i < columns.length; i++) {
			createCell(workbook, columns[i] ,columnRow, i, XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.VERTICAL_CENTER);
		}
		for (int i = 0; i < data.size(); i++) {
			Row dataRow = sheet.createRow(i + 1);
			Object[] rowData = data.get(i);
			for (int j = 0; j < rowData.length; j++) {
				Object val = rowData[j];
				String content = val == null ? "" : val.toString();
				createCell(workbook, content ,dataRow, j, XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.VERTICAL_CENTER);
			}
		}
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
	}
	
	public static void exportExcel(HttpServletRequest request, HttpServletResponse response, String exportName, List<ExcelModel> excelModels) throws IOException{
		Iterator<ExcelModel> itr = excelModels.iterator();
		setExportName(request, response, exportName);
		XSSFWorkbook workbook = new XSSFWorkbook();
		while(itr.hasNext()){
			ExcelModel model = itr.next();
			Sheet sheet = workbook.createSheet(model.getSheetName());
			int columnWidth[] = model.getColumnWidths();
			for (int i = 0; i < columnWidth.length; i++) {
				int j = columnWidth[i];
				sheet.setColumnWidth(i, 256 * j);
			}
			Row columnRow = sheet.createRow(0);
			String columns[] = model.getColumns();
			for (int i = 0; i < columns.length; i++) {
				createCell(workbook, columns[i] ,columnRow, i, XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.VERTICAL_CENTER);
			}
			for (int i = 0; i < model.getDatas().size(); i++) {
				Row dataRow = sheet.createRow(i + 1);
				Object[] rowData = model.getDatas().get(i);
				for (int j = 0; j < rowData.length; j++) {
					Object val = rowData[j];
					String content = val == null ? "" : val.toString();
					createCell(workbook, content ,dataRow, j, XSSFCellStyle.ALIGN_CENTER, XSSFCellStyle.VERTICAL_CENTER);
				}
			}
		}
		OutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
	}

	private static void setExportName(HttpServletRequest request, HttpServletResponse response, String exportName) {
		response.setContentType("application/octet-stream;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(response.SC_OK);
		String agent = request.getHeader("USER-AGENT");
		try {
			if (null != agent && (agent.contains("MSIE") || agent.contains("rv:11") || agent.contains("Chrome"))) {
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(exportName, "UTF-8") + ".xlsx");
			} else if (null != agent && agent.contains("Mozilla")) { 
				String codedFileName = "=?UTF-8?B?"	+ (new String(Base64.encodeBase64(exportName.getBytes("UTF-8")))) + "?=";
				response.setHeader("Content-Disposition", "attachment; filename=" + codedFileName + ".xlsx");
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(exportName, "UTF-8") + ".xlsx");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 解析上传人员名单EXCEL
	 * @param inputStream
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static JSONArray parseUserExcel(InputStream inputStream, String fileName) throws IOException {
        Workbook wb = null;
        if(fileName.endsWith("xls")){  
            wb = new HSSFWorkbook(inputStream);//解析xls格式
        }else if(fileName.endsWith("xlsx")){  
            wb = new XSSFWorkbook(inputStream);//解析xlsx格式
        }  
        Sheet sheet = wb.getSheetAt(0);//第一个工作表
        int firstRowIndex = sheet.getFirstRowNum();  
        int lastRowIndex = sheet.getLastRowNum();  
        JSONArray jArray = new JSONArray();
        //读取Excel表
        for(int rIndex = firstRowIndex+1; rIndex <= lastRowIndex; rIndex ++){ 
        	Row _row = sheet.getRow(rIndex);
        	Cell _cell = _row.getCell(0);
        	String _userId = _cell == null ? "" : _cell.toString();
        	// 过滤工号被误读为number类型时自带的小数位
        	int _lastIndex = _userId.indexOf(".") < 0 ? _userId.length() : _userId.indexOf(".");
        	_userId = _userId.substring(0,_lastIndex);
        	_cell = _row.getCell(1);
        	String _userName = _cell == null ? "" : _cell.toString();
        	JSONObject jObj = new JSONObject();
        	jObj.put("userId", _userId.trim());
        	jObj.put("userName", _userName.trim());
        	jArray.add(jObj);
        }
        return jArray;
	}
	/**
	 * 解析单元格数值结果(保留2位,四舍五入)
	 * @param cell
	 * @return
	 */
	public static double getCellNumerice(Cell cell) {
		if( cell == null ) return 0;
		double result = 0;
		try {
			switch ( cell.getCellType() ) {
			case 0:
				// 数值型
				result = Double.valueOf(NumericUtils.formatDouble( cell.getNumericCellValue() ));
				break;
			case 1:
				// 字符串型
				String _result = cell.getStringCellValue();
				if( StringUtil.isBlank(_result) ){
					int _maxSize = _result.indexOf(".") > 0 ? _result.indexOf(".") + 2 : _result.length();
					result = Double.valueOf( _result.substring(0, _maxSize) );
				}
				break;
			}
		} catch (Exception e) {
			Logger.error(e);
			return 0;
		}
		return result;
	}
	
    private static String parseExcelDate(Cell cell) {
        String result = new String();  
        switch (cell.getCellType()) {  
        case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
            if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                SimpleDateFormat sdf = null;  
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                    sdf = new SimpleDateFormat("HH:mm");  
                } else {// 日期  
                    sdf = new SimpleDateFormat("yyyy-MM-dd");  
                }  
                Date date = cell.getDateCellValue();  
                result = sdf.format(date);  
            } else if (cell.getCellStyle().getDataFormat() == 58) {  
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
                double value = cell.getNumericCellValue();  
                Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);  
                result = sdf.format(date);  
            } else {  
                double value = cell.getNumericCellValue();  
                CellStyle style = cell.getCellStyle();
                DecimalFormat format = new DecimalFormat();  
                String temp = style.getDataFormatString();  
                // 单元格设置成常规  
                if (temp.equals("General")) {  
                    format.applyPattern("#");  
                }  
                result = format.format(value);  
            }  
            break;  
        case HSSFCell.CELL_TYPE_STRING:// String类型
            result = cell.getRichStringCellValue().toString();  
            break;  
        case HSSFCell.CELL_TYPE_BLANK:
            result = "";  
        default:  
            result = "";  
            break;  
        }  
        return result;  
    }  
}
