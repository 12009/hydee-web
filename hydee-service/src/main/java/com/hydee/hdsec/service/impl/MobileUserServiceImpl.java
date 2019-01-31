package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.entity.MobileUser;
import com.hydee.hdsec.service.MobileUserService;
import com.hydee.hdsec.sqlDao.MobileUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ************************************************************************
 * **                              _oo0oo_                               **
 * **                             o8888888o                              **
 * **                             88" . "88                              **
 * **                             (| -_- |)                              **
 * **                             0\  =  /0                              **
 * **                           ___/'---'\___                            **
 * **                        .' \\\|     |// '.                          **
 * **                       / \\\|||  :  |||// \\                        **
 * **                      / _ ||||| -:- |||||- \\                       **
 * **                      | |  \\\\  -  /// |   |                       **
 * **                      | \_|  ''\---/''  |_/ |                       **
 * **                      \  .-\__  '-'  __/-.  /                       **
 * **                    ___'. .'  /--.--\  '. .'___                     **
 * **                 ."" '<  '.___\_<|>_/___.' >'  "".                  **
 * **                | | : '-  \'.;'\ _ /';.'/ - ' : | |                 **
 * **                \  \ '_.   \_ __\ /__ _/   .-' /  /                 **
 * **            ====='-.____'.___ \_____/___.-'____.-'=====             **
 * **                              '=---='                               **
 * ************************************************************************
 * **                        佛祖保佑      镇类之宝                         **
 * ************************************************************************
 * Created by King.Liu
 */
@Service
public class MobileUserServiceImpl implements MobileUserService{
    @Autowired
    private MobileUserDao mobileUserDao;

    @Override
    public MobileUser getByMobileUser(Map<String,Object> map) {
        return mobileUserDao.getByMobileUser(map);
    }

    @Override
    public List<MobileUser> queryXmUserIdByCid(String customerId) {
        return mobileUserDao.queryXmUserIdByCid(customerId);
    }

    @Override
    public List<MobileUser> queryMobileUserByCustomerId(String customerId) {
        return mobileUserDao.queryMobileUserByCustomerId(customerId);
    }
}
