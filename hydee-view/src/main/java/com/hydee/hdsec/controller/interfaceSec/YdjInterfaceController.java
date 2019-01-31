package com.hydee.hdsec.controller.interfaceSec;

import com.hydee.hdsec.controller.BaseController;
import com.hydee.hdsec.entity.*;
import com.hydee.hydee.service.CompanyUserService;
import com.hydee.hdsec.vo.JsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 药店家接口
 */
@Controller
@RequestMapping(value = "/ydjInterface")
public class YdjInterfaceController extends BaseController{

    @Autowired
    CompanyUserService companyUserService;

    /**
     *
     * @param companyUser(ydjToken)
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/ydjLogin")
    @ResponseBody
    public Object ydjLogin(CompanyUser companyUser, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception{
        JsonVo<Map<String,Object>> jsonObj = new JsonVo<Map<String,Object>>();
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String,Object> map = new HashMap<String,Object>();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("ydjToken",companyUser.getYdjToken());
        CompanyUser users = companyUserService.selectUserByParams(params);
        map.put("users",users);
        jsonObj.setData(map);
        return jsonObj;
    }


}
