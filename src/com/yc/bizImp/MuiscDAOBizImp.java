package com.yc.bizImp;

import java.util.List;

import com.yc.bean.MusicBean;
import com.yc.biz.MusicDAOBiz;
import com.yc.dao.MusicDAO;
import com.yc.util.StringUtil;

public class MuiscDAOBizImp implements MusicDAOBiz{

	MusicDAO dao= new MusicDAO();
	
	@Override
	public List<MusicBean> findByTrem(MusicBean t) {
		
		
		
		// TODO Auto-generated method stub
		return dao.findByTrem(t);
	}

	@Override
	public List<MusicBean> findByTrem1(int uid) {
		// TODO Auto-generated method stub
		
		if(uid != 0) {
			return dao.findByTrem1(uid);
		}
		
		return null;
	}
    
}
