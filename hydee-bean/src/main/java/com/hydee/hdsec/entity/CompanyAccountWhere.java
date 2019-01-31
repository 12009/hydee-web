package com.hydee.hdsec.entity;

import com.hydee.hdsec.entity.utils.DateUtil;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 资金去向
 */
public class CompanyAccountWhere extends SqlConditions {
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long taskId;//任务ID

    private Long dictId;//资金类型（字典表type:fund_type）

    private String userId;//用户ID
    
    private String userName;//用户名
    
    private String customerId;//所属公司

    private String customerName;//公司名称

    private Double redMoney;//红包金额

    private Double serveCharge;//平台服务费

    private Double serverDivided;//平台红包分成

    private Double trainFund;//培训基金

    private Double fundDivided;//基金分成

    private Double correct;//正确率

    private Date createTime;//领取（分成）时间

    private String phone;//联系电话

    private String serialNumber;//流水号

    private Integer type;//类别(1、活动;2、培训)

    private Double advertising;//广告费

    private Double favorable;//优惠卷

    private String name;//名称

    private String chainCustomerId;//连锁企业

    //外键表字段
    private String title;//培训名称
    private String dictName;//资金类型名称
    
    
    private int isCount;//是否开启统计 过滤  1则开启 0则不开启
    private Double redMoneyCount;//统计过滤红包的金额
    private Double serveChargeCount;//平台服务费过滤统计金额
    private Double trainFundCount;//培训基金过滤统计金额
    
   //网页参数 非数据库字段
    private String pStartTime; // 开始时间 日期格式：2016-09-05 或 2016-09-05 17:00:00（时分秒）为空则空字符 
    private String pEndTime;//结束时间  日期格式：2016-09-05 或 2016-09-05 17:00:00（时分秒） 为空则空字符

    private Date taskCreateTime;//任务创建时间
    private Double moneyReward;//任务总额

    private static SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public Double getRedMoney() {
        return redMoney;
    }

    public void setRedMoney(Double redMoney) {
        this.redMoney = redMoney;
    }

    public Double getServeCharge() {
        return serveCharge;
    }

    public void setServeCharge(Double serveCharge) {
        this.serveCharge = serveCharge;
    }

    public Double getServerDivided() {
        return serverDivided;
    }

    public void setServerDivided(Double serverDivided) {
        this.serverDivided = serverDivided;
    }

    public Double getTrainFund() {
        return trainFund;
    }

    public void setTrainFund(Double trainFund) {
        this.trainFund = trainFund;
    }

    public Double getFundDivided() {
        return fundDivided;
    }

    public void setFundDivided(Double fundDivided) {
        this.fundDivided = fundDivided;
    }

    public Double getCorrect() {
        return correct;
    }

    public void setCorrect(Double correct) {
        this.correct = correct;
    }

    public Date getCreateTime() {
        return createTime;
    }
    
    public String getCreateTimeFmt(){
    	return DateUtil.fomatDateForMinuteApp(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getAdvertising() {
        return advertising;
    }

    public void setAdvertising(Double advertising) {
        this.advertising = advertising;
    }

    public Double getFavorable() {
        return favorable;
    }

    public void setFavorable(Double favorable) {
        this.favorable = favorable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getRedMoneyCount() {
		return redMoneyCount;
	}

	public void setRedMoneyCount(Double redMoneyCount) {
		this.redMoneyCount = redMoneyCount;
	}

	public Double getServeChargeCount() {
		return serveChargeCount;
	}

	public void setServeChargeCount(Double serveChargeCount) {
		this.serveChargeCount = serveChargeCount;
	}

	public Double getTrainFundCount() {
		return trainFundCount;
	}

	public void setTrainFundCount(Double trainFundCount) {
		this.trainFundCount = trainFundCount;
	}

	public int getIsCount() {
		return isCount;
	}

	public void setIsCount(int isCount) {
		this.isCount = isCount;
	}

    public String getChainCustomerId() {
        return chainCustomerId;
    }

    public void setChainCustomerId(String chainCustomerId) {
        this.chainCustomerId = chainCustomerId;
    }

    @JsonIgnore
    public Double getMoneyReward() {
        return moneyReward;
    }

    public void setMoneyReward(Double moneyReward) {
        this.moneyReward = moneyReward;
    }

    @JsonIgnore
    public Date getTaskCreateTime() {
        return taskCreateTime;
    }

    public void setTaskCreateTime(Date taskCreateTime) {
        this.taskCreateTime = taskCreateTime;
    }

    @JsonIgnore
    public String getTaskCreateTimeFmt(){
        return dateFmt.format(taskCreateTime);
    }
}