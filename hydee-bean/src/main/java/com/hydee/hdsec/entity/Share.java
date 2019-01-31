package com.hydee.hdsec.entity;


import com.hydee.hdsec.entity.utils.DateUtil;
import com.hydee.hdsec.entity.utils.StringUtil;

import java.util.List;

public class Share extends Page{
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String content;
	private String shareTime;
	private String isPublished;
	private String sourceType;
	private String sourceId;
	private String customerId;
	private String userId;
	private String userName;
	private String busNo;
	private String busName;
	private String headImg;
	private List<ShareImg> shareImgs;
	private List<ShareLiker> likers;
	private List<ShareComment> comments;
	private boolean isLiked;
	
	public Share(){
		this.isPublished = "0";
		this.busName = "";
		this.busNo = "";
		this.busName = "";
		this.headImg = "";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getShareTime() {
		return shareTime == null ? "" : shareTime.replaceAll("\\.\\S+$", "");
	}
	public void setShareTime(String shareTime) {
		this.shareTime = shareTime;
	}
	public String getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(String isPublished) {
		this.isPublished = isPublished;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ShareComment> getComments() {
		return comments;
	}
	public void setComments(List<ShareComment> comments) {
		this.comments = comments;
	}
	public List<ShareLiker> getLikers() {
		return likers;
	}
	public void setLikers(List<ShareLiker> likers) {
		this.likers = likers;
	}
	public List<ShareImg> getShareImgs() {
		return shareImgs;
	}
	public void setShareImgs(List<ShareImg> shareImgs) {
		this.shareImgs = shareImgs;
	}
	public boolean isLiked() {
		return isLiked;
	}
	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	public int getLikesCount() {
		return likers == null ? 0 : this.likers.size();
	}
	public String getShareTimeFmt(){
		return DateUtil.formatShare(shareTime);
	}
	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
}
