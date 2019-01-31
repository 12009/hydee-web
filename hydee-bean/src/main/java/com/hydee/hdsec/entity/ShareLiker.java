package com.hydee.hdsec.entity;


import java.io.Serializable;

public class ShareLiker implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String userId;
	private String customerId;
	private String shareId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof String){
			return this.userId.equals(obj);
		}else if(obj instanceof ShareLiker){
			return this.userId.equals(((ShareLiker) obj).getUserId());
		}
		return false;
	}
}
