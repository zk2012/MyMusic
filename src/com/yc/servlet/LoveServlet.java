package com.yc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.biz.LoveDAOBiz;
import com.yc.bizImp.LoveDAOBizImp;

/**
 * Servlet implementation class LoveServlet
 */
@WebServlet("/LoveServlet")
public class LoveServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
   
	LoveDAOBiz lbiz = new LoveDAOBizImp();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op=request.getParameter("op");
		
		if(op.equals("add")) {
			
		}
		
		
	}

}
