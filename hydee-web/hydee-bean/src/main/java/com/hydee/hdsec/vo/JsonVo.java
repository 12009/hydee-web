package com.hydee.hdsec.vo;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * 类名称：JsonVo 
 * 类描述：JSON返回包装类 
 * 创建人：LuoF 
 * 创建日期：2016-08-18
 * 修改人：
 * 修改日期： 
 * 修改备注：
 * 
 * @version V_1.0
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class JsonVo<T> {
	/**
	 * 结果
	 */
	private boolean result = true;

	/**
	 * 返回的数据
	 */
	private T data;

	/**
	 * 返回数量
	 */
	private int count;
	

	/**
	 * 返回总数量
	 */
	private int allcount;
	
	/**
	 * 状态码
	 */
	private String status = "200";

	/**
	 * 获取result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * 设置result
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/**
	 * 获取data
	 */
	public T getData() {
		return data;
	}

	/**
	 * 设置data
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * 获取count
	 */
	@SuppressWarnings("rawtypes")
	public int getCount() {
		if (data instanceof List) {
			count = ((List) data).size();
		} else {
			this.count = 1;
		}
		return count;
	}

	/**
	 * 设置count
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * 获取allcount
	 */
	public int getAllcount() {
		return allcount;
	}

	/**
	 * 设置count
	 */
	public void setAllcount(int allcount) {
		this.allcount = allcount;
	}

	/**
	 * 获取status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
