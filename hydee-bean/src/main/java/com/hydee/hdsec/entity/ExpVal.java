package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 数据库表Exp_val_list所对应的实体类
 * 
 * @author
 * 
 */
public class ExpVal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String customerId;
	private String userId;
	private String type;
	private String val;
	private String getDateTime;
	private String paperSingleItemId;

	public ExpVal() {
		this.id = "";
		this.customerId = "";
		this.userId = "";
		this.type = "";
		this.val = "";
		this.getDateTime = "";
		this.paperSingleItemId = "";
	}
	
	public ExpVal(String customerId, String userId, String type, String paperSingleItemId) {
		this.customerId = customerId;
		this.userId = userId;
		this.type = type;
		this.paperSingleItemId = paperSingleItemId;
	}

	public ExpVal(String customerId, String userId, String type) {
		this.customerId = customerId;
		this.userId = userId;
		this.type = type;
	}
	
	public ExpVal(String customerId, String userId) {
		this.customerId = customerId;
		this.userId = userId;
	}

	/**
	 * 返回id
	 * 
	 * @return
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 设置id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回customerId
	 * 
	 * @return
	 */
	public String getCustomerId() {
		return this.customerId;
	}

	/**
	 * 设置customerId
	 * 
	 * @param customerId
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * 返回userId
	 * 
	 * @return
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * 设置userId
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 返回type
	 * 
	 * @return
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * 设置type
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 返回val
	 * 
	 * @return
	 */
	public String getVal() {
		return this.val;
	}

	/**
	 * 设置val
	 * 
	 * @param val
	 */
	public void setVal(String val) {
		this.val = val;
	}

	/**
	 * 返回getDateTime
	 * 
	 * @return
	 */
	public String getGetDateTime() {
		return this.getDateTime;
	}

	/**
	 * 设置getDateTime
	 * 
	 * @param getDateTime
	 */
	public void setGetDateTime(String getDateTime) {
		this.getDateTime = getDateTime;
	}

	public String getPaperSingleItemId() {
		return paperSingleItemId;
	}

	public void setPaperSingleItemId(String paperSingleItemId) {
		this.paperSingleItemId = paperSingleItemId;
	}
	
}
