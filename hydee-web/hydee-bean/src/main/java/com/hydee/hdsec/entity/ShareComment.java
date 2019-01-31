package com.hydee.hdsec.entity;


import com.hydee.hdsec.entity.utils.DateUtil;
import com.hydee.hdsec.entity.utils.StringUtil;

import java.io.Serializable;

public class ShareComment implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String shareId;
	private String observerId;
	private String targetId;
	private String publishTime;
	private String content;
	private String observerName;
	private String targetName;
	private String customerId;
	private String headImg;

	public ShareComment(){
		this.headImg = "";
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
	public String getObserverId() {
		return observerId;
	}
	public void setObserverId(String observerId) {
		this.observerId = observerId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getPublishTime() {
		return publishTime.replaceAll("\\.\\S+$", "");
	}
	public String getPublishTimeFmt() {
		return DateUtil.formatShare(publishTime);
	}
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	public String getContent() {
		return StringUtil.unicode2String(content);
	}
	public String getContentSource() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getObserverName() {
		return observerName;
	}

	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
}
