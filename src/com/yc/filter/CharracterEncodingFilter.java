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
 *    编码集过滤器（防止乱码）
 * @author Administrator
 *
 */
@WebFilter(filterName="CharracterEncodingFilter",value="/*",
            initParams=@WebInitParam(name = "encoding", value = "utf-8"))
public class CharracterEncodingFilter implements Filter {

    private String encoding="utf-8";
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest res=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		res.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		String temp=fConfig.getInitParameter("encoding");
		if(temp!=null) {
			encoding = temp;
		}
		
		// TODO Auto-generated method stub
	}

}
