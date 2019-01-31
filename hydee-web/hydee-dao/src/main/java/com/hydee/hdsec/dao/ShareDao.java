package com.hydee.hdsec.dao;

import com.hydee.hdsec.entity.Share;
import com.hydee.hdsec.entity.ShareComment;
import com.hydee.hdsec.entity.ShareImg;
import com.hydee.hdsec.entity.ShareLiker;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShareDao {

	int insertShare(Share share);

	int saveShareImgs(List<ShareImg> shareImgs);

	List<Share> listAllSharesListPage(Share share);

	int insertComment(ShareComment comment);

	ShareLiker selectShareLiker(ShareLiker liker);

	int insertShareLiker(ShareLiker liker);

	int deleteShareLiker(ShareLiker liker);

	Share getFirstShare(Share share);
	
}