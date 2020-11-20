package com.yc.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.bean.LoveBean;
import com.yc.bean.MusicBean;
import com.yc.comms.DBHepler;
import com.yc.util.StringUtil;

public class LoveDAO implements BaseDAO<LoveBean>{

	DBHepler db =new DBHepler();

	@Override
	public int add(LoveBean t) {
		
		String sql1 = "select * from love where mid=?";     //判断歌曲有没有被添加
		List<Object> params = new ArrayList<Object>();
		params.add(t.getMid());
		
	//	System.out.println("Mid="+t.getMid());
		
		List<LoveBean> list = db.findMutiple(sql1, params, LoveBean.class);
		
		if(list.size() == 0) {      //说明该歌曲没有被添加
		//	System.out.println("aaaa");
			String sql="insert into love values(0,?,?)";	
			return db.update(sql, t.getUid(),t.getMid());
		}
		
		System.out.println("bbb");
		
		return 0;
		
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
