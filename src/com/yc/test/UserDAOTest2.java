package com.yc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.yc.bean.UserBean;
import com.yc.dao.UserDAO;

class UserDAOTest2 {

	UserDAO ud = new UserDAO();
	
	@Test
	void addtest() {
     
		UserBean us =new UserBean();
		
        us.setUname("aaab");
        us.setUpwd("123321");
        us.setUphoto("../uphoto/2.jpg");
        System.out.println(ud.add(us));
		
      }
	
	@Test
	void findtest() {
		System.out.println(ud.login("yc", "123321"));
		
      }

}