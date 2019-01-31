package com.hydee.hdsec.entity;

import java.io.Serializable;

public class ShareImg implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String shareId;
	private String imgLink;
	
	public ShareImg(){
	}
	
	public ShareImg(String shareId,String imgLink){
		this.shareId = shareId;
		this.imgLink = imgLink;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public String getImgLink() {
		return imgLink;
	}
	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}
}
