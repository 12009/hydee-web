package com.hydee.hdsec.common.constant;

public class ErrorCode {

	private int code;
	private String msg;

	public ErrorCode(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 获取code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 获取msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 设置msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
