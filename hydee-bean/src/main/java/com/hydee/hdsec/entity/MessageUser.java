package com.hydee.hdsec.entity;

import java.io.Serializable;

/**
 * 数据库表MessageUser所对应的实体类
 * @author 
 *
 */
public class MessageUser implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String id;
    private String messageId;
    private String customerId;
    private String userId;
    private String hasRead;
    private String createTime;

    private String userName;//用户名称
    private String userPhone;//用户手机号
    private String secPhone;//小蜜手机号
    private String cid;//通知ID
    public MessageUser()
    {
        this.id="";
        this.messageId="";
        this.customerId="";
        this.userId="";
        this.hasRead="";
        this.createTime="";
        this.userName ="";
        this.userPhone="";
        this.secPhone="";
    }
    
    public MessageUser(String messageId, String customerId, String userId, String hasRead) {
		this.messageId = messageId;
		this.customerId = customerId;
		this.userId = userId;
		this.hasRead = hasRead;
	}

	/**
     * 返回id
     * @return
     */
    public String getId()
    {
        return this.id;
    }
    /**
     * 设置id
     * @param id
     */
    public void setId(String id)
    {
        this.id = id;
    }
    /**
     * 返回messageId
     * @return
     */
    public String getMessageId()
    {
        return this.messageId;
    }
    /**
     * 设置messageId
     * @param messageId
     */
    public void setMessageId(String messageId)
    {
        this.messageId = messageId;
    }
    /**
     * 返回customerId
     * @return
     */
    public String getCustomerId()
    {
        return this.customerId;
    }
    /**
     * 设置customerId
     * @param customerId
     */
    public void setCustomerId(String customerId)
    {
        this.customerId = customerId;
    }
    /**
     * 返回userId
     * @return
     */
    public String getUserId()
    {
        return this.userId;
    }
    /**
     * 设置userId
     * @param userId
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    /**
     * 返回hasRead
     * @return
     */
    public String getHasRead()
    {
        return this.hasRead;
    }
    /**
     * 设置hasRead
     * @param hasRead
     */
    public void setHasRead(String hasRead)
    {
        this.hasRead = hasRead;
    }
    /**
     * 返回createTime
     * @return
     */
    public String getCreateTime()
    {
        return this.createTime;
    }
    /**
     * 设置createTime
     * @param createTime
     */
    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }


    public String getSecPhone() {
        return secPhone;
    }

    public void setSecPhone(String secPhone) {
        this.secPhone = secPhone;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MessageUser)) return false;
        return this.userId.equals(((MessageUser) obj).getUserId());
    }
}
