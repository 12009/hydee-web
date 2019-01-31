package com.hydee.hdsec.entity;

public class ErrorLog {
    private Long id;

    private String errorMsg;

    private String errorSource;

    private String errorSourceId;

    private String remark;

    private String errorType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? null : errorMsg.trim();
    }

    public String getErrorSource() {
        return errorSource;
    }

    public void setErrorSource(String errorSource) {
        this.errorSource = errorSource == null ? null : errorSource.trim();
    }

    public String getErrorSourceId() {
        return errorSourceId;
    }

    public void setErrorSourceId(String errorSourceId) {
        this.errorSourceId = errorSourceId == null ? null : errorSourceId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType == null ? null : errorType.trim();
    }
}