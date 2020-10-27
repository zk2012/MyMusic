package com.yc.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.yc.bean.MusicBean;
import com.yc.dao.MusicDAO;

class MusicDAOTest {

	MusicBean mb =new MusicBean();
	MusicDAO dao = new MusicDAO();
	
	@Test
	void findtest() {
		System.out.println(dao.findByTrem(mb));
		System.out.println("aa");
	}

}
