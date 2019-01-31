package com.hydee.hdsec.entity;


import com.hydee.hdsec.entity.utils.StringUtil;

import java.io.Serializable;

public class MobileUser implements Serializable {
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String userId;
	private String userName;
	private String imgPath;
	private Long stamp;
	private String stampStr;
	private String cid;
	private String mobileNo;
	private String verifyCode;
	private String verifyCodeCreateTime;
	private String birthday;
	private String hobby;
	private String depId;
	private String depName;
	private String positionNo;
	private String positionName;
	private String loginCode;
	private String loginCodeCreateTime;
	private String customerName;
	private String deviceToken;
	private String version;
	private String password;
	private String h2compid;
	private String groupId;
	private String lastLoginTime;
	private String busNo;
	private String busName;
	private String busArea;
	private int status;

	public String getH2compid() {
		return h2compid;
	}

	public void setH2compid(String h2compid) {
		this.h2compid = h2compid;
	}

	public Long getStamp() {
		return stamp;
	}

	public void setStamp(Long stamp) {
		this.stamp = stamp;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getStampStr() {
		return stampStr;
	}

	public void setStampStr(String stampStr) {
		this.stampStr = stampStr;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getVerifyCodeCreateTime() {
		return verifyCodeCreateTime;
	}

	public void setVerifyCodeCreateTime(String verifyCodeCreateTime) {
		this.verifyCodeCreateTime = verifyCodeCreateTime;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getPositionNo() {
		return positionNo;
	}

	public void setPositionNo(String positionNo) {
		this.positionNo = positionNo;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getLoginCode() {
		return loginCode;
	}

	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getLoginCodeCreateTime() {
		return loginCodeCreateTime;
	}

	public void setLoginCodeCreateTime(String loginCodeCreateTime) {
		this.loginCodeCreateTime = loginCodeCreateTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPassword() {
		return StringUtil.unicode2String(password);
	}
	
	public String getPasswordSource() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPasswordUnicode(String password) {
		this.password = StringUtil.string2UnicodeEx(password);
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBusArea() {
		return busArea;
	}

	public void setBusArea(String busArea) {
		this.busArea = busArea;
	}
	
}
