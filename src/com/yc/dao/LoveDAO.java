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
		
		String sql="insert into values(0,?,?)";
		
		return db.update(sql,t.getUid(),t.getMid());
	}

	@Override
	public List<LoveBean> findByTrem(LoveBean t) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from love,user,music where love.uid=`user`.uuid and love.mid=music.mid ");
		List<Object> params =  new ArrayList<Object>(); 
	
			if( t.getUid() != 0) {
				sb.append(" and love.uid=?");
				params.add(t.getUid());
				
			}
		
		//数据排序（升序）
		sb.append(" order by love.mid asc");
		// TODO Auto-generated method stub
		return db.findMutiple(sb.toString(), params, LoveBean.class);
	}

	@Override
	public int update(LoveBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(LoveBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}
