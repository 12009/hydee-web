package com.hydee.hdsec.common.exception.handler;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.hydee.hdsec.common.constant.ErrorCodes;
import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.util.Logger;

/**
 * 
 * 项目名称：hdsec 类名称：SystemExceptionHandler 类描述：统一异常处理 创建人：zz 创建时间：2014-12-30
 * 下午3:01:14 修改人：zz 修改时间：2014-12-30 下午3:01:14 修改备注：
 * 
 * @version Ver 1.1
 */
@SuppressWarnings("deprecation")
public class SystemExceptionHandler implements HandlerExceptionResolver {

	private void err(Throwable error) {
		if (error instanceof ServiceException
				|| error instanceof SocketException) {
			Logger.error(error.getMessage());
		} else {
			Logger.error("", error);
		}
	}

	@ResponseBody
	public ModelAndView resolveException(HttpServletRequest req, HttpServletResponse resp, Object obj, Exception ex) {
		ModelAndView modelView = new ModelAndView();
		AbstractView jsonView = new MappingJacksonJsonView();
		Map<String, Object> map = new HashMap<String, Object>();
		// 将错误输出到控制台和日志
		err(ex);
		if (ex instanceof ServiceException) {// 业务异常
			map.put("result", false);
			map.put("status", ((ServiceException) ex).getErrorCode());
			map.put("errors", ex.getMessage());
		} else if (ex instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) { // 文件过大异常
			map.put("result", false);
			map.put("status", ErrorCodes.FILE_FAIL_3.getCode());
			map.put("errors", ErrorCodes.FILE_FAIL_3.getMsg());
		} else if (ex instanceof NullPointerException) {
			map.put("result", false);
			map.put("status", ErrorCodes.DATA_NULL.getCode());
			map.put("errors", ErrorCodes.DATA_NULL.getMsg());
		} else {
			map.put("result", false);
			map.put("status", 500);
			map.put("errors", ex.getMessage());
		}
		jsonView.setAttributesMap(map);
		modelView.setView(jsonView);
		return modelView;
	}
}
