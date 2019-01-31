package com.hydee.hdsec.common.exception;

import com.hydee.hdsec.common.constant.ErrorCode;

/**
 * 
 * 项目名称：hdsec 类名称：ServiceException 类描述：业务自定义异常 创建人：zz 创建时间：2014-12-30 上午11:34:57
 * 修改人：zz 修改时间：2014-12-30 上午11:34:57 修改备注：
 * 
 * @version Ver 1.1
 */
public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	int errorCode = 500;

	public ServiceException(ErrorCode errorCode) {
		super(errorCode.getMsg());
		this.errorCode = errorCode.getCode();
	}

	public ServiceException(Throwable e) {
		super(e);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}

	public ServiceException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * 最常用的构造方法
	 * 
	 * @param errorCode
	 *            错误ID
	 * @param message
	 *            错误消息
	 * @param e
	 *            异常信息
	 */
	public ServiceException(int errorCode, String message, Throwable e) {
		super(message, e);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
