package com.yc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.yc.bean.LoveBean;
import com.yc.bean.MusicBean;
import com.yc.comms.DBHepler;
import com.yc.dao.LoveDAO;
import com.yc.dao.MusicDAO;

class LoveDAOTest {

	
	LoveDAO dao = new LoveDAO();
	
	@Test
	void insert() {
		LoveBean lb = new LoveBean();
		lb.setUid(25);
		lb.setMid(1);
		
		System.out.println(dao.add(lb));

	}
	
	@Test
	void delete() {
		LoveBean lb = new LoveBean();
		
		lb.setMid(1);
		
		System.out.println(dao.delete(lb));

	}

}
