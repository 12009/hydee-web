package com.hydee.hydee.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyUserDao;
import com.hydee.hdsec.entity.CompanyOrganization;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hydee.service.CompanyOrgService;
import com.hydee.hydee.service.CompanyUserService;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.MD5;
import com.hydee.hdsec.util.StringUtil;

/**
 * 用户 业务层实现类
 * Created by Administrator on 2016/8/16.
 */
@Service
public class CompanyUserServiceImpl implements CompanyUserService {
	@Autowired
    CompanyUserDao companyUserDao;
    @Autowired
    CompanyOrgService orgService;

    /**
     * 根据用户名和密码查询数据
     * @param companyUser
     * @return
     */
    @Override
    public CompanyUser selectUserByPassWord(CompanyUser companyUser) throws Exception{
        return companyUserDao.selectUserByPassWord(companyUser);
    }

    @Override
    public int updateByCompanyUser(CompanyUser companyUser) throws Exception{
        return companyUserDao.updateByCompanyUser(companyUser);
    }

	@Override
	public CompanyUser findUserByUsername(CompanyUser user) throws Exception{
		CompanyUser companyUser = new CompanyUser();
		companyUser.setUserName(user.getUserName());
		companyUser.setId(user.getId());
		return companyUserDao.selectByArgs(companyUser);
	}
	
	@Override
	public int registUser(CompanyUser companyUser) throws Exception {
		// 创建厂商
		CompanyOrganization org = new CompanyOrganization();
		org.setOrgName(companyUser.getCompany());
		orgService.saveOrUpdate(org);
		// 设置用户状态未待审核
		companyUser.setStatus(EnumUtil.LoginStatus.STATUS_AUTHSTR.value);
		// 加密用户密码
		companyUser.setUserPassword(MD5.md5(companyUser.getUserPassword()));
		companyUser.setCreateId(1L);
		companyUser.setCustomerId(org.getCustomerId());
		int addCount = companyUserDao.insertCompanyUser(companyUser);			
		return addCount;
	}

	@Override
	public CompanyUser findeUserByUsernameAndEmail(String userName, String email) throws Exception {
		if(StringUtil.isBlanks(true, userName, email)){
			throw new ServiceException(ErrorCodes.EMAIL_USERNAME_NOT_NULL);
		}
		CompanyUser companyUser = new CompanyUser();
		companyUser.setUserName(userName);
		companyUser.setEmail(email);
		return companyUserDao.selectByArgs(companyUser);
	}

	@Override
	public int updateVerifyCode(Long id, String verifiCode) throws Exception {
		CompanyUser companyUser = new CompanyUser();
		companyUser.setId(id);
		companyUser.setVerifiCode(verifiCode);
		return companyUserDao.updateByCompanyUser(companyUser);
	}

	@Override
	public String getUsefulVerifiCode(String userName) throws Exception {
		return companyUserDao.findUsefulVerifiCodeByUsername(userName);
	}
	/**
	 * 列出用户所管辖的所有药企用户详情列表
	 */
	@Override
	public List<CompanyUser> listByCreaterIdListPage(CompanyUser user) throws Exception {
		return companyUserDao.listByCreaterIdListPage(user);
	}

	@Override
	public CompanyUser findUserById(Long userId) throws Exception {
		return companyUserDao.selectById(userId);
	}

	@Override
	public int saveOrUpdate(CompanyUser user) throws Exception {
		// 加密用户密码
		if(!StringUtil.isBlank(user.getUserPassword())){
			user.setUserPassword(MD5.md5(user.getUserPassword()));
		}else{
			user.setUserPassword(null);
		}
		if(user.getId() == null){
			return companyUserDao.insertCompanyUser(user);
		}else{
			return companyUserDao.updateByCompanyUser(user);
		}
	}

	@Override
	public CompanyUser findAdminByCustomerId(String customerId) throws Exception {
		return companyUserDao.findAdminByCustomerId(customerId);
	}

	@Override
	public CompanyUser selectUserByParams(Map<String, Object> map) throws Exception {
		return companyUserDao.selectUserByParams(map);
	}
}
