package com.hydee.hdsec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hydee.hdsec.dao.CompanyMenuDao;
import com.hydee.hdsec.dao.base.BaseDao;
import com.hydee.hdsec.entity.CompanyMenu;

/**
 * 菜单Dao实现类
 * Created by Administrator on 2016/8/16.
 */
@Repository("companyMenuDao")
public class CompanyMenuDaoImpl extends BaseDao implements CompanyMenuDao{
    @Override
    public int deleteByPrimaryKey(Long menuId) {
        return (Integer) delete("companyMenuMapper.deleteByPrimaryKey", menuId);
    }

    @Override
    public int insert(CompanyMenu companyMenu) {
        return (Integer) save("companyMenuMapper.insert",companyMenu);
    }


    @Override
    public CompanyMenu selectByPrimaryKey(Long menuId) {
        return (CompanyMenu) findForObject("companyMenuMapper.selectByPrimaryKey",menuId);
    }

    @Override
    public int updateByPrimaryKeySelective(CompanyMenu companyMenu) {
        return (Integer) update("companyMenuMapper.updateByPrimaryKeySelective",companyMenu);
    }

    @Override
    public int updateByPrimaryKey(CompanyMenu record) {
        return 0;
    }

	@Override
	public List<CompanyMenu> listByRoleId(long roleId) {
		return (List<CompanyMenu>) findForList("companyMenuMapper.listByRoleId", roleId);
	}
}
