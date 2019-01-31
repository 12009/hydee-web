package com.hydee.hdsec.sqlDao;

import com.hydee.hdsec.vo.FundWhereVo;
import org.springframework.stereotype.Repository;

/**
 * Created by 94908 on 2017/07/14.
 */
@Repository
public interface FundWhereDao {

	public Double countMaterialRedMoney(FundWhereVo fundWhereVo);
}
