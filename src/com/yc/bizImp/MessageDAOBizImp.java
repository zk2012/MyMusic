package com.yc.bizImp;

import java.util.List;

import com.yc.bean.MessageBean;
import com.yc.biz.MessageDAOBiz;
import com.yc.dao.MessageDAO;

public class MessageDAOBizImp implements MessageDAOBiz{

	MessageDAO dao = new MessageDAO();
	
	@Override
	public int add(MessageBean t) {
		// TODO Auto-generated method stub
		return dao.add(t);
	}

	@Override
	public List<MessageBean> findByTrem(MessageBean t) {
		// TODO Auto-generated method stub
		return dao.findByTrem(t);
	}

	@Override
	public MessageBean getMid(int mid) {
		
		if(mid != 0) {
			return dao.getMid(mid);
		}
		
		return null;
	}

}
