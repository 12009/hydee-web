package com.hydee.hdsec.sqlDao;

import com.hydee.hdsec.entity.AppAdvert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by King.Liu
 * 2017/1/4.
 */
@Repository
public interface AdvertsDao {
    public List<AppAdvert> advertsListServerPage(AppAdvert appAdvert);

    public List<AppAdvert> countAdvertDatasListServerPage(AppAdvert appAdvert);
}
