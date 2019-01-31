package com.hydee.hdsec.controller;

import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.*;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.vo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/8.
 */
@Controller
@RequestMapping(value = "/view/accountOverviewDetail")
public class AccountOverViewController {
	@Autowired
	CompanyAccountService companyAccountService;

	/**
	 * 查询30天任务人数
	 * @param model
	 * @param request
	 * @param rsp
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = "/showMontTaskCount")
	@ResponseBody
	public Object showMontTaskCount(Model model,HttpServletRequest request,HttpServletResponse rsp) throws Exception {
		CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> result = new HashMap<String, Object>();
		JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
		map.put("customerId",currUser.getCustomerId());
		List<String> monthTaskDate = companyAccountService.queryMonthTaskByCustomer(map);
		result.put("monthTaskDate", monthTaskDate);
		jsonObj.setData(result);
		return jsonObj;
	}
    
}
