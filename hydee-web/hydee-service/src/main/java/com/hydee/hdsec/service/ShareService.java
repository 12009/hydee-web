package com.hydee.hdsec.service;

import com.hydee.hdsec.entity.Share;
import com.hydee.hdsec.entity.ShareComment;
import com.hydee.hdsec.entity.ShareImg;
import com.hydee.hdsec.entity.ShareLiker;

import java.util.List;

public interface ShareService {
	
	public int publish(Share share) throws Exception ;

	public int saveShareImgs(List<ShareImg> shareImgs) throws Exception ;

	public List<Share> listAllShares(Share share) throws Exception;

	public int comment(ShareComment comment) throws Exception;

	public int liked(ShareLiker liker) throws Exception;

	public Share getFirstShare(Share share) ;
}
