package com.hydee.hdsec.entity;

/**
 * SQL过滤查询:条件实体类
 * 继承分页类,包含分页属性
 * @author LuoF
 */
public class SqlConditions extends Page {
	private static final long serialVersionUID = 1L;
	private String keywords;		// 搜索过滤条件-可用关键字
	private String startTime_c;	// 搜索过滤条件-开始时间(yyyy-MM-dd hh:mm:ss)
	private String endTime_c;		// 搜索过滤条件-结束时间(yyyy-MM-dd hh:mm:ss)
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getStartTime_c() {
		return startTime_c;
	}
	public void setStartTime_c(String startTime_c) {
		this.startTime_c = startTime_c;
	}
	public String getEndTime_c() {
		return endTime_c;
	}
	public void setEndTime_c(String endTime_c) {
		this.endTime_c = endTime_c;
	}
	
}
