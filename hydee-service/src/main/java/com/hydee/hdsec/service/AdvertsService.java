package com.hydee.hdsec.service;

import com.hydee.hdsec.entity.AppAdvert;

import java.util.List;

public interface AdvertsService {

	public List<AppAdvert> advertsListServerPage(AppAdvert appAdvert);

	public List<AppAdvert> countAdvertDatas(AppAdvert appAdvert) throws Exception;
}
