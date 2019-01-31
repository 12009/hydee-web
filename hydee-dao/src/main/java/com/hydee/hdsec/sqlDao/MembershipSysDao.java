package com.hydee.hdsec.sqlDao;

import com.hydee.hdsec.entity.ExpVal;
import org.springframework.stereotype.Repository;

/**
 * Created by 94908 on 2017/4/19/0019.
 */
@Repository
public interface MembershipSysDao {
    int setPoints(ExpVal expVal);
}
