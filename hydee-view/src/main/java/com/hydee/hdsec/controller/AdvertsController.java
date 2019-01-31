package com.hydee.hdsec.controller;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.AdvertsService;
import com.hydee.hdsec.service.CompanyService;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.HttpUtil;
import com.hydee.hdsec.util.ServerChargeUtil;
import com.hydee.hdsec.vo.JsonVo;
import com.hydee.hydee.service.*;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by King.Liu
 * 2016/8/24.
 */
@Controller
@RequestMapping(value = "/view/showAdverts")
public class AdvertsController extends BaseController{
    /**新增培训任务**/
    private static final String ADVERTS_DETAIL = "/system/advertisingStatistics/advertsDetail";

    @Autowired
    public AdvertsService advertsService;

    @RequestMapping(value = "/advertsDetail")
    public String advertsDetail(AppAdvert appAdvert, Model model, HttpServletRequest request) throws Exception{
        List<AppAdvert> appAdverts = advertsService.countAdvertDatas(appAdvert);
        model.addAttribute("reList",appAdverts);
        model.addAttribute("page",appAdvert);
        return ADVERTS_DETAIL;
    }


}
