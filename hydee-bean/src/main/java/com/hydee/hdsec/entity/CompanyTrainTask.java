package com.hydee.hdsec.entity;

import com.hydee.hdsec.entity.utils.DateUtil;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.Date;

/**
 *培训任务实体
 */
public class CompanyTrainTask extends Page{
    private static final long serialVersionUID = 1L;
    private Long taskId;

    private String title;//标题

    private Long likeNum;//点赞数

    private Double moneyReward;//赏金总额

    private Double awardLow;//分档奖励低

    private Double awardMiddle;//分档奖励中

    private Double awardHigh;//分档奖励高

    private Date createTime;//创建时间

    private Long createId;//创建人

    private Date modifiedTime;//修改时间

    private Long modifiedId;//修改人

    private String customerId;//所属厂商

    private Date startTime;//开始时间

    private Date endTime;//结束时间

    private Double trainFund;//培训基金

    private Double serveCharge;//平台服务费

    private Integer status;//状态(1、草稿；2、即将开始；3、进行中；4、已结束；5：已参加：6：未参加)

    private Integer commissionType;//佣金类型（1：按钱、2：按百分比）

    private Double residueMoney;//剩余金额

    private Integer customerCount;//指定连锁数目

    private Integer joinCustomerCount;//参加连锁数目

    private Integer userCount;//参加人数

    private Integer commentCount;//评论数

    private Double whereFund;//已获得培训基金

    private Double whereRedMoney;//已获得赏金

    private String secCustomerId;//连锁公司ID

    private String customerName;//厂商名

    private Integer joinStatus;//是否参加

    private String userId;//用户ID

    private String imgUrl;//任务图片

    private Integer isParticipation;//是否参与(0:未参与、1:参与)

    private Integer isActivation;//是否激活(0:未激活、1:激活)

    private String busno;


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Long likeNum) {
        this.likeNum = likeNum;
    }

    public Double getMoneyReward() {
        return moneyReward;
    }

    public void setMoneyReward(Double moneyReward) {
        this.moneyReward = moneyReward;
    }

    public Double getAwardLow() {
        return awardLow;
    }

    public void setAwardLow(Double awardLow) {
        this.awardLow = awardLow;
    }

    public Double getAwardMiddle() {
        return awardMiddle;
    }

    public void setAwardMiddle(Double awardMiddle) {
        this.awardMiddle = awardMiddle;
    }

    public Double getAwardHigh() {
        return awardHigh;
    }

    public void setAwardHigh(Double awardHigh) {
        this.awardHigh = awardHigh;
    }

    public Date getCreateTime() {
        return createTime;
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

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Long getModifiedId() {
        return modifiedId;
    }

    public void setModifiedId(Long modifiedId) {
        this.modifiedId = modifiedId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getTrainFund() {
        return trainFund;
    }

    public void setTrainFund(Double trainFund) {
        this.trainFund = trainFund;
    }

    public Double getServeCharge() {
        return serveCharge;
    }

    public void setServeCharge(Double serveCharge) {
        this.serveCharge = serveCharge;
    }

    public Integer getStatus() {
//        if(2 == status) {
//            Date nowDate = new Date();
//            if(null != startTime || null != endTime){
//                if (startTime.getTime() <= nowDate.getTime() && endTime.getTime() >= nowDate.getTime()) {
//                    status = 3;
//                }
//            }
//
//        }
        if(null != status){
            if(4 != status){
                if(null != joinStatus){
                    status = joinStatus > 0 ? 5 : 6;
                }
            }
        }

        return status;
    }

    public void setStatus(Integer status) {

        this.status = status;
    }

    public Integer getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(Integer commissionType) {
        this.commissionType = commissionType;
    }

    public Double getResidueMoney() {
        return residueMoney;
    }

    public void setResidueMoney(Double residueMoney) {
        this.residueMoney = residueMoney;
    }

    public String getCreateTimeFmt() {
        return DateUtil.format(createTime).replace(" ","</br>");
    }

    public String getStartTimeFmt() {
        return DateUtil.format(startTime);
    }

    public String getEndTimeFmt() {
        return DateUtil.format(endTime);
    }

    public Integer getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }

    public Integer getJoinCustomerCount() {
        return joinCustomerCount;
    }

    public void setJoinCustomerCount(Integer joinCustomerCount) {
        this.joinCustomerCount = joinCustomerCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getSecCustomerId() {
        return secCustomerId;
    }

    public void setSecCustomerId(String secCustomerId) {
        this.secCustomerId = secCustomerId;
    }

    public Double getWhereFund() {
        return whereFund;
    }

    public void setWhereFund(Double whereFund) {
        this.whereFund = whereFund;
    }

    public Double getWhereRedMoney() {
        return whereRedMoney;
    }

    public void setWhereRedMoney(Double whereRedMoney) {
        this.whereRedMoney = whereRedMoney;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(Integer joinStatus) {
        this.joinStatus = joinStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getIsParticipation() {
        return isParticipation;
    }

    public void setIsParticipation(Integer isParticipation) {
        this.isParticipation = isParticipation;
    }

    public Integer getIsActivation() {
        return isActivation;
    }

    public void setIsActivation(Integer isActivation) {
        this.isActivation = isActivation;
    }

    @JsonIgnore
    public String getBusno() {
        return busno;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }
}