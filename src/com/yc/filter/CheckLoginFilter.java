package com.yc.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
@WebFilter(filterName="CheckLoginFilter",value="/back/manager/*",initParams=@WebInitParam(name="errorPage", value = ""))
public class CheckLoginFilter implements Filter {

	private String encoding="utf-8";

	public void destroy() {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest res=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		res.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		//链式调用下一个过滤器
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		String temp=fConfig.getInitParameter("encoding");
		if(temp!=null) {
			encoding=temp;
		}

	}
}
