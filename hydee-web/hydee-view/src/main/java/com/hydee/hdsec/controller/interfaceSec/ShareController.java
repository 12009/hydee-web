package com.hydee.hdsec.controller.interfaceSec;

import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.Share;
import com.hydee.hdsec.entity.ShareComment;
import com.hydee.hdsec.entity.ShareLiker;
import com.hydee.hdsec.entity.utils.DateUtil;
import com.hydee.hdsec.service.ShareService;
import com.hydee.hdsec.util.HttpUtil;
import com.hydee.hdsec.util.StringUtil;
import com.hydee.hdsec.vo.JsonVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/share")
public class ShareController {
	private static final String ADD_COMMENT_POTIONS = "messageCompany/commentAddPoints";
	@Autowired
	private ShareService shareService;
	
	/**
	 * 发布分享
	 * @param share
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/publish")
	@ResponseBody
	public Object publish(Share share, HttpServletRequest request)throws Exception{
		JsonVo<String> result = new JsonVo<String>();
		Share firstShare = shareService.getFirstShare(share);
		String data = "";
		if(null == firstShare){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("customerId",share.getCustomerId());
			params.put("userId",share.getUserId());
			String flag = HttpUtil.getServiceName(ADD_COMMENT_POTIONS,params);
			JSONObject resultObj = JSONObject.fromObject(flag);
			boolean res = false;
			try {
				res = (Boolean) resultObj.get("result");
			} catch (Exception e) {
				result.setResult(false);
				result.setData("新增积分失败！");
				return result;
			}
			if(!res){
				result.setResult(false);
				result.setData("新增积分失败！");
				return result;
			}
			data = (String) resultObj.get("data");

		}
		result.setData(data);
		int addCount = shareService.publish(share);
		if(addCount <= 0){
			result.setResult(false);
			result.setData("服务器繁忙,稍后请重试!");
		}
		return result;
	}
	/**
	 * 查询所有可见的分享列表
	 * @param share
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping("/listShare")
	@ResponseBody
	public Object listShare(Share share,HttpServletRequest request) throws Exception {
		JsonVo<List<Share>> result = new JsonVo<List<Share>>();
		List<Share> reList = shareService.listAllShares(share);
		for(Share _share : reList){
			for(ShareLiker _liker : _share.getLikers()){
				if(_liker.equals(share.getUserId())){
					_share.setLiked(true);
					break;
				}
			};
		}
		result.setData(reList);
		return result;
	}
	/**
	 * 发表评论或对评论进行回复
	 * @param comment
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping("/comment")
	@ResponseBody
	public Object comment(ShareComment comment,HttpServletRequest request) throws Exception{
		JsonVo<ShareComment> result = new JsonVo<ShareComment>();
		int addCount = shareService.comment(comment);
		if(addCount > 0){
			comment.setPublishTime(DateUtil.format(new Date()));
			comment.setContent(StringUtil.unicode2String(comment.getContent()));
			result.setData(comment);
		}else{
			result.setResult(false);
			result.setData(null);
		}
		return result;
	}
	/**
	 * 用户点赞/取消点赞
	 * @param liker
	 * @param request
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping("/liked")
	@ResponseBody
	public Object liked(ShareLiker liker,HttpServletRequest request) throws Exception{
		JsonVo<String> result = new JsonVo<String>();
		int addCount = shareService.liked(liker);
		if(addCount > 0){
			// "1":点赞;"2":取消点赞
			result.setData(String.valueOf(addCount));
		}else{
			result.setResult(false);
			result.setData("服务器繁忙,稍后请重试!");
		}
		return result;
	}
}
