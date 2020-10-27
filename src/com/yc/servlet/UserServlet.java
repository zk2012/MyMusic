package com.yc.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.yc.bean.UserBean;
import com.yc.biz.UserDAOBiz;
import com.yc.bizImp.UserDAOBizImp;
import com.yc.util.FileUploadUtil;;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    UserDAOBiz ubiz = new UserDAOBizImp();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op=request.getParameter("op");
		
		if("login".equals(op)) {       //用户登录
			login(request,response);
		}else if("check".equals(op)) {   //主界面检查是否登录
			check(request,response);
		}else if("register".equals(op)) {  //创建用户
			register(request,response);
		}
		
		
		
//		// TODO Auto-generated method stub
//		doGet(request, response);
	}



	/**
	 * 创建用户
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws Exception 
	 */
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		FileUploadUtil uploadUtil = new FileUploadUtil();
		//使用SmartUpload上传文件。因为原来其使用JSP进行文件上传，而JSP自带PageContext对象。但是现在我们是在Servlet
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		
		int result = 0;
		
		try {
			Map<String,String> map = uploadUtil.uploads(pageContext); //得到商品信息
			//添加商品
			System.out.println(map);
			
			result = ubiz.add(map);
			System.out.println(result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		// TODO Auto-generated method stub
		this.send(response, result);
		// TODO Auto-generated method stub
		
	}



	/**
	 * 检查是否登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取登录用户的信息
				Object obj = request.getSession().getAttribute("currentLoginMember");
				//判断是否存储
				if( obj == null ) {  //未登录
					this.send(response, 100,"未登录",null);
					return;
				}
				this.send(response, 200,"验证成功",obj);

		
		// TODO Auto-generated method stub
		
	}



	/**
	 * 用户登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		
		UserBean ub = ubiz.login(uname, upwd);
		
		if(ub != null) {
			request.getSession().setAttribute("currentLoginMember",ub);
			this.send(response, 200);
			return;
		}
			
		this.send(response, 101);
		// TODO Auto-generated method stub
		
	}

}
