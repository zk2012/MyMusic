package com.yc.biz;

import java.util.List;

import com.yc.bean.MessageBean;

public interface MessageDAOBiz {

	public int add(MessageBean t);
	
	public List<MessageBean> findByTrem(MessageBean t);
	
	public MessageBean getMid(int mid);
	
}
