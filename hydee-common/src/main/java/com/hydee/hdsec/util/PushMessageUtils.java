package com.hydee.hdsec.util;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import net.sf.json.JSONObject;

import com.hydee.hdsec.entity.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PushMessageUtils {
	/*测试配置信息
	static String appId = "fm48d6o2TX5hkOPNnK4rE8";
	static String appkey = "IPRMIlFMUy5wpwbopqgqE8";
	static String master = "CI8d9QniVu7EdRXwuasZR2";
	static String CID = "2b554257936d0e71ff512cb66902bc5e";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	*/
	/*药店小蜜配置信息  两套配置信息*/
	static String appId_a = "5EGPsiOPxk9dZPfy7OcbH8";
	static String appkey_a = "VPXErNRgfx9oRBOxkQgUG6";
	static String master_a = "F9pzxvb7EM7PPK1bUVFk35";
	
	static String appId_b = "gN4fFXfPBU8EfQhIvjTi04";
	static String appkey_b = "SpYZ00Hdpe9ZjrgdAsaxG5";
	static String master_b = "DofMN0dzK19VWh82wUjjb3";
	
	static String CID = "c53960b32501332236e9422c8b6143aa";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	
	public static void pushToSingleAsyn(final String cid, final String content) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		threadPool.execute(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					pushToSingle(cid, content, appkey_a, appId_a, master_a);
					pushToSingle(cid, content, appkey_b, appId_b, master_b);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		threadPool.shutdown();
	}

	public static void pushToSingle(String cid, String content, String appkey, String appId, String master) throws Exception {
		IGtPush push = new IGtPush(host, appkey, master);
		TransmissionTemplate template = getTransmissionTemplate(content,appkey,appId);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选, (0- 72 小时内的正整数)
		message.setOfflineExpireTime(24 * 3600 * 1000); 
		message.setData(template);
		message.setPushNetWorkType(0); // 可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
		Target target = new Target();

		target.setAppId(appId);
		target.setClientId(cid);
		// 用户别名推送，cid和用户别名只能2者选其一
		// String alias = "个";
		// target.setAlias(alias);
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (RequestException e) {
			e.printStackTrace();
			ret = push.pushMessageToSingle(message, target, e.getRequestId());
		}
		if (ret != null) {
			System.out.println(ret.getResponse().toString());
		} else {
			System.out.println("服务器响应异常");
		}
	}
	
	public static void pushToListAsyn(final List<CID> cidList, final String content) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		threadPool.execute(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					pushToList(cidList, content,appkey_a,appId_a,master_a);
					pushToList(cidList, content,appkey_b,appId_b,master_b);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		threadPool.shutdown();
	}
	
	public static void pushToList(List<CID> cidList, String content,String appkey,String appId,String master) throws Exception {
		//配置返回每个用户返回用户状态，可选
		System.setProperty("gexin_pushList_needDetails", "true");
		// System.setProperty("gexin_pushList_needAsync", "true");
		final IGtPush push = new IGtPush(host, appkey, master);
		// LinkTemplate template = linkTemplateDemo();
        // TransmissionTemplate template = TransmissionTemplateDemo();
        // LinkTemplate template = linkTemplateDemo();
		// NotificationTemplate template = NotificationTemplateDemo();
		// NotyPopLoadTemplate template = NotyPopLoadTemplateDemo();
		TransmissionTemplate template = getTransmissionTemplate(content,appkey,appId);

		ListMessage message = new ListMessage();
		message.setData(template);
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选, (0- 72 小时内的正整数)
		message.setOfflineExpireTime(24 * 3600 * 1000);
		message.setPushNetWorkType(0); // 可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
		// 配置推送目标
		List<Target> targets = new ArrayList<Target>();
		for (CID cid : cidList) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(cid.getCid());
			targets.add(target);
		}
		//获取taskID
		String taskId = push.getContentId(message);
		//使用taskID对目标进行推送
		IPushResult ret = push.pushMessageToList(taskId, targets);
		//打印服务器返回信息
		System.out.println(ret.getResponse().toString());
	}
	
	public static TransmissionTemplate getTransmissionTemplate(String content,String appkey,String appId) throws Exception {
		TransmissionTemplate template = new TransmissionTemplate();
		template.setAppId(appId);
		template.setAppkey(appkey);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(2);
		template.setTransmissionContent(content);
		// template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        // template.setPushInfo("", 1, "", "", "", "", "", "");
		
		//************APN高级推送*******************//
		APNPayload apnpayload = new APNPayload();
		apnpayload.setBadge(1);
		apnpayload.setSound("default");
		apnpayload.setContentAvailable(1);
		apnpayload.setCategory("ACTIONABLE");
		JSONObject jObj = null;
		try{
			jObj = JSONObject.fromObject(content);
		}catch (Exception ex){
			jObj = JSONObject.fromObject("{\"code\":\"50003\",\"msg\":\"您有一条新的通知,请注意查收!\"}");
		}
		//简单模式APNPayload.SimpleMsg 
		apnpayload.setAlertMsg(new APNPayload.SimpleAlertMsg(jObj.getString("msg")));
	    //字典模式使用下者
	    //payload.setAlertMsg(getDictionaryAlertMsg());
	    template.setAPNInfo(apnpayload);
	    return template;
	}
	
	private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(){
	    APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
	    alertMsg.setBody("body");
	    alertMsg.setActionLocKey("ActionLockey");
	    alertMsg.setLocKey("LocKey");
	    alertMsg.addLocArg("loc-args");
	    alertMsg.setLaunchImage("launch-image");
	    // IOS8.2以上版本支持
	    alertMsg.setTitle("Title");
	    alertMsg.setTitleLocKey("TitleLocKey");
	    alertMsg.addTitleLocArg("TitleLocArg");
	    return alertMsg;
	}
	
	public static void main(String[] args) throws Exception {
		String content = "test by jerrysun";
		content = "{\"code\":\"50003\",\"msg\":\"您有一条新的通知,请注意查收!\"}";
//		pushToSingleAsyn(CID, content);
//		CID = "70fe51ed0d417d4c44b3420eb4909e0a"; //android - zengrong
//		pushToSingleAsyn(CID, content);
//		CID = "57ac9d4cade70cfaff35d4da67470965"; //IOS -luof
		CID = "3b3a5c6b84f8bd99d34454ab9d57379d"; //IOS -lucan
		pushToSingleAsyn(CID, content);
//		pushToListAsyn(Arrays.asList(new CID[]{new CID(cid)}), content);
	}
	
}
