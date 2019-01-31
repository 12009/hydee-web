package com.hydee.hdsec.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.service.*;
import com.hydee.hdsec.util.LimitUtil;
import com.hydee.hydee.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hydee.hdsec.util.Const;

/**
 * Created by Administrator on 2016/7/4.
 */
@Controller
@RequestMapping(value = "/view")
public class ViewController {
	/** 首页 **/
    private static final String INDEX_VIEW = "/main/index";
    /** 账户总览 **/
    private static final String ACCOUNT_OVERVIEW_DETAIL ="/system/accountOverview/accountOverviewDetail";
    /** 角色管理 **/
    private static final String ROLE_MAN = "/system/roleMan/roleMan";
    /** 账号列表 **/
    private static final String ACCOUNT_NUM_MAN = "/system/accountNumMan/accountNumMan";
    /** 培训课件 **/
    private static final String TRAIN_CLASS = "/system/myTraining/trainingList";
    /** 培训任务 **/
    private static final String TASK_CLASS = "/system/myTraining/trainingTaskList";
	/** 培训习题  **/
	private static final String TRAININGEXERCISES = "/system/myTraining/trainingExercises";
    /** 账户列表 **/
    private static final String ACCOUNT_MAN = "/system/accountMan/accountMan";
    /** 充值管理 **/
    private static final String RECHARGEMANAGEMENT = "/system/accountMan/rechargemanagement";
    /** 充值添加 **/
    private static final String ACCOUNTRECHARGE = "/system/accountInformation/accountRecharge";
    /** 充值记录 **/
//    private static final String RECHARGERECORD = "/system/accountInformation/rechargeRecord";
    /** 公司信息 **/
	private static final String COMPANY_INFOMATION = "/system/accountInformation/companyInformation"; 
	/** 资金去向 **/
	private static final String FUNDSWHEREABOUTS = "/system/accountInformation/fundsWhereabouts"; 
	/** 资金去向详细 **/
	private static final String FUNDSWHEREABOUTSDETAIL = "/system/accountInformation/jijinmingxi"; 
	/** 冻结余额  **/
	private static final String FROZENBALANCE = "/system/accountInformation/frozenBalance";
	/** 冻结余额  **/
	private static final String ADVERTISINGSTATISTICS = "/system/advertisingStatistics/advertisingStatistics";

