package com.hydee.hdsec.service.impl;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.dao.ShareDao;
import com.hydee.hdsec.entity.Share;
import com.hydee.hdsec.entity.ShareComment;
import com.hydee.hdsec.entity.ShareImg;
import com.hydee.hdsec.entity.ShareLiker;
import com.hydee.hdsec.service.ShareService;
import com.hydee.hdsec.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {
	
	@Autowired
	private ShareDao shareDao;
	
	public int publish(Share share) throws Exception {
		if(StringUtil.isBlanks(false, share.getUserId(), share.getCustomerId(), share.getContent(), share.getSourceId(), share.getSourceType())){
			throw new ServiceException(ErrorCodes.TASK_INTERFACE_PARAMS);
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
