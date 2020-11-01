package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.yc.bean.MusicBean;
import com.yc.comms.DBHepler;
import com.yc.bean.MusicBean;
import com.yc.util.StringUtil;

public class MusicDAO implements BaseDAO<MusicBean>{

	DBHepler db=new DBHepler();

	@Override
	public int add(MusicBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MusicBean> findByTrem(MusicBean t) {
		
		//做sql语句拼接
		StringBuffer sb =new StringBuffer();
		sb.append("select * from music where 1=1 "); 
		List<Object> params = null;
		
		if( t != null ) {
			params = new ArrayList<Object>();
			
			if( !StringUtil.isNull(t.getMusicname())) {
				sb.append(" and musicname=?");
				params.add(t.getMusicname());
				
			}
		}
		
		//数据排序（升序）
		sb.append(" order by mid asc");
		// TODO Auto-generated method stub
		return db.findMutiple(sb.toString(), params, MusicBean.class);
		
	}
	
	/**
	 * 添加到我喜欢表
	 * @param uid
	 * @return
	 */
	public List<MusicBean> findByTrem(int uid) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from love,user,music where love.uid=`user`.uuid and love.mid=music.mid ");
		List<Object> params =  new ArrayList<Object>(); 
	
		sb.append(" and love.uid=?");
		params.add(uid);

		
		//数据排序（升序）
		sb.append(" order by love.id asc");
		// TODO Auto-generated method stub
		return db.findMutiple(sb.toString(), params, MusicBean.class);
	}

	@Override
	public int update(MusicBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(MusicBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
