package com.hydee.hdsec.entity;


import com.hydee.hdsec.entity.utils.StringUtil;

import java.io.Serializable;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String customerId;
	private String serverName;
	private String companyName;
	private String singleItemSwitch = "off";
	private int appHideDur = 0;
	private String serverUrl;
	private String verifyCode;
	private String serverType;
	private String shifts;
	private String goToWorkTime1;
	private String goOffWorkTime1;
	private String goToWorkTime2;
	private String goOffWorkTime2;
	private String goToWorkTime3;
	private String goOffWorkTime3;
	private String workHoursTheory;
	private String hType;
	
	private String checkBoxValue;
	private String ifChecked = "0";

	private int smsLimit;//手机短信发送最高限制20160801

	public Company(){
		this.serverName = "";
		this.serverUrl = "";
		this.companyName = "";
		this.customerId = "";
		this.smsLimit=0;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSingleItemSwitch() {
		return singleItemSwitch;
	}

	public void setSingleItemSwitch(String singleItemSwitch) {
		this.singleItemSwitch = singleItemSwitch;
	}

	public int getAppHideDur() {
		return appHideDur;
	}

	public void setAppHideDur(int appHideDur) {
		this.appHideDur = appHideDur;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public String getShifts() {
		return shifts;
	}

	public void setShifts(String shifts) {
		this.shifts = shifts;
	}

	public String getGoToWorkTime1() {
		return goToWorkTime1;
	}

	public void setGoToWorkTime1(String goToWorkTime1) {
		this.goToWorkTime1 = goToWorkTime1;
	}

	public String getGoOffWorkTime1() {
		return goOffWorkTime1;
	}

	public void setGoOffWorkTime1(String goOffWorkTime1) {
		this.goOffWorkTime1 = goOffWorkTime1;
	}

	public String getGoToWorkTime2() {
		return goToWorkTime2;
	}

	public void setGoToWorkTime2(String goToWorkTime2) {
		this.goToWorkTime2 = goToWorkTime2;
	}

	public String getGoOffWorkTime2() {
		return goOffWorkTime2;
	}

	public void setGoOffWorkTime2(String goOffWorkTime2) {
		this.goOffWorkTime2 = goOffWorkTime2;
	}

	public String getGoToWorkTime3() {
		return goToWorkTime3;
	}

	public void setGoToWorkTime3(String goToWorkTime3) {
		this.goToWorkTime3 = goToWorkTime3;
	}

	public String getGoOffWorkTime3() {
		return goOffWorkTime3;
	}

	public void setGoOffWorkTime3(String goOffWorkTime3) {
		this.goOffWorkTime3 = goOffWorkTime3;
	}

	public String getWorkHoursTheory() {
		return workHoursTheory;
	}

	public void setWorkHoursTheory(String workHoursTheory) {
		this.workHoursTheory = workHoursTheory;
	}

	public String getCheckBoxValue() {
		return checkBoxValue;
	}

	public void setCheckBoxValue(String checkBoxValue) {
		this.checkBoxValue = checkBoxValue;
	}

	public String getIfChecked() {
		return ifChecked;
	}

	public void setIfChecked(String ifChecked) {
		this.ifChecked = ifChecked;
	}

	public String gethType() {
		return hType;
	}

	public void sethType(String hType) {
		this.hType = hType;
	}

	public int getSmsLimit() {
		return smsLimit;
	}

	public void setSmsLimit(int smsLimit) {
		this.smsLimit = smsLimit;
	}

	public String getVerifyCode() {
		return StringUtil.unicode2String(verifyCode);
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
}
