package com.hydee.hdsec.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hydee.hdsec.entity.utils.DateUtil;

/**
 *用户实体类
 */
public class CompanyUser extends Page {
    private static final long serialVersionUID = 1L;
    private Long id;//用户id

    private Long roleId;//角色ID
    
    private String roleName;//角色名称

    private String userName;//用户名称

    private String realName;//登陆名

    private String userPassword;//用户密码

    private String userPhone;//用户手机

    private String email;//用户邮箱

    private String owner;//使用人

    private String customerId;//厂商ID

    private String company;//厂商名称

    private Date lastLoginTime;//最后登陆时间

    private Date createTime;//创建时间

    private Long createId;//创建人

    private Date modifiedTime;//修改时间

    private Long modifiedId;//修改id

    private Integer status;//状态（1、已启用，2、已锁定）

    private String loginIp;//登陆IP

    private String remark;//备注

    private String verifiCode;//验证码
    
    private Date verifiCodeCreateTime;//验证码创建时间
    
    private String token;//保持登录凭证

    private String ydjToken;//药店加登录

    private Double serveCharge;//平台服务费

    private Double serveChargePercent;//平台服务费百分比
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserPassword() {
        return StringUtils.isBlank(userPassword) ? null : userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    public String getLastLoginTimeFmt() {
        return DateUtil.format(lastLoginTime);
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
    
    public String getCreateTimeFmt() {
        return DateUtil.format(createTime);
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }

	public Date getVerifiCodeCreateTime() {
		return verifiCodeCreateTime;
	}

	public void setVerifiCodeCreateTime(Date verifiCodeCreateTime) {
		this.verifiCodeCreateTime = verifiCodeCreateTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	// 判断是否为超级管理员
	public boolean getIsSysAdmin() {
		return this.id == 1L;
	}
	// 判断是否为管理员
	public boolean getIsAdmin(){
		return this.createId == 1L;
	}

    public String getYdjToken() {
        return ydjToken;
    }

    public void setYdjToken(String ydjToken) {
        this.ydjToken = ydjToken;
    }

    public Double getServeCharge() {
        return serveCharge;
    }

    public void setServeCharge(Double serveCharge) {
        this.serveCharge = serveCharge;
    }

    public Double getServeChargePercent() {
        return serveChargePercent;
    }

    public void setServeChargePercent(Double serveChargePercent) {
        this.serveChargePercent = serveChargePercent;
    }
}