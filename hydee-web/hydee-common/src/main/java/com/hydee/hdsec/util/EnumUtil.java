package com.hydee.hdsec.util;

/**
 * Created by Administrator on 2016/7/15.
 */
public class EnumUtil {
    public enum LoginStatus{
        STATUS_USERFUL(1),STATUS_UNUSERFUL(2),STATUS_AUTHSTR(3),STATUS_AUTHFAILD(4);
        public Integer value;
        LoginStatus(Integer arg){
            this.value=arg;
        }
    }
    
    public enum WithdrawalStatus{
    	//待审核、审核失败、审核成功
    	STATUS_AUTHSTR(0),STATUS_AUTHFAILD(1),STATUS_SUCCESSFUL(2);
        public Integer value;
        WithdrawalStatus(Integer arg){
            this.value=arg;
        }
    }

    public enum Dict{
        //连锁企业培训基金、平台服务费、培训奖金、提取到可用余额、追加培训奖金
        TRAIN_FUND(1L),SERVE_CHARGE(2L),RED_MONEY(3L),USABLE_MONEY(4L),ADD_MONEY(5L);
        public Long value;
        Dict(Long arg){
            this.value=arg;
        }
    }
}
