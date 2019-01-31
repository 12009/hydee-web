package com.hydee.hdsec.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.hydee.hdsec.entity.utils.DateUtil;

/**
 * 角色ID
 */
public class CompanyRole implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleId;

    private String roleName;//角色名称

    private String roleType;//角色类型

    private Date createTime;//创建时间
    
    private Long createId;//创建id

    private Date modifiedTime;//修改时间

    private Long modifiedId;//修改id
    
    private int count;//使用本角色的用户统计
    
    private List<Long> menuIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleType() {
        return roleType == null ? "1" : roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType == null ? null : roleType.trim();
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.menuIds = menuIds;
	}
}