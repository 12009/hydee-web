package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 数据字典
 */
public class CompanyDict implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long dictId;

    private String dictName;//字典名称

    private String dictType;//字典类别

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }
}