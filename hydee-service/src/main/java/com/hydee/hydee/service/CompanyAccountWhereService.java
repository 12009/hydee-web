package com.hydee.hydee.service;

import java.io.IOException;
import java.util.List;

import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.CompanyAccountWhere;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface CompanyAccountWhereService {
	 /**
     * 分页查询记录 条件<br>
     * 权限：管理员+商户
     * @param companyAccountWhere 详见此类网页参数
     * @return 返回分页充值记录  失败返回null
     * */
    List<CompanyAccountWhere> queryAccountWhereListPage(CompanyAccountWhere companyAccountWhere) throws ServiceException ;

    /**
     * 导出接口
     * @param companyAccountWhere
     * @return
     * @throws ServiceException
     */
    public void accountWhereExport(CompanyAccountWhere companyAccountWhere, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException;

    List<CompanyAccountWhere> queryAccountWhere2ListPage(CompanyAccountWhere companyAccountWhere) throws ServiceException ;
    /**
     * 查询用户所获得总赏金
     * @param customerId
     * @param userId
     * @return
     */
	double selectTotalRedMoney(String customerId, String userId) throws ServiceException;

	double getSumTaskCharge(String customerId) throws ServiceException;

}