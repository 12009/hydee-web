package com.hydee.hydee.service;

import com.hydee.hdsec.entity.CompanyOrganization;

import java.util.List;

public interface CompanyOrgService {
	/**
	 * 保存/新增药企厂商
	 * @param org		:厂商对象
	 * @return
	 * @throws Exception
	 */
	int saveOrUpdate(CompanyOrganization org) throws Exception;
	/**
	 * 根据机构名获取对应机构
	 * @return
	 * @throws Exception
	 */
	CompanyOrganization findOrgByOrgname(CompanyOrganization org) throws Exception;
	/**
	 * 根据机构ID获取对应机构
	 * @param customerId
	 * @return
	 * @throws Exception
	 */
	CompanyOrganization findByCustomerId(String customerId) throws Exception;

	List<CompanyOrganization> selectOrganization() throws Exception;
}
