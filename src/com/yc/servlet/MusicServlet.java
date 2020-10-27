package com.yc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bean.MusicBean;
import com.yc.biz.MusicDAOBiz;
import com.yc.bizImp.MuiscDAOBizImp;

/**
 * Servlet implementation class MusicServlet
 */
@WebServlet("/MusicServlet")
public class MusicServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    MusicDAOBiz mbiz =new MuiscDAOBizImp();


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op=request.getParameter("op");
		
		if(op.equals("finds")) {
			finds(request,response);
		}
		
		
		
		// TODO Auto-generated method stub
		//doGet(request, response);
	}




	/**
	 * 显示所有歌单中的歌曲
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void finds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.send(response, mbiz.findByTrem(null));
		
	}

}
