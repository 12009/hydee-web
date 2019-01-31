package com.hydee.hdsec.sqlDao;


import com.hydee.hdsec.entity.SystemConfig;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 项目名称：hdsec
 * 类名称：SystemConfigDao
 * 类描述：系统设置数据操作
 * 创建人：zz
 * 创建时间：2014-12-30 上午10:53:50
 * 修改人：zz
 * 修改时间：2014-12-30 上午10:53:50
 * 修改备注：
 * @version Ver 1.1
 */
@Repository
public interface SystemConfigDao {
	
	/**
	 * 
	 * findByPrimaryKey(根据主键查询)
	 */
	SystemConfig findByPrimaryKey(String key);
	
	List<SystemConfig> listSystemConfig4Member();
}
