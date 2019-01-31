package com.hydee.hdsec.service;

import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.SystemConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * 项目名称：hdsec
 * 类名称：SystemConfigService
 * 类描述：系统配置业务层
 * 创建人：zz
 * 创建时间：2014-12-30 上午11:09:18
 * 修改人：zz
 * 修改时间：2014-12-30 上午11:09:18
 * 修改备注：
 * @version Ver 1.1
 */
public interface SystemConfigService {
	
	public SystemConfig findByPrimaryKey(String key) throws ServiceException;

	public List<SystemConfig> listSystemConfig4Member();

}
