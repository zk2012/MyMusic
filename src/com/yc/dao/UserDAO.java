package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.UserBean;
import com.yc.comms.DBHepler;
import com.yc.util.StringUtil;

public class UserDAO implements BaseDAO<UserBean>{
   
	DBHepler db = new DBHepler();
	
	/**
	 * 创建用户
	 */
	@Override
	public int add(UserBean t) {
		
		String sql1 = "select * from user where uname=?";   // 判断该用户名是否被注册
		List<Object> params = new ArrayList<Object>();
		
		params.add(t.getUname());
		
		List<UserBean> list = db.findMutiple(sql1, params, UserBean.class);
		
		if(list.size() == 0) {
			String sql ="insert into user values(0,?,?,?,0)";
			// TODO Auto-generated method stub
			return db.update(sql, t.getUname(),t.getUpwd(),t.getUphoto());
		}
		
		return 0;
	}

	@Override
	public List<UserBean> findByTrem(UserBean t) {
	
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用户登录查询
	 * @param uname
	 * @param upwd
	 * @return
	 */
	public UserBean login(String uname,String upwd) {
		
		
		String sql="select * from user where uname=? and upwd=?";
		List<Object> params= new ArrayList<Object>();
		
		params.add(uname); //用户名
		params.add(upwd);  //密码
		
        List<UserBean> list = db.findMutiple(sql, params, UserBean.class);
		
	//	System.out.println(list);
		
		if(null != list && list.size() > 0) {
			return list.get(0);
		}
        
		return null;
		
	}
	
	
	
	@Override
	public int update(UserBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(UserBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
