package com.yc.bizImp;

import java.util.Map;

import com.yc.bean.UserBean;
import com.yc.biz.UserDAOBiz;
import com.yc.dao.UserDAO;
import com.yc.util.StringUtil;

public class UserDAOBizImp implements UserDAOBiz{

	UserDAO dao = new UserDAO();
	
	@Override
	public int add(Map<String, String> map) {
		
		UserBean bean=new UserBean(0,map.get("uname"),map.get("upwd"),map.get("uphoto"), 0);
		// TODO Auto-generated method stub
		return dao.add(bean);
	}

	@Override
	public UserBean login(String uname, String upwd) {
		
		if(!StringUtil.isNull(uname,upwd)) {
			return dao.login(uname, upwd);
		}
		
		// TODO Auto-generated method stub
		return null;
		
	}

}
