package com.hydee.hdsec.entity;

import java.io.Serializable;

public class AppAdvertCustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String customerId;
	private String customerName;
	private Integer advertId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Integer getAdvertId() {
		return advertId;
	}
	public void setAdvertId(Integer advertId) {
		this.advertId = advertId;
	}
}
