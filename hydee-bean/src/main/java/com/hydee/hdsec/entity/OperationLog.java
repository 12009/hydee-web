/**
 * 项目名：hdsec
 * 包名：com.hdsec.operationlog.entity
 * 文件名：OperationLog.java
 * 版本信息：@version 1.0
 * 日期：2014-12-30-下午3:21:49
 * Copyright (c) 2014海典软件-版权所有  
 */
package com.hydee.hdsec.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称：hdsec
 * 类名称：OperationLog
 * 类描述：操作日志
 * 创建人：zz
 * 创建时间：2014-12-30 下午3:21:49
 * 修改人：zz
 * 修改时间：2014-12-30 下午3:21:49
 * 修改备注：
 * @version Ver 1.1
 */

public class OperationLog implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键UUID
	 */
	private String uuid;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 部门ID
	 */
	private String depId;
	/**
	 * 部门名称
	 */
	private String depName;
	/**
	 * 职位
	 */
	private String position;
	/**
	 * 访问IP
	 */
	private String visitorIp;
	/**
	 * 访问模块
	 */
	private String module;
	/**
	 * 访问子模块
	 */
	private String subModule;
	/**
	 * 访问时间
	 */
	private Date visitTime;
	/**
	 *  公司id
	 */
	private String firmId;
	/**
	 * 公司名称
	 */
	private String firmName;
	
	/**
	 *日志描述 
	 */
	private String description;
	/**
	 * 访问设备型号
	 */
	private String devicetoken;
	private String deviceType;
	private String osversion;
	
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getOsversion() {
		return osversion;
	}
	public void setOsversion(String osversion) {
		this.osversion = osversion;
	}
	public String getDevicetoken() {
		return devicetoken;
	}
	public void setDevicetoken(String devicetoken) {
		this.devicetoken = devicetoken;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
		
	/**
	 * 获取uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * 设置uuid
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * 获取userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取depId
	 */
	public String getDepId() {
		return depId;
	}
	/**
	 * 设置depId
	 */
	public void setDepId(String depId) {
		this.depId = depId;
	}
	/**
	 * 获取depName
	 */
	public String getDepName() {
		return depName;
	}
	/**
	 * 设置depName
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}
	/**
	 * 获取position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置position
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取visitorIp
	 */
	public String getVisitorIp() {
		return visitorIp;
	}
	/**
	 * 设置visitorIp
	 */
	public void setVisitorIp(String visitorIp) {
		this.visitorIp = visitorIp;
	}
	/**
	 * 获取module
	 */
	public String getModule() {
		return module;
	}
	/**
	 * 设置module
	 */
	public void setModule(String module) {
		this.module = module;
	}
	/**
	 * 获取subModule
	 */
	public String getSubModule() {
		return subModule;
	}
	/**
	 * 设置subModule
	 */
	public void setSubModule(String subModule) {
		this.subModule = subModule;
	}
	/**
	 * 获取visitTime
	 */
	public Date getVisitTime() {
		return visitTime;
	}
	/**
	 * 设置visitTime
	 */
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	/**
	 * 获取firmId
	 * @return
	 */
	public String getFirmId() {
		return firmId;
	}
	/**
	 * 设置firmId
	 * @param firmId
	 */
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	/**
	 * 获取firmName
	 * @return
	 */
	public String getFirmName() {
		return firmName;
	}
	/**
	 * 设置firmName
	 * @param firmName
	 */
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	
	
	
}
