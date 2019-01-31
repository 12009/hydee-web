package com.hydee.hdsec.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hydee.hdsec.util.QiniuUtils;
import com.hydee.hdsec.vo.JsonVo;

@Controller
@RequestMapping("/qiniu")
public class QiniuController {
	
	/**
	 * 更新七牛云token
	 * @throws IOException 
	 */
	@RequestMapping("/uptoken")
	@ResponseBody
	public void uptoken(HttpServletResponse response) throws Exception{
		// 设置response返回消息头
		response.setContentType("application/json; charset=UTF-8");
		//设置servlet用UTF-8转码 
		response.setCharacterEncoding("UTF-8");  
		String uptoken = QiniuUtils.getUpToken();
		PrintWriter pw = response.getWriter();  
		pw.write("{\"uptoken\":\""+uptoken+"\"}");  
		pw.flush();
		pw.close();
	}
	
	@RequestMapping("/delFile")
	@ResponseBody
	public Object delFile(String fileLink) throws Exception{
		JsonVo<String> jsonObj = new JsonVo<String>();
		QiniuUtils.delete(fileLink);
		jsonObj.setData("删除成功!");
		return jsonObj;
	}
}
