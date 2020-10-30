package com.yc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.yc.bean.LoveBean;
import com.yc.bean.MusicBean;
import com.yc.comms.DBHepler;
import com.yc.dao.LoveDAO;
import com.yc.dao.MusicDAO;

class LoveDAOTest {

	LoveBean lb =new LoveBean();
	LoveDAO dao = new LoveDAO();
	
	@Test
	void findtest() {
		lb.setUid(25);
		
		System.out.println(dao.findByTrem(lb));

	}

}
