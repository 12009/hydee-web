package com.hydee.hdsec.entity;

import java.util.Date;

import com.hydee.hdsec.entity.utils.DateUtil;

/**
 * 提现表实体
 */
public class CompanyWithdrawal extends SqlConditions{
    private static final long serialVersionUID = 1L;
    private Long id;

    private String serialNumber;//流水号

    private String userId;//用户ID

    private String userName;//用户名

    private String customerId;//所属公司

    private String customerName;//公司名

    private String phone;//联系电话

    private Double putMoney;//提现金额

    private String bankName;//开户行

    private String bankCard;//银行卡号

    private Integer status;//状态

    private String remark;//备注

    private Date createTime;//创建时间
    
    private Date remitTime;//打款时间
    
    private String remittorId;//打款人ID
    
    private String remittorName;//打款人姓名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Double getPutMoney() {
        return putMoney;
    }

    public void setPutMoney(Double putMoney) {
        this.putMoney = putMoney;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }
    
    public String getCreateTimeFmt() {
        return DateUtil.fomatDateForApp(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Date getRemitTime() {
		return remitTime;
	}
	public String getRemitTimeFmt() {
		return DateUtil.fomatDateForApp(remitTime);
	}

	public void setRemitTime(Date remitTime) {
		this.remitTime = remitTime;
	}

	public String getRemittorId() {
		return remittorId;
	}

	public void setRemittorId(String remittorId) {
		this.remittorId = remittorId;
	}

	public String getRemittorName() {
		return remittorName;
	}

	public void setRemittorName(String remittorName) {
		this.remittorName = remittorName;
	}
    
}