package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.IPAddress;
import com.hydee.hdsec.entity.OperationLog;

import java.util.List;

/**
 * 
 * 项目名称：hdsec
 * 类名称：OperationLogDao
 * 类描述：日志操作数据层
 * 创建人：zz
 * 创建时间：2014-12-31 下午4:54:52
 * 修改人：zz
 * 修改时间：2014-12-31 下午4:54:52
 * 修改备注：
 * @version Ver 1.1
 */

public interface OperationLogDao {

	/**
	 * 添加日志
	 */
	void addOperationLog(List<OperationLog> operationLogList);
	
	void insertIpAddr(IPAddress ipAddr);
	
	int countIpAddr(String ip);
}
