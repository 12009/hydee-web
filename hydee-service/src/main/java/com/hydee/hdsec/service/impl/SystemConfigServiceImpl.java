package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.SystemConfig;
import com.hydee.hdsec.service.SystemConfigService;
import com.hydee.hdsec.sqlDao.SystemConfigDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 94908 on 2017/4/19/0019.
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService{
    @Autowired
    SystemConfigDao systemConfigDao;

    @Override
    public SystemConfig findByPrimaryKey(String key) throws ServiceException {
        SystemConfig systemConfig = systemConfigDao.findByPrimaryKey(key);
        if(systemConfig==null){
            throw new ServiceException(ErrorCodes.DATA_NULL);
        }
        return systemConfig;
    }

    @Override
    public List<SystemConfig> listSystemConfig4Member() {
        List<SystemConfig> list = systemConfigDao.listSystemConfig4Member();
        return list;
    }
}
