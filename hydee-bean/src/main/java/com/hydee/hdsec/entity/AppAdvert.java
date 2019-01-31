package com.hydee.hdsec.entity;

import net.sf.json.JSONArray;

import java.util.List;

public class AppAdvert extends Page{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String imgUrl;
	private String advertUrl;
	private String companyName;
	private String publisherName;
	private String startTime;
	private String playStartTime;
	private String endTime;
	private String playEndTime;
	private String createTime;
	private Integer adStatus;
	private String remark;
	private String region;
	private String city;
	private Integer duration;
	
	private String advertName;
	private String companyId;
	private Integer advertType;
	
	private Integer visitCount;
	private Integer clickCount;

	private Integer ystDisCnt;

	private Integer totalDisCnt;

	private Integer ystClkCnt;

	private Integer totalClkCnt;
	
	private List<AppAdvertCustomer> customerList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAdvertUrl() {
		return advertUrl;
	}

	public void setAdvertUrl(String advertUrl) {
		this.advertUrl = advertUrl;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getStartTime() {
		return startTime == null ? null : startTime.replaceAll("\\.\\S+", "").replace(" ","</br>");
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getPlayStartTime() {
		return playStartTime == null ? null : playStartTime.replaceAll("\\.\\S+", "").replace(" ","</br>");
	}

	public void setPlayStartTime(String playStartTime) {
		this.playStartTime = playStartTime;
	}

	public String getEndTime() {
		return endTime == null ? null : endTime.replaceAll("\\.\\S+", "").replace(" ","</br>");
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPlayEndTime() {
		return playEndTime == null ? null : playEndTime.replaceAll("\\.\\S+", "").replace(" ","</br>");
	}

	public void setPlayEndTime(String playEndTime) {
		this.playEndTime = playEndTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(Integer adStatus) {
		this.adStatus = adStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getAdvertType() {
		return advertType;
	}

	public void setAdvertType(Integer advertType) {
		this.advertType = advertType;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public void setClickCount(Integer clickCount) {
		this.clickCount = clickCount;
	}

	public List<AppAdvertCustomer> getCustomerList() {
		return customerList;
	}

	public String getCustomerListJson() {
		return customerList == null ? null : JSONArray.fromObject(customerList).toString();
	}

	public void setCustomerList(List<AppAdvertCustomer> customerList) {
		this.customerList = customerList;
	}

	public Integer getYstDisCnt() {
		return ystDisCnt;
	}

	public void setYstDisCnt(Integer ystDisCnt) {
		this.ystDisCnt = ystDisCnt;
	}

	public Integer getTotalDisCnt() {
		return totalDisCnt;
	}

	public void setTotalDisCnt(Integer totalDisCnt) {
		this.totalDisCnt = totalDisCnt;
	}

	public Integer getYstClkCnt() {
		return ystClkCnt;
	}

	public void setYstClkCnt(Integer ystClkCnt) {
		this.ystClkCnt = ystClkCnt;
	}

	public Integer getTotalClkCnt() {
		return totalClkCnt;
	}

	public void setTotalClkCnt(Integer totalClkCnt) {
		this.totalClkCnt = totalClkCnt;
	}
}
