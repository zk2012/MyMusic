package com.yc.bizImp;

import com.yc.bean.LoveBean;
import com.yc.biz.LoveDAOBiz;
import com.yc.comms.DBHepler;
import com.yc.dao.LoveDAO;

public class LoveDAOBizImp implements LoveDAOBiz{

	LoveDAO dao = new LoveDAO();
	
	@Override
	public int add(LoveBean t) {
		// TODO Auto-generated method stub
		return dao.add(t);
	}

	@Override
	public int delete(LoveBean t) {
		// TODO Auto-generated method stub
		return dao.delete(t);
	}

}