    @Autowired
    CompanyMenuService menuService;
    @Autowired
	CompanyUserService userService;
    @Autowired
	CompanyRoleService roleService;
    @Autowired
	CompanyOrgService orgService;
    @Autowired
	CompanyAccountRechargeService accountRechargeService;
    @Autowired
    CompanyTrainCLassService companyTrainCLassService;
    @Autowired
    CompanyTrainTaskService companyTrainTaskService;
    @Autowired
	CompanyAccountWhereService companyAccountWhereService;
	@Autowired
	CompanyAccountService companyAccountService;
	@Autowired
	CompanyTrainBaseService companyTrainBaseService;
	@Autowired
	CompanyTrainExercisesTypeService companyTrainExercisesTypeService;
	@Autowired
	CompanyDictService companyDictService;
	@Autowired
	AdvertsService advertsService;
	@Autowired
	CompanyOrgService companyOrgService;
	@Autowired
	CompanyService companyService;
    /**
     * 访问-首页
     * @param model
     * @param request
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    public String toIndex(Model model,HttpServletRequest request,HttpServletResponse rsp) throws Exception {
    	List<CompanyMenu> menues = (List<CompanyMenu>) request.getSession().getAttribute(Const.SESSION_MENUES);
		CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	menues = menuService.resetMenuesForPage(menues);
		CompanyOrganization organization = companyOrgService.findByCustomerId(currUser.getCustomerId());
    	model.addAttribute("menues", menues);
    	model.addAttribute("org", organization);
        return INDEX_VIEW;
    }

    /**
     * 访问-账户总览
     * @param rsp
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/accountOverviewDetail")
    public String toViewAccountOverviewDetail(Model model,HttpServletRequest request,HttpServletResponse rsp) throws Exception {
		CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		CompanyAccount account = companyAccountService.selectByCustomerId(currUser.getCustomerId());
		int doingAccount = companyAccountService.getTaskDoingAccount(currUser.getCustomerId());
		double taskCharge = companyAccountWhereService.getSumTaskCharge(currUser.getCustomerId());
		double taskFrozen = companyTrainTaskService.getTaskFrozenCharge(currUser.getCustomerId());
		int taskUserAccount = companyTrainTaskService.selectTaskUserAccount(currUser.getCustomerId());
		model.addAttribute("availableBalance", account == null ? "0" : account.getAvailableBalance());
		model.addAttribute("doingAccount", doingAccount);
		model.addAttribute("taskCharge", taskCharge);
		model.addAttribute("taskFrozen", taskFrozen);
		model.addAttribute("taskUserAccount", taskUserAccount);
        return ACCOUNT_OVERVIEW_DETAIL;
    }
    /**
     * 访问-角色管理
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/roleMan")
    public String toViewRoleMan(CompanyUser user,Model model,HttpServletRequest request) throws Exception{
    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	user.setCreateId(currUser.getId());
    	List<CompanyMenu> menues = (List<CompanyMenu>) request.getSession().getAttribute(Const.SESSION_MENUES);
    	List<CompanyRole> reList = roleService.listByCreateIdListPage(user);
    	menues = menuService.resetMenuesForPage(menues);
    	model.addAttribute("menues", menues);
    	model.addAttribute("reList", reList);
    	model.addAttribute("page", user);
        return ROLE_MAN;
    }
    /**
     * 访问-账号管理(药企)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/accountNumMan")
    public String toViewAccountNumMan(CompanyUser user,Model model,HttpServletRequest request) throws Exception{
    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	user.setCreateId(currUser.getId());
    	List<CompanyUser> compAdmins = userService.listByCreaterIdListPage(user);
    	List<CompanyRole> roleList = roleService.listByCreateId(currUser.getId());
    	model.addAttribute("reList", compAdmins);
    	model.addAttribute("roleList", roleList);
    	model.addAttribute("page", user);
        return ACCOUNT_NUM_MAN;
    }

	/**
	 * 访问-培训课件
	 * @param companyTrainClass
	 * @param model
	 * @param request
	 * @return
     * @throws Exception
     */
    @RequestMapping(value = "/trainClass")
    public String toViewTrainClass(CompanyTrainClass companyTrainClass,Model model,HttpServletRequest request) throws Exception{
        CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        companyTrainClass.setCustomerId(currUser.getCustomerId());
        List<CompanyTrainClass> companyTrainClassList = companyTrainCLassService.queryClassListPage(companyTrainClass);
        model.addAttribute("reList", companyTrainClassList);
        model.addAttribute("page", companyTrainClass);
        return TRAIN_CLASS;
    }

	/**
	 * 访问-培训任务
	 * @param companyTrainTask
	 * @param model
	 * @param request
	 * @return
     * @throws Exception
     */
    @RequestMapping(value = "/trainTask")
    public String toViewTrainTask(CompanyTrainTask companyTrainTask, Model model,HttpServletRequest request) throws Exception{
        CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
        if(currUser.getIsSysAdmin()){
			companyTrainTask.setFindType("1");
		}
        companyTrainTask.setCustomerId(currUser.getCustomerId());
        List<CompanyTrainTask> companyTrainTaskList = companyTrainTaskService.queryTaskListPage(companyTrainTask);
        model.addAttribute("reList", companyTrainTaskList);
        model.addAttribute("page", companyTrainTask);
        return TASK_CLASS;
    }

	/**
	 * 访问-培训习题
	 * @param companyTrainBase
	 * @param model
	 * @param request
	 * @return
     * @throws Exception
     */
    @RequestMapping(value = "/trainingExercises")
    public String trainingExercises(CompanyTrainBase companyTrainBase, Model model,HttpServletRequest request) throws Exception{
        CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		companyTrainBase.setCustomerId(currUser.getCustomerId());
		List<CompanyTrainBase> companyTrainBases = companyTrainBaseService.selectBaseListPage(companyTrainBase);
		CompanyTrainExercisesType exercisesType = new CompanyTrainExercisesType();
		exercisesType.setCustomerId(currUser.getCustomerId());
		List<CompanyTrainExercisesType> trainExercisesTypes = companyTrainExercisesTypeService.selectTypeByCustomerId(exercisesType);
		model.addAttribute("reList", companyTrainBases);
		model.addAttribute("exercisesTypes", trainExercisesTypes);
		model.addAttribute("limitCount", LimitUtil.exercisesLimitCount);
		model.addAttribute("page", companyTrainBase);
        return TRAININGEXERCISES;
    }

