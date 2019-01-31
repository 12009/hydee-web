package com.hydee.hdsec.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 项目名称：hdsec
 * 类名称：SystemConfig
 * 类描述：系统设置
 * 创建人：zz
 * 创建时间：2014-12-30 上午10:44:10
 * 修改人：zz
 * 修改时间：2014-12-30 上午10:44:10
 * 修改备注：
 * @version Ver 1.1
 *
 */
public class SystemConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 配置的Key
	 */
	private String key;
	/**
	 * 配置的名称
	 */
	private String name;
	/**
	 * 配置的值Value
	 */
	private String value;
	/**
	 * 描述
	 */
	private String description;
	/**
	 *最后更新时间
	 */
	private Date lastupDatetime;
	/**
	 * 获取key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取lastupDatetime
	 */
	public Date getLastupDatetime() {
		return lastupDatetime;
	}
	/**
	 * 设置lastupDatetime
	 */
	public void setLastupDatetime(Date lastupDatetime) {
		this.lastupDatetime = lastupDatetime;
	}
	


	 
}
