package com.yc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.yc.bean.MessageBean;
import com.yc.dao.MessageDAO;

class MessageDAOTest {

	MessageDAO dao = new MessageDAO();
	
	@Test
	void test() {
		
		MessageBean mb = new MessageBean();
		
		mb.setMid(2);
		
		System.out.println(dao.findByTrem(mb));
		
	}
	
	@Test
	void test1() {
		
		MessageBean mb = new MessageBean();
		
		mb.setMid(2);
		mb.setUid(2);
		mb.setMessage("我很喜欢这首歌!!!");
		
		System.out.println(dao.add(mb));
		
	}
	

}
