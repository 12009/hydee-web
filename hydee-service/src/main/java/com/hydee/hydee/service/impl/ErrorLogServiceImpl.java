package com.hydee.hydee.service.impl;

import com.hydee.hdsec.dao.ErrorLogDao;
import com.hydee.hdsec.entity.ErrorLog;
import com.hydee.hydee.service.ErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorLogServiceImpl implements ErrorLogService {
	
	@Autowired
	private ErrorLogDao errorLogDao;

	@Override
	public int insertErrorLog(ErrorLog errorLog) {
		return errorLogDao.insertSelective(errorLog);
	}
}