    /**
     * 访问-账户管理(超管)
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/accountMan")
    public String toViewAccountMan(CompanyUser user,Model model,HttpServletRequest request) throws Exception{
    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	user.setCreateId(currUser.getId());
    	List<CompanyUser> compAdmins = userService.listByCreaterIdListPage(user);
    	List<CompanyRole> roleList = roleService.listByCreateId(currUser.getId());
		List<Company> companyList = companyService.listCompany();
    	model.addAttribute("reList", compAdmins);
    	model.addAttribute("compList", companyList);
    	model.addAttribute("roleList", roleList);
    	model.addAttribute("page", user);
        return ACCOUNT_MAN;
    }
    
    /**
     * 访问-厂家信息
     * @param user
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/companyInformation")
    public String toViewCompanyInfo(CompanyUser user,Model model,HttpServletRequest request) throws Exception{
    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	CompanyOrganization org = orgService.findByCustomerId(currUser.getCustomerId());
    	if(!currUser.getIsSysAdmin()){
    		CompanyUser adminUser = userService.findAdminByCustomerId(currUser.getCustomerId());
    		model.addAttribute("adminUser", adminUser);
    	}
    	model.addAttribute("org", org);
        return COMPANY_INFOMATION;
    }
    
    //充值管理页
    @RequestMapping(value = "/companyAccountRecharge")
    public String toViewCompanyAccountRecharge(HttpServletRequest request,Model model,CompanyAccountRecharge companyAccountRecharge) throws Exception{
    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	List<CompanyAccountRecharge> companyAccountRechargeList = accountRechargeService.queryRechargeListPage(companyAccountRecharge);
 
    	model.addAttribute("companyAccountRechargeList", companyAccountRechargeList);
    	model.addAttribute("page", companyAccountRecharge);
        return RECHARGEMANAGEMENT;
    }

	/**
	 * 广告页显示
	 * @param request
	 * @param model
	 * @param appAdvert
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = "/showAdverts")
	public String toViewCompanyAccountRecharge(HttpServletRequest request,Model model,AppAdvert appAdvert) throws Exception{
		CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
		appAdvert.setCompanyId(currUser.getCustomerId());
		List<AppAdvert> appAdverts = advertsService.advertsListServerPage(appAdvert);
		model.addAttribute("reList", appAdverts);
		model.addAttribute("page", appAdvert);
		return ADVERTISINGSTATISTICS;
	}

    //充值记录页面
    @RequestMapping(value = "/accountRecharge")
    public String toViewRechargeRecord(HttpServletRequest request,Model model,CompanyAccountRecharge companyAccountRecharge) throws Exception{
    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	//如果不是超级管理员 则设置用户ID 在查询时只查询当前用户ID的记录
    	if(!currUser.getIsSysAdmin()){
    		companyAccountRecharge.setCreateId(currUser.getId());
    	}
    	List<CompanyAccountRecharge> companyAccountRechargeList = accountRechargeService.queryRechargeListPage(companyAccountRecharge);
		List<CompanyDict> companyDicts = companyDictService.selectByType("payment");
		CompanyAccount account = companyAccountService.selectByCustomerId(currUser.getCustomerId());
		model.addAttribute("availableBalance", account == null ? "0" : account.getAvailableBalance());
    	model.addAttribute("companyAccountRechargeList", companyAccountRechargeList);
    	model.addAttribute("companyDicts", companyDicts);
    	model.addAttribute("page", companyAccountRecharge);
        return ACCOUNTRECHARGE;
    }
    
    //资金去向页
    @RequestMapping(value = "/fundsWhereabouts")
    public String toViewFundsWhereabouts(HttpServletRequest request,Model model,
    		@RequestParam(value="title", required=false)String title, 
    		@RequestParam(value="dictId", required=false)Long dictId, 
    		@RequestParam(value="pStartTime", required=false)String pStartTime, 
    		@RequestParam(value="pEndTime", required=false)String pEndTime, 
    		@RequestParam(value="currentPage", required=false)Integer currentPage,
    		@RequestParam(value="showCount", required=false)Integer showCount,String tabType) throws Exception{
    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	CompanyAccountWhere companyAccountWhere = new CompanyAccountWhere();
    	companyAccountWhere.setTitle(title);
    	companyAccountWhere.setDictId(dictId);
    	companyAccountWhere.setpStartTime(pStartTime);
    	companyAccountWhere.setpEndTime(pEndTime);
    	companyAccountWhere.setCurrentPage(currentPage==null?1:currentPage);
    	companyAccountWhere.setShowCount(showCount==null?5:showCount);
    	//如果不是超级管理员 则设置用户ID 在查询时只查询当前用户ID的记录
    	if(!currUser.getIsSysAdmin()){
    		companyAccountWhere.setCustomerId(currUser.getCustomerId());
    	}
    	//设置过滤统计
    	companyAccountWhere.setIsCount(1);
    	List<CompanyAccountWhere> companyAccountWhereList = companyAccountWhereService.queryAccountWhereListPage(companyAccountWhere);
    	model.addAttribute("companyAccountWhereList", companyAccountWhereList);
    	model.addAttribute("page", companyAccountWhere);
    	model.addAttribute("tabType", tabType);
        return FUNDSWHEREABOUTS;
    }
    
    
    
    //资金去向页明细
    @RequestMapping(value = "/fundsWhereaboutsDetail")
    public String toViewFundsWhereaboutsDetail(HttpServletRequest request,Model model,CompanyAccountWhere companyAccountWhere) throws Exception{

    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	//如果不是超级管理员 则设置用户ID 在查询时只查询当前用户ID的记录
    	if(!currUser.getIsSysAdmin()){
			companyAccountWhere.setCustomerId(currUser.getCustomerId());
    	}
		companyAccountWhere.setTitle(null);
    	List<CompanyAccountWhere> companyAccountWhereList = companyAccountWhereService.queryAccountWhereListPage(companyAccountWhere);
    	model.addAttribute("companyAccountWhereList", companyAccountWhereList);
    	model.addAttribute("page", companyAccountWhere);
        return FUNDSWHEREABOUTSDETAIL;
    }
    
    
    //冻结余额
    @RequestMapping(value = "/frozenBalance")
    public String toViewFrozenBalance(HttpServletRequest request,Model model,
    		@RequestParam(value="title", required=false)String title, 
    		@RequestParam(value="currentPage", required=false)Integer currentPage,
    		@RequestParam(value="showCount", required=false)Integer showCount,
		    String tabType) throws Exception{
    	 
    	CompanyUser currUser = (CompanyUser) request.getSession().getAttribute(Const.SESSIONCODE);
    	
    	
    	CompanyTrainTask companyTrainTask = new CompanyTrainTask();
    	companyTrainTask.setTitle(title);
    	companyTrainTask.setCurrentPage(currentPage==null?1:currentPage);
    	companyTrainTask.setShowCount(showCount==null?5:showCount);
    	//如果不是超级管理员 则设置用户ID 在查询时只查询当前用户ID的记录
    	if(!currUser.getIsSysAdmin()){
    		companyTrainTask.setCreateId(currUser.getId());
    	}
    	
    	List<CompanyTrainTask> companyTrainTaskList = companyTrainTaskService.queryTask2ListPage(companyTrainTask);
 
    	
    	model.addAttribute("companyTrainTaskList", companyTrainTaskList);
    	model.addAttribute("tabType", tabType);
    	model.addAttribute("page", companyTrainTask);
        return FROZENBALANCE;
    }

	public static void main(String[] args) {
		System.out.println(new BigDecimal(70).compareTo(new BigDecimal(80)));
	}
}
