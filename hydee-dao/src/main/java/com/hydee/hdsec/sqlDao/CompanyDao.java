package com.hydee.hdsec.sqlDao;


import com.hydee.hdsec.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyDao {

	List<Company> listCompany();

	Company getCompanyByCustomerId(String xmCustomerId);

}
