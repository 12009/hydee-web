package com.hydee.hdsec.entity;

import com.hydee.hdsec.entity.utils.DateUtil;
import com.hydee.hdsec.entity.utils.StringUtil;

import java.util.Date;

/**
 * 培训任务评论实体
 */
public class CompanyTrainTaskComment extends Page{
    private static final long serialVersionUID = 1L;
    private Long id;

    private Long taskId;//任务ID

    private String content;//内容

    private String fromUserId;//评论人ID

    private String fromUserName;//评论人

    private String toUserId;//被回复人ID

    private String toUserName;//被回复人

    private Date commentTime;//评论时间

    public CompanyTrainTaskComment() {
        this.content = "";
        this.fromUserId = "";
        this.fromUserName = "";
        this.toUserId = "";
        this.toUserName = "";
    }

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

    public String getContent() {
        return StringUtil.unicode2String(content);
    }

    public String getContentStore(){
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId == null ? null : fromUserId.trim();
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName == null ? null : fromUserName.trim();
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId == null ? null : toUserId.trim();
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName == null ? null : toUserName.trim();
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentTimeFmt() {
        return DateUtil.formatShare(DateUtil.format(commentTime));
    }
}