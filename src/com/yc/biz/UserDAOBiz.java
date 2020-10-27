package com.yc.biz;

import java.util.Map;

import com.yc.bean.UserBean;

public interface UserDAOBiz {
     
	public int add(Map<String, String> map);
	
	public UserBean login(String uname,String upwd);

}
