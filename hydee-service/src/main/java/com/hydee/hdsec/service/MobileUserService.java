package com.hydee.hdsec.service;

import com.hydee.hdsec.entity.MobileUser;

import java.util.List;
import java.util.Map;

public interface MobileUserService {
    public MobileUser getByMobileUser(Map<String,Object> map);

    public List<MobileUser> queryXmUserIdByCid(String customerId);

    public List<MobileUser> queryMobileUserByCustomerId(String customerId);
}
