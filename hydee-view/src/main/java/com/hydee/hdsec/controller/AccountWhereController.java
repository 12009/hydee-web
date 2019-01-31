package com.hydee.hdsec.controller;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.CompanyService;
import com.hydee.hdsec.service.MobileUserService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.DateUtil;
import com.hydee.hdsec.util.HttpUtil;
import com.hydee.hdsec.util.ServerChargeUtil;
import com.hydee.hdsec.vo.JsonVo;
import com.hydee.hydee.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by King.Liu
 * 2016/8/24.
 */
@Controller
@RequestMapping(value = "/view/fundsWhereabouts")
public class AccountWhereController extends BaseController{

    @Autowired
    public CompanyAccountWhereService companyAccountWhereService;

    @RequestMapping("/accountWhereExportEx")
    public void checkIn4ExportEx(CompanyAccountWhere vo,HttpServletRequest request, HttpServletResponse response) throws ServiceException, IOException {
        if(vo.getTitle() != null && !"".equals(vo.getTitle())) vo.setTitle(URLDecoder.decode(vo.getTitle(),"utf-8"));
        CompanyUser user = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        vo.setCustomerId(user.getCustomerId());
        companyAccountWhereService.accountWhereExport(vo,request,response);
    }


}
