package com.hydee.hdsec.vo;

import java.util.Date;

/**
 * 资金去向
 */
public class FundWhereVo {
    private String userId;//用户ID

    private String customerId;//所属连锁

    private String dictCode;//资金类型

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}