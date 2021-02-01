package com.yc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.MessageBean;
import com.yc.biz.MessageDAOBiz;
import com.yc.bizImp.MessageDAOBizImp;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/MessageServlet")
public class MessageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    
	MessageDAOBiz mdb = new MessageDAOBizImp();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String op=request.getParameter("op");
//		String id = request.getParameter("nowId");
//		System.out.println(id);
		
		if(op.equals("find")) {  // 查找并显示所有音乐id
			find(request,response);
		}else if(op.equals("check")) { // 检查音乐id是否被存入session中
			check(request,response);
		}else if(op.equals("getMid")) {  // 获取音乐id
			getMid(request,response);
		}
		
	}

	
	
	/**
	 * 获取音乐的mid
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getMid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String mid=request.getParameter("mid");
		
		// 创建一个cookies对象
		Cookie cookies = new Cookie("mid",mid);
		
		cookies.setMaxAge(1000*60);  // 设置时长为100分钟后cookie值自动消失
		
		// 强转为整形
		int mid1=Integer.parseInt(mid);
		
		MessageBean mb = mdb.getMid(mid1);
		
		if(mb != null) {
			
			String cookieName = cookies.getName();
			
			if(cookieName.equals("mid")) {
				this.send(response, 200);
				return;
			}
		}
		
		this.send(response, 101);
		
	}


	/**
	 * 检查是哪首音乐的mid
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          Object obj = request.getCookies();
          
          if( obj == null ) {
        	  this.send(response, 100,"未赋音乐id",null);
        	  return;
          }
          
          this.send(response, 200,"已赋音乐id",obj);
		
	}

	/**
	 * 查询显示所有消息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int mid=Integer.parseInt(request.getParameter("mid"));
		
		MessageBean mb = new MessageBean();
		
		mb.setMid(mid);
		
		this.send(response, mdb.findByTrem(mb)); 
		
		
	}

}
