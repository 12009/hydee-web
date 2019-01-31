package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.ErrorLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogDao {

    int insertSelective(ErrorLog record);
}