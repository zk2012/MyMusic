package com.yc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yc.util.JsonModel;

/**
 * 自定义的基础Servlet
 * @author Administrator
 *
 */
public class BaseServlet extends HttpServlet{

	
	private static final long serivalVersionUID=-4185946525105531064L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req, resp);
	}



	
	//响应int   处理跨域的报错操作
	protected void send(HttpServletResponse response,int result) throws ServletException,IOException{
		
		//Access-Control-Allow-Origin   解决跨域问题
		response.setHeader("Access-Control-Allow-Origin", "*");
        
		//解决前端网页   xml解析错误 ： 格式不佳问题
		response.setContentType("text/plain");
		
		PrintWriter out =response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	
	//响应String   处理跨域的报错操作
		protected void send(HttpServletResponse response,String result) throws ServletException,IOException{
			
			//Access-Control-Allow-Origin   解决跨域问题
			response.setHeader("Access-Control-Allow-Origin", "*");
	        
			//解决前端网页   xml解析错误 ： 格式不佳问题
			response.setContentType("text/plain");
			
			PrintWriter out =response.getWriter();
			out.print(result);
			out.flush();
			out.close();
		}
		
		
		//响应Object   处理跨域的报错操作
		protected void send(HttpServletResponse response,Object obj) throws ServletException,IOException{
			
			//Access-Control-Allow-Origin   解决跨域问题
			response.setHeader("Access-Control-Allow-Origin", "*");
	        
			//解决前端网页   xml解析错误 ： 格式不佳问题
			response.setContentType("application/json");
			
			PrintWriter out =response.getWriter();
			Gson gson=new GsonBuilder().serializeNulls().create();
			out.print(gson.toJson(obj));
			out.flush();
			out.close();
		}
		
		//响应JsonModel   处理跨域的报错操作                 状态码           错误码                     数据
		protected void send(HttpServletResponse response,Integer code,String errorMsg,Object obj) throws ServletException,IOException{
			
			//Access-Control-Allow-Origin   解决跨域问题
			response.setHeader("Access-Control-Allow-Origin", "*");
	        
			//解决前端网页   xml解析错误 ： 格式不佳问题
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
            JsonModel jm =new JsonModel();
			jm.setCode(code);
			jm.setErrorMsg(errorMsg);
			jm.setObj(obj);
			Gson gson=new GsonBuilder().serializeNulls().create();
			out.print(gson.toJson(jm));
			out.flush();
			out.close();		
			}

}
