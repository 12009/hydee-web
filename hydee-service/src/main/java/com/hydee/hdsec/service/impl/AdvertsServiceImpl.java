package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.entity.AppAdvert;
import com.hydee.hdsec.service.AdvertsService;
import com.hydee.hdsec.sqlDao.AdvertsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertsServiceImpl implements AdvertsService {
	@Autowired
	AdvertsDao advertsDao;

	@Override
	public List<AppAdvert> advertsListServerPage(AppAdvert appAdvert) {
		return advertsDao.advertsListServerPage(appAdvert);
	}

	@Override
	public List<AppAdvert> countAdvertDatas(AppAdvert appAdvert) throws Exception {
		return advertsDao.countAdvertDatasListServerPage(appAdvert);
	}
}
