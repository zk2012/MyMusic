package com.yc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.LoveBean;
import com.yc.biz.LoveDAOBiz;
import com.yc.bizImp.LoveDAOBizImp;

import javafx.scene.Parent;

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
			add(request,response);
		}
		
		
	}

	
	/**
	 * 添加我喜欢音乐
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int mid = Integer.parseInt(request.getParameter("mid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		LoveBean lb =new LoveBean();
		
		lb.setUid(uid);
		lb.setMid(mid);
		
		this.send(response, lbiz.add(lb));
		
		// TODO Auto-generated method stub
		
	}

}
