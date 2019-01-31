package com.hydee.hdsec.entity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 数据库表message所对应的实体类
 * 
 * @author
 * 
 */
public class Message{
	private static final long serialVersionUID = 1L;
	private String id;
	private String customerId;
	private String type;
	private String content;
	private String createrId;
	private String createrName;
	private String createTime;
	private String userIdListStr;
	private String hasRead;

	private String startTime;//开始时间（活动模板）
	private String endTime;//结束时间（活动模板）
	private String noticeTime;//通知时间
	private String headLine;//标题
	private String compereId;//主持人ID
	private String compereName;//主持人名称
	private String address;//地址
	private String templateType;//模板类型(1000：系统默认；1001：会议模板；1002：活动模板；1003：培训模板)
	private String status;//状态值（0：发布；1：草稿；2：作废）
	private String signAddress;//签到地址

	private String userId;//通知人员

	private String lat;
	private String lng;

	private String createStartTime;//查询条件开始时间
	private String createEndTime;//查询条件结束时间

	private String userAmount;//通知总人数
	private String noReadAmount;//没有阅读的人数

	private int appPageType;//0：没有详情、1：厂商有详情、2：试卷有详情

	private String sourceId;//消息主键ID
	
	public Message() {
		this.id = "";
		this.customerId = "";
		this.type = "";
		this.content = "";
		this.createrId = "";
		this.createrName = "";
		this.createTime = "";
		this.userIdListStr = "";
		this.hasRead = "";
		this.startTime="";//开始时间（活动模板）
		this.endTime="";//结束时间（活动模板）
		this.noticeTime="";//通知时间
		this.headLine="";//标题
		this.compereId="";//主持人ID
		this.compereName="";//主持人名称
		this.address="";//地址
		this.templateType="";//模板类型(1000：系统默认；1001：会议模板；1002：活动模板；1003：培训模板)
		this.status="";//状态值（0：发布；1：草稿；2：作废）
		this.signAddress="";//签到地址
		this.lat="";
		this.lng="";
		this.appPageType=0;//0：没有详情、1：有详情
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
	 * 返回content
	 * 
	 * @return
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 设置content
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 返回createrId
	 * 
	 * @return
	 */
	public String getCreaterId() {
		return this.createrId;
	}

	/**
	 * 设置createrId
	 * 
	 * @param createrId
	 */
	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	/**
	 * 返回createTime
	 * 
	 * @return
	 */
	public String getCreateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(this.createTime);
			return sdf.format(date);
		} catch (ParseException e) {
			return this.createTime;
		}
	}

	/**
	 * 设置createTime
	 * 
	 * @param createTime
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserIdListStr() {
		return userIdListStr;
	}

	public void setUserIdListStr(String userIdListStr) {
		this.userIdListStr = userIdListStr;
	}

	public String getHasRead() {
		return hasRead;
	}

	public void setHasRead(String hasRead) {
		this.hasRead = hasRead;
	}

	public String getStartTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(this.startTime);
			return sdf.format(date);
		} catch (ParseException e) {
			return this.startTime;
		}
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(this.endTime);
			return sdf.format(date);
		} catch (ParseException e) {
			return this.endTime;
		}
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNoticeTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(this.noticeTime);
			return sdf.format(date);
		} catch (ParseException e) {
			return this.noticeTime;
		}
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getHeadLine() {
		return headLine;
	}

	public void setHeadLine(String headLine) {
		this.headLine = headLine;
	}

	public String getCompereId() {
		return compereId;
	}

	public void setCompereId(String compereId) {
		this.compereId = compereId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCompereName() {
		return compereName;
	}

	public void setCompereName(String compereName) {
		this.compereName = compereName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getSignAddress() {
		return signAddress;
	}

	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getUserAmount() {
		return userAmount;
	}

	public void setUserAmount(String userAmount) {
		this.userAmount = userAmount;
	}

	public String getNoReadAmount() {
		return noReadAmount;
	}

	public void setNoReadAmount(String noReadAmount) {
		this.noReadAmount = noReadAmount;
	}

	public int getAppPageType() {
		return appPageType;
	}

	public void setAppPageType(int appPageType) {
		this.appPageType = appPageType;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

}
