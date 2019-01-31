package com.hydee.hdsec.common.constant;

import com.hydee.hdsec.util.LimitUtil;

/**
 * 
 * 类名称：ErrorCodes
 * 类描述：错误代码常量
 */
public class ErrorCodes {
	private static final int HYDEE_SYS = 100;
	private static final int HYDEE_API = 1000;
	
	public static final ErrorCode DATA_NULL = new ErrorCode(HYDEE_SYS + 1, "查询数据为空");
	
	public static final ErrorCode FILE_FAIL_1 = new ErrorCode(HYDEE_SYS + 2, "上传文件为空");
	
	public static final ErrorCode FILE_FAIL_2 = new ErrorCode(HYDEE_SYS + 3, "上传文件类型不符");
	
	public static final ErrorCode FILE_FAIL_3 = new ErrorCode(HYDEE_SYS + 4, "上传文件容量过大");
	
	public static final ErrorCode TASK_LOCK_MONEY = new ErrorCode(HYDEE_SYS + 5, "锁定余额失败！");
	
	public static final ErrorCode USERNAME_HAS_REGISTED = new ErrorCode(HYDEE_API + 1, "用户名已经在系统存在，请重新输入");
	
	public static final ErrorCode EMAIL_USERNAME_NOT_MATCH = new ErrorCode(HYDEE_API + 2, "用户名与邮箱不匹配，请重新确认");
	
	public static final ErrorCode EMAIL_USERNAME_NOT_NULL = new ErrorCode(HYDEE_API + 3, "用户名与邮箱不能为空");
	
	public static final ErrorCode ROLENAME_HAS_REPEATED = new ErrorCode(HYDEE_API + 4, "角色名已被使用");
	
	public static final ErrorCode ORG_NAME_HAS_REPEAT = new ErrorCode(HYDEE_API + 5, "该机构名称已被管理员注册使用");
	
	public static final ErrorCode ROLE_HAS_USEFUL = new ErrorCode(HYDEE_API + 6, "当前角色正在被用户使用");

	public static final ErrorCode TASK_PARAMS = new ErrorCode(HYDEE_API + 7, "任务对应课件，指定连锁不能为空");

	public static final ErrorCode TASK_ID_NOT_NULL = new ErrorCode(HYDEE_API + 8, "任务编号不能为空");

	public static final ErrorCode TASK_STATUS = new ErrorCode(HYDEE_API + 9, "进行中或结束的任务不可删除");

	public static final ErrorCode TASK_NOT_EXIST = new ErrorCode(HYDEE_API + 10, "任务不存在");

	public static final ErrorCode CLASS_NOT_EXERCISES = new ErrorCode(HYDEE_API + 11, "习题不能为空");

	public static final ErrorCode CLASS_EXERCISES_SUM = new ErrorCode(HYDEE_API + 12, "习题个数不能超过"+ LimitUtil.exercisesLimitCount+"个");

	public static final ErrorCode TASK_INTERFACE_PARAMS = new ErrorCode(HYDEE_API + 13, "参数不完整！");

	public static final ErrorCode TASK_UPDATE = new ErrorCode(HYDEE_API + 15, "修改任务！");

	public static final ErrorCode TASK_NOT_DOING = new ErrorCode(HYDEE_API + 16, "任务没有开启！");

	public static final ErrorCode TASK_NOW_OPEN = new ErrorCode(HYDEE_API + 17, "该课件有关联的任务！");

	public static final ErrorCode TASK_USER_EXIST = new ErrorCode(HYDEE_API + 18, "已经参加过答题！");

	public static final ErrorCode TASK_LOCK_TIME_OUT = new ErrorCode(HYDEE_API + 19, "时间超过"+LimitUtil.TASKLOCKTIME+"小时");

	public static final ErrorCode TASK_RESIDUE_MONEY_NOT = new ErrorCode(HYDEE_API + 20, "剩余余额被锁定，请稍后再来！");
	
	public static final ErrorCode UID_CID_IS_NULL = new ErrorCode(HYDEE_API + 21, "参数 userId, customerId 不能为空");
	
	public static final ErrorCode UID_CHCID_IS_NULL = new ErrorCode(HYDEE_API + 22, "参数 userId, chainCustomerId 不能为空");
	
	public static final ErrorCode TASK_IS_LIKE = new ErrorCode(HYDEE_API + 23, "不能连续点赞！");
	
	public static final ErrorCode BIND_BANK_CARD_ERROR = new ErrorCode(HYDEE_API + 24, "参数 userId, customerId, bankCard, bankName, ownerName 不能为空");
	
	public static final ErrorCode WITHDRAW_FIALED = new ErrorCode(HYDEE_API + 25, "参数 userId, customerId, bindBankId, userName, customerName 不能为空");
	
	public static final ErrorCode WITHDRAW_FIALED_2 = new ErrorCode(HYDEE_API + 26, "提现金额必须大于100");
	
	public static final ErrorCode TASK_IS_OVER = new ErrorCode(HYDEE_API + 27, "任务已结束！");

	public static final ErrorCode BANK_CARD_IS_BIND = new ErrorCode(HYDEE_API + 28, "该用户已绑定过此卡");

	public static final ErrorCode WITHDRAW_FIALED_3 = new ErrorCode(HYDEE_API + 29, "抱歉,小蜜提现日是每周二,且每周仅限提现一次!");

	public static final ErrorCode CHCID_IS_NULL = new ErrorCode(HYDEE_API + 30, "参数 chainCustomerId 不能为空");

	public static final ErrorCode UNBIND_BANK_CARD_ERROR = new ErrorCode(HYDEE_API + 31, "待解绑的银行卡ID不能为空");

	public static final ErrorCode WITHDRAW_FIALED_4 = new ErrorCode(HYDEE_API + 32, "未查询到此bindBankId所对应的银行卡信息");

	public static final ErrorCode CLICK_LIKE_NUM = new ErrorCode(HYDEE_API + 33, "点赞新增积分失败！");

	public static final ErrorCode ISSUE_TASK_ERROR = new ErrorCode(HYDEE_API + 34, "发布任务通知管理员失败！");


}
