package com.hydee.hdsec.controller.interfaceSec;

import com.hydee.hdsec.entity.Company;
import com.hydee.hdsec.service.CompanyService;
import com.hydee.hdsec.vo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/12/28.
 */
@Controller
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @RequestMapping(value = "/listCompany")
    @ResponseBody
    public Object balanceDetail(String chainCustomerId,String userId) throws Exception{
//        JsonVo<Map<String,Object>> result = new JsonVo<Map<String,Object>>();
        List<Company> companyList = companyService.listCompany();
//        Map<String,Object> map = new HashMap<String, Object>();
//        map.put("limitBalance", companyList);
//        result.setData(map);
        return companyList;
    }
}
