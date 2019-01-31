package com.hydee.hdsec.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

import com.hydee.hdsec.common.exception.ServiceException;
import com.hydee.hdsec.entity.CompanyMenu;
import com.hydee.hdsec.entity.CompanyUser;
import com.hydee.hdsec.util.Const;
import com.hydee.hdsec.util.StringUtil;

/**
 * Created by King.DD on 2016/8/16.
 */
public class LoginFilter implements Filter {
    private Pattern urlPattern;
    private Pattern viewPattern;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String regStr = filterConfig.getInitParameter("verifyFilterStr").replaceAll(",", "|").replaceAll("[\\*]+", "[\\\\S]+");
        this.urlPattern = Pattern.compile(regStr);
        this.viewPattern = Pattern.compile("/view/\\S+");
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();

        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        CompanyUser userId = (CompanyUser) session.getAttribute(Const.SESSIONCODE);
        List<CompanyMenu> menues = (List<CompanyMenu>) session.getAttribute(Const.SESSION_MENUES);

        /*无需过滤的页面*/
        Matcher _mat = urlPattern.matcher(path);
        if(_mat.find()){
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        if ( null == userId || "".equals(userId)) {
        	if(servletRequest.getMethod().toUpperCase().equals("POST")){
        		// 返回错误信息JSON对象
    			servletResponse.setHeader("Content-type", "text/html;charset=UTF-8");  
    			servletResponse.setContentType("application/json; charset=UTF-8");
    			servletResponse.setCharacterEncoding("UTF-8");  
    			PrintWriter pw = servletResponse.getWriter();  
    			pw.write("{\"result\":false,\"status\":100,\"errors\":\"登录超时，请重新登录!\"}");  
    			pw.flush();
    			pw.close();
        	}else{
        		// 跳转到登陆页面
        		servletResponse.sendRedirect(servletRequest.getContextPath()+"/login/goLogin");
        	}
        } else {
        	if(viewPattern.matcher(path).find()){
        		// 已经登陆,校验菜单权限
        		String __regStr = "";
        		for(CompanyMenu menu : menues){
        			if(!StringUtil.isBlank(menu.getMenuUrl())){
        				__regStr += menu.getMenuUrl() + "\\S*|";
        			}
        		}
        		__regStr = __regStr.substring(0,__regStr.length()-1);
        		Pattern __urlPattern = Pattern.compile(__regStr);
        		if(!__urlPattern.matcher(path).find()){
        			// 返回错误信息JSON对象
        			servletResponse.setHeader("Content-type", "text/html;charset=UTF-8");  
        			servletResponse.setContentType("application/json; charset=UTF-8");
        			servletResponse.setCharacterEncoding("UTF-8");  
        			PrintWriter pw = servletResponse.getWriter();  
        			pw.write("{\"result\":false,\"status\":109,\"errors\":\"权限校验失败\"}");  
        			pw.flush();
        			pw.close();
                    return;
        		}
        	}
            chain.doFilter(request, response);
        }
    }
    
    @Override
    public void destroy() {
    }
}
