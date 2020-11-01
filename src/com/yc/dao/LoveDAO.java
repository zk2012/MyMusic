package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.LoveBean;
import com.yc.bean.MusicBean;
import com.yc.comms.DBHepler;
import com.yc.util.StringUtil;

public class LoveDAO implements BaseDAO<LoveBean>{

	DBHepler db =new DBHepler();

	@Override
	public int add(LoveBean t) {
		
		String sql="insert into love values(0,?,?)";
		
		return db.update(sql, t.getUid(),t.getMid());
	}

	@Override
	public List<LoveBean> findByTrem(LoveBean t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(LoveBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(LoveBean t) {
		
		String sql="DELETE from love where mid=?";
		
		return db.update(sql, t.getMid());
	}
	
	

	
	
	
}
