package com.hydee.hdsec.entity;

import com.hydee.hdsec.entity.utils.DateUtil;

import java.util.Date;

/**
 * 培训课件实体
 */
public class CompanyTrainClass extends Page {
    private static final long serialVersionUID = 1L;
    private Long classId;

    private String title;//标题

    private String contentUrl;//内容地址

    private String content;//内容

    private Long createId;//创建人

    private Date createTime;//创建时间

    private Date updateTime;//修改时间

    private int likeNum;//点赞数

    private int readNum;//阅读数

    private String customerId;//所属公司

    private Integer status;//状态(1、草稿；2、待审核；3、进行中；4、审核不通过；5、已结束)

    private String remark;//备注

    private String isPosition;//推荐位

    private int exercisesCount;//习题数

    private int taskCount;//任务数

    private Integer contentType;//内容类型0：H5、1：PDF

    private String customerName;//公司名

    private Long baseId;//习题ID

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl == null ? null : contentUrl.trim();
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
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

    public String getIsPosition() {
        return isPosition;
    }

    public void setIsPosition(String isPosition) {
        this.isPosition = isPosition == null ? null : isPosition.trim();
    }

    public String getCreateTimeFmt() {
        return DateUtil.format(createTime).replace(" ","</br>");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getExercisesCount() {
        return exercisesCount;
    }

    public void setExercisesCount(int exercisesCount) {
        this.exercisesCount = exercisesCount;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }
}