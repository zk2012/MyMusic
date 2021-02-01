package com.yc.dao;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Message;
import com.yc.bean.MessageBean;
import com.yc.bean.UserBean;
import com.yc.comms.DBHepler;

public class MessageDAO implements BaseDAO<MessageBean>{

	DBHepler db = new DBHepler();
	
	@Override
	public int add(MessageBean t) {
		
		String sql="INSERT into message VALUES(0,?,?,?,NOW())";
		
		
		return db.update(sql, t.getUid(),t.getMid(),t.getMessage());
	}

	@Override
	public List<MessageBean> findByTrem(MessageBean t) {

		String sql="select uid,mid,message,date_format(time, '%Y-%m-%d %H:%i')time,uname,uphoto from message,`user` where message.uid=`user`.uuid and mid=?";
		
		List<Object> params = new ArrayList<Object>();
		
		params.add(t.getMid());

		return db.findMutiple(sql, params,MessageBean.class);
	}
	
	public MessageBean getMid(int mid) {
		
		String sql="select uid,mid,message,date_format(time, '%Y-%m-%d %H:%i')time,uname,uphoto from message,`user` where message.uid=`user`.uuid and mid=?";
		List<Object> params = new ArrayList<Object>();
		
		params.add(mid);
		List<MessageBean> list = db.findMutiple(sql, params, MessageBean.class);
		
		if(null != list && list.size() > 0) {
			return list.get(0);
		}
        
		
		return null;
		
	}

	@Override
	public int update(MessageBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(MessageBean t) {
		// TODO Auto-generated method stub
		return 0;
	}



	
}
