package com.hydee.hdsec.entity;

import com.hydee.hdsec.entity.utils.DateUtil;

import java.util.Date;

/**
 * 账户充值
 */
public class CompanyAccountRecharge extends Page{
    private static final long serialVersionUID = 1L;
    private Long id;

    private String accountName;//账户名

    private String bankCard;//银行卡号

    private String bankName;//所属银行

    private String amount;//充值金额

    private String phone;//联系电话

    private String serialNumber;//流水号

    private Long dictId;//支付方式（字典表type:payment）

    private String customerId;//所属公司

    private Integer status;//-1:所有状态、0:待确认、1:充值成功、2:充值失败

    private String remark;//备注

    private Date createTime;//创建时间

    private Long createId;//创建人

    private String transferProof;//转账凭证
    
    //外键表字段
    private String orgName;//连锁企业
    
    private String userName;//用户名
    
    private String dictName;//充值方式字符串
    
    //网页参数 非数据库字段
    
    private String pName;//连锁企业/姓名查询 状态  为空则空字符
    private String pStartTime; // 开始时间 日期格式：2016-09-05 或 2016-09-05 17:00:00（时分秒）为空则空字符 
    private String pEndTime;//结束时间  日期格式：2016-09-05 或 2016-09-05 17:00:00（时分秒） 为空则空字符
    
    
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard == null ? null : bankCard.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
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
        return DateUtil.format(createTime).replace(" ","</br>");
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public Long getCreateId() {
		return createId;
	}

	public void setCreateId(Long createId) {
		this.createId = createId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpStartTime() {
		return pStartTime;
	}

	public void setpStartTime(String pStartTime) {
		this.pStartTime = pStartTime;
	}

	public String getpEndTime() {
		return pEndTime;
	}

	public void setpEndTime(String pEndTime) {
		this.pEndTime = pEndTime;
	}


	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

    public String getTransferProof() {
        return transferProof;
    }

    public void setTransferProof(String transferProof) {
        this.transferProof = transferProof;
    }
}