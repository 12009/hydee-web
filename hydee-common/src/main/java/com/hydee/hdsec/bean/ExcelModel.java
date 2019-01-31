package com.hydee.hdsec.bean;

import java.io.Serializable;
import java.util.List;

public class ExcelModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String sheetName;
	private String[] columns;
	private int[] columnWidths;
	private List<Object[]> datas;
	
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	public int[] getColumnWidths() {
		return columnWidths;
	}
	public void setColumnWidths(int[] columnWidths) {
		this.columnWidths = columnWidths;
	}
	public List<Object[]> getDatas() {
		return datas;
	}
	public void setDatas(List<Object[]> datas) {
		this.datas = datas;
	}
}
