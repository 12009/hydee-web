package com.hydee.hydee.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hydee.hdsec.util.ExportExcelUtils;
import org.springframework.stereotype.Service;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.CompanyAccountWhereDao;
import com.hydee.hdsec.entity.CompanyAccountWhere;
import com.hydee.hydee.service.CompanyAccountWhereService;
import com.hydee.hdsec.util.EnumUtil;
import com.hydee.hdsec.util.StringUtil;
@Service
public class CompanyAccountWhereServiceImpl implements CompanyAccountWhereService {

	@Resource
	CompanyAccountWhereDao companyAccountWhereDao;
	
	@Override
	public List<CompanyAccountWhere> queryAccountWhereListPage(CompanyAccountWhere companyAccountWhere) throws ServiceException {

		return companyAccountWhereDao.queryAccountWhereListPage(companyAccountWhere);
	}

	@Override
	public void accountWhereExport(CompanyAccountWhere companyAccountWhere, HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
		List<CompanyAccountWhere> accountWhereList = companyAccountWhereDao.queryAccountWhere(companyAccountWhere);
		String exportName = "已参加培训员工明细表" + StringUtil.getDateStr();
		String sheetName = "已参加培训员工明细表";
		String columns[] = new String[]{"培训名称","培训创建时间","培训总金额","用户姓名","用户红包", "红包领取时间","流水号"};
		int columnWidth[] = new int[]{24, 24, 20, 20, 20, 20, 36};
		List<Object[]> data = new ArrayList<Object[]>(accountWhereList.size());
		for (CompanyAccountWhere accountWhere : accountWhereList) {
			data.add(new Object[]{accountWhere.getTitle(),accountWhere.getTaskCreateTimeFmt(),accountWhere.getMoneyReward(),accountWhere.getUserName()
					,accountWhere.getRedMoney(),accountWhere.getCreateTimeFmt(),accountWhere.getSerialNumber()});
		}
		ExportExcelUtils.simpleExportExcel(request, response, exportName, sheetName, columns,columnWidth, data);
	}

	@Override
	public List<CompanyAccountWhere> queryAccountWhere2ListPage(CompanyAccountWhere companyAccountWhere) throws ServiceException {
		return companyAccountWhereDao.queryAccountWhere2ListPage(companyAccountWhere);
	}

	@Override
	public double selectTotalRedMoney(String chainCustomerId, String userId) throws ServiceException {
		if(StringUtil.isBlanks(false, chainCustomerId, userId)){
			throw new ServiceException(ErrorCodes.UID_CHCID_IS_NULL);
		}
		CompanyAccountWhere accountWhere = new CompanyAccountWhere();
		accountWhere.setChainCustomerId(chainCustomerId);
		accountWhere.setUserId(userId);
		accountWhere.setDictId(EnumUtil.Dict.RED_MONEY.value);
		Double _count = companyAccountWhereDao.countRedMoney(accountWhere);
		return _count == null ? 0 : _count;
	}

	@Override
	public double getSumTaskCharge(String customerId) throws ServiceException {
		return companyAccountWhereDao.getSumTaskCharge(customerId);
	}

}
