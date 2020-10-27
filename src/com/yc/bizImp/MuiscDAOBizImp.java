package com.yc.bizImp;

import java.util.List;

import com.yc.bean.MusicBean;
import com.yc.biz.MusicDAOBiz;
import com.yc.dao.MusicDAO;

public class MuiscDAOBizImp implements MusicDAOBiz{

	MusicDAO dao= new MusicDAO();
	
	@Override
	public List<MusicBean> findByTrem(MusicBean t) {
		
		
		
		// TODO Auto-generated method stub
		return dao.findByTrem(t);
	}
    
}
