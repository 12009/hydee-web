package com.hydee.hydee.service.impl;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.ShareDao;
import com.hydee.hdsec.entity.*;
import com.hydee.hdsec.sqlDao.MembershipSysDao;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.HttpUtil;
import com.hydee.hydee.service.ShareService;
import com.hydee.hdsec.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShareServiceImpl implements ShareService {
	@Autowired
	private ShareDao shareDao;
	@Autowired
	private MembershipSysDao membershipSysDao;
	
	public int publish(Share share,boolean flag) throws Exception {
		if(StringUtil.isBlanks(false, share.getUserId(), share.getCustomerId(), share.getContent(), share.getSourceId(), share.getSourceType())){
			throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
		}
		if(flag){
			ExpVal expVal=new ExpVal();
			expVal.setCustomerId(share.getCustomerId());
			expVal.setUserId(share.getUserId());
			expVal.setType(Const.COMPANY_POINTSVAL_COMMENT);
			int submitInt2 = membershipSysDao.setPoints(expVal);
//			if(submitInt2 <= 0){
//				throw new ServiceException(ErrorCodes.ADD_EXP_VAL_ERROR);
//			}
		}
		return shareDao.insertShare(share);
	}

	public int saveShareImgs(List<ShareImg> shareImgs) throws Exception {
		return shareDao.saveShareImgs(shareImgs);
	}

	public List<Share> listAllShares(Share share) throws Exception {
		if(StringUtil.isBlanks(false, share.getUserId(), share.getCustomerId(), share.getSourceId(), share.getSourceType())){
			throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
		}
		return shareDao.listAllSharesListPage(share);
	}

	@Override
	public List<Share> showSharesAll(Share share) throws Exception {
		if(StringUtil.isBlanks(false, share.getSourceId())){
			throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
		}
		return shareDao.listAllSharesListPage(share);
	}

	public int comment(ShareComment comment) throws Exception {
		if(StringUtil.isBlanks(false, comment.getShareId(), comment.getObserverId())){
			throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
		}
		return shareDao.insertComment(comment);
	}

	public int liked(ShareLiker liker) throws Exception {
		if(StringUtil.isBlanks(false, liker.getShareId(), liker.getUserId(), liker.getCustomerId())){
			throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
		}
		ShareLiker _liker = shareDao.selectShareLiker(liker);
		int count = 0;
		if(_liker == null){
			count = shareDao.insertShareLiker(liker);
		}else{
			count = shareDao.deleteShareLiker(liker);
			count = count < 1 ? 0 : 2;
		}
		return count;
	}

	public Share getFirstShare(Share share) {
		return shareDao.getFirstShare(share);
	}

}
